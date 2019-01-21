package de.othr.grn.service;

import de.othr.grn.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vilsmeier.Transaktion;
import vilsmeier.TransaktionsService;
import vilsmeier.TransaktionsServiceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@RequestScoped
@WebService
public class WarenWirtschaftService implements WarenWirtschaftServiceIF{

    //Zugang: http://localhost:8080/grothDHL-0.1/WarenWirtschaftService?wsdl

    @Inject
    VersandartService versandartService;

    @Inject
    ConstantService constantService;

    @PersistenceContext(unitName = "grPU")
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(WarenWirtschaftService.class);

    @Transactional
    public Lieferung aufgeben(Lieferung neu,long kontoNr) throws TransactionException{
        logger.info("Lieferung wird aufgeben: " + neu.toString());

        Lieferung tmpLief;
        Adresse tmpAdr = new Adresse();
        tmpAdr.setStrasse(neu.getAdresse().getStrasse());
        tmpAdr.setPlz(neu.getAdresse().getPlz());
        tmpAdr.setOrt(neu.getAdresse().getOrt());

        if (neu.getClass() == Bestellung.class){
            logger.info("Lieferung als Bestellung identifiziert");
            Bestellung bestellung =  new Bestellung(createLagergut(((Bestellung)neu).getLagergut()),tmpAdr,((Bestellung) neu).getAnzahl());

            //Eigenlager managen
            if(bestellung.getAdresse().equals(constantService.getEigeneAdresse())){
                if(bestellung.getLagergut().manageEigenlager(bestellung.getAnzahl())){
                    logger.info(constantService.getKlebeband() + "-Bestellung identifiziert, Eigenlager erhöht");
                    entityManager.persist(bestellung);
                    bestellung.setLieferStatus(LieferStatus.s6);
                    return bestellung;
                }
            }
            tmpLief = bestellung;
        }else{
            Paket paket = new Paket();
            paket.setInhalt(((Paket)neu).getInhalt());
            paket.setGewicht(neu.getGewicht());
            paket.setAdresse(tmpAdr);
            tmpLief = paket;
        }

        if(neu.getVersandart() == null){
            tmpLief.setVersandart(versandartService.findVersandart("0.STD"));
            logger.info("Versandart fehlt; auf Standartversand gesetzt");
        }else{
            tmpLief.setVersandart(neu.getVersandart());
        }

        tmpLief.setLieferStatus(LieferStatus.s1);
        transaction(tmpLief, kontoNr);
        tmpLief.setLieferStatus(LieferStatus.s5);
        entityManager.persist(tmpLief);

        Query query = entityManager.createQuery(
                "SELECT s FROM Eigenlager AS s WHERE s.ware = :ware",Eigenlager.class);
        query.setParameter("ware",constantService.getKlebeband());
        Eigenlager klebeband = (Eigenlager) query.getSingleResult();
        klebeband.setAnzahl(klebeband.getAnzahl()-1);
        klebeband = entityManager.merge(klebeband);

        if (klebeband.getAnzahl()<5){
            klebebandBestellen();
            logger.info("Klebandstand gering, neues wird bestellt");
        }

        return tmpLief;
        // TODO: Lieferungsstatus über Zeit? verändern
    }

    @Override
    @Transactional
    @WebMethod
    public String bestellungAufgeben(@WebParam(name = "Bestellung") Bestellung neu,@WebParam(name = "Kontonummer") long kontoNr) throws TransactionException{
        logger.info("Bestellung empfangen, Auftrag wird aufgegeben");
        Lieferung lieferung = aufgeben(neu,kontoNr);
        String returnString = constantService.getDomain() + constantService.getStatusUrl() + lieferung.getLieferNr();
        logger.info("Bestellung erfolgreich; PaketverfolgungsURL: " + returnString);
        return returnString;
    }

    private void transaction(Lieferung lieferung, long kontoNr) throws TransactionException{
        logger.info("Versandüberweißung eingeleitet");

        if(kontoNr == constantService.getKontonr()){
            logger.info("Eigene Kontonummer angegeben; Versand wird übersprungen");
            return;
        }

        TransaktionsServiceService transaktionsServiceService = new TransaktionsServiceService();
        TransaktionsService stub = transaktionsServiceService.getTransaktionsServicePort();

        Transaktion transaktion = new Transaktion();
        transaktion.setVon(kontoNr);
        transaktion.setZu(constantService.getKontonr());
        transaktion.setBetrag(lieferung.versandBerechnen());
        transaktion.setVerwendungszweck("Versandkosten von Bestellung: " + lieferung.toString());
        logger.info("Versandkosten Ueberweisung über: "+ transaktion.getBetrag() +" wird abgeschickt");

        try{
            transaktion = stub.transaktionTaetigen(transaktion);
        }catch (Exception e){
            throw new TransactionException("Fehler bei der Verbindung zu Vilsmeier-Bank: " + e.getMessage());
        }

        logger.info("Transaktion " + (transaktion.isAbgeschlossen()?"erfolgreich":"gescheitert"));

        if(!transaktion.isAbgeschlossen()){
            throw new TransactionException("Transaktionsdaten fehlerhaft. Transaktion nicht erfolgreich. Bitte überprüfen Sie ihre Daten");
        }
    }

    @Transactional
    public Lieferung empfangen(Lieferung versandt){
        Lieferung temp = entityManager.find(Lieferung.class, versandt.getLieferNr());
        temp.setLieferStatus(LieferStatus.s6);
        temp = entityManager.merge(temp);
        return temp;
    }

    @Transactional
    public void klebebandBestellen() {
        //TODO: Schnittstelle von BBestellungen importieren
    }

    @Transactional
    public int getKlebebandAnzahl(){
        try{
            TypedQuery<Lagergut> query = entityManager.createQuery(
                    "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware",constantService.getKlebeband());
            return ((Eigenlager) query.getSingleResult()).getAnzahl();
        }catch (NoResultException e){
            logger.error(constantService.getKlebeband()+" nicht gefunden.");
            return 0;
        }
    }

    @Transactional
    public List<Lieferung> lieferungenAnzeigen(Adresse adresse) {
        TypedQuery<Lieferung> query = entityManager.createQuery(
                "SELECT s FROM Lieferung AS s WHERE s.adresse = :adresse",
                Lieferung.class);
                query.setParameter("adresse",adresse);
        return query.getResultList();
    }

    @Transactional
    public Lieferung findeLieferung(long paketNr) {
        Lieferung gefunden = entityManager.find(Lieferung.class, paketNr);

        logger.info("Lieferung "+ paketNr + "gesucht. Gefunden: " + ((gefunden == null)?"keine":gefunden));

        return gefunden;
    }

    public Collection<Lieferung> getAlleLieferungen(){
        TypedQuery<Lieferung> query = entityManager.createQuery("SELECT s FROM Lieferung AS s", Lieferung.class);
        return query.getResultList();
    }

    @Transactional
    public Lieferung loescheLieferung(Lieferung lieferung){
        lieferung = entityManager.find(Lieferung.class, lieferung.getId());
        entityManager.remove(lieferung);
        logger.info("Lieferung gelöscht: "+ lieferung);
        return lieferung;
    }

    public Collection<Lagergut> getAlleLagergueter(){
        TypedQuery<Lagergut> query = entityManager.createQuery("SELECT s FROM Lagergut AS s", Lagergut.class);
        return query.getResultList();
    }

    @Transactional
    private Lagergut createLagergut(Lagergut lagergut){
        Lagergut tmpLag;
        logger.info("Lagergut wird angefragt...");
        try{
            Query query = entityManager.createQuery(
                    "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware",lagergut.getWare());
            tmpLag =(Lagergut) query.getSingleResult();
        }catch(NoResultException e) {
            tmpLag = new Lagergut();
            tmpLag.setWare(lagergut.getWare());
            tmpLag.setGewicht(lagergut.getGewicht());
            logger.info("Neues Lagergut angelegt: "+tmpLag);
            entityManager.persist(tmpLag);
            return tmpLag;
        }
        logger.info("Lagergut schon vorhanden: " + tmpLag);
        return tmpLag;
    }

    public String longToEuro(long betrag){
        String nachKomma = (betrag%100L)+"";
        if((betrag%100L)<10)
            nachKomma = 0+nachKomma;
        return ((betrag/100L) + "," + nachKomma + "€");
    }

}
