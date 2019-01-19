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

    @Override
    @Transactional
    public Lieferung aufgeben(Lieferung neu,long kontoNr){
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
        entityManager.persist(tmpLief);

        logger.info("Versandüberweißung eingeleitet");
        TransaktionsServiceService transaktionsServiceService = new TransaktionsServiceService();
        TransaktionsService stub = transaktionsServiceService.getTransaktionsServicePort();

        Transaktion transaktion = new Transaktion();
        transaktion.setVon(kontoNr);
        transaktion.setZu(constantService.getKontonr());
        transaktion.setBetrag(tmpLief.versandBerechnen());
        transaktion.setVerwendungszweck("Versandkosten von PaketNr: " + tmpLief.getLieferNr());
        logger.info("Versandkosten Ueberweisung über: "+ transaktion.getBetrag() +" wird abgeschickt");
        transaktion = stub.transaktionTaetigen(transaktion);
        logger.info("Transaktion " + (transaktion.isAbgeschlossen()?"erfolgreich":"gescheitert"));

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
    public Lieferung bestellungAufgeben(@WebParam(name = "Bestellung") Bestellung neu,@WebParam(name = "Kontonummer") long kontoNr){
        return aufgeben(neu,kontoNr);
    }

    @Override
    @Transactional
    public Lieferung empfangen(Lieferung versandt){
        Lieferung temp = entityManager.find(Lieferung.class, versandt.getLieferNr());
        temp.setLieferStatus(LieferStatus.s6);
        entityManager.merge(temp);
        return temp;
    }

    @Override
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
            return 0;
        }
    }

    @Override
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

        System.out.println("GEFUNDEN " + gefunden);

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
        return lieferung;
    }

    public Collection<Lagergut> getAlleLagergueter(){
        TypedQuery<Lagergut> query = entityManager.createQuery("SELECT s FROM Lagergut AS s", Lagergut.class);
        return query.getResultList();
    }

    @Transactional
    private Lagergut createLagergut(Lagergut lagergut){
        try{
            Query query = entityManager.createQuery(
                    "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware",lagergut.getWare());
            return (Lagergut) query.getSingleResult();
        }catch(NoResultException e) {
            Lagergut tmpLag = new Lagergut();
            tmpLag.setWare(lagergut.getWare());
            tmpLag.setGewicht(lagergut.getGewicht());
            entityManager.persist(tmpLag);
            return tmpLag;
        }
    }

    public String longToEuro(long betrag){
        String nachKomma = (betrag%100L)+"";
        if((betrag%100L)<10)
            nachKomma = 0+nachKomma;
        return ((betrag/100L) + "," + nachKomma + "€");
    }

}
