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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

        if (neu.getClass() == Bestellung.class){
            logger.info("Lieferung als Bestellung identifiziert");
            Bestellung bestellung = (Bestellung) neu;
            neu = new Bestellung(((Bestellung) neu).getLagergut(),neu.getAdresse(),((Bestellung) neu).getAnzahl());
            bestellung.setLagergut(createLagergut(bestellung.getLagergut()));

            //Eigenlager managen
            if(bestellung.getAdresse().equals(constantService.getEigeneAdresse())){
                if(bestellung.getLagergut().manageEigenlager(bestellung.getAnzahl())){
                    logger.info(constantService.getKlebeband() + "-Bestellung identifiziert, Eigenlager erhöht");
                    entityManager.persist(neu);
                    bestellung.setLieferStatus(LieferStatus.s6);
                    return neu;
                }
            }

        }

        if(neu.getVersandart() == null){
            neu.setVersandart(versandartService.findVersandart("0.STD"));
            logger.info("Versandart fehlt; auf Standartversand gesetzt");
        }

        entityManager.persist(neu);
        neu.setLieferStatus(LieferStatus.s1);
        neu.setVerfolgungsNr(neu.hashCode());

        //TODO: Versand überweißen (lassen)
        logger.info("Versandüberweißung eingeleitet");
        TransaktionsServiceService transaktionsServiceService = new TransaktionsServiceService();
        TransaktionsService stub = transaktionsServiceService.getTransaktionsServicePort();

        Transaktion transaktion = new Transaktion();
        transaktion.setVon(kontoNr);
        transaktion.setZu(constantService.getKontonr());
        transaktion.setBetrag(neu.versandBerechnen());
        transaktion.setVerwendungszweck("Versandkosten von PaketNr: " + neu.getLieferNr());
        logger.info("Versandkosten Ueberweisung über: "+ transaktion.getBetrag() +" wird abgeschickt");
        transaktion = stub.transaktionTaetigen(transaktion);
        logger.info("Transaktion " + (transaktion.isAbgeschlossen()?"erfolgreich":"gescheitert"));

        TypedQuery<Lagergut> query = entityManager.createQuery(
                "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
        query.setParameter("ware",constantService.getKlebeband());
        Eigenlager klebenand = (Eigenlager) query.getSingleResult();
        klebenand.setAnzahl(klebenand.getAnzahl()-1);

        if (klebenand.getAnzahl()<5){
            klebebandBestellen();
            logger.info("Klebandstand gering, neues wird bestellt");
        }

        return neu;
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
            TypedQuery<Lagergut> query = entityManager.createQuery(
                    "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware",lagergut.getWare());
            return query.getSingleResult();
        }catch(NoResultException e) {
            entityManager.persist(lagergut);
            return lagergut;
        }
    }

    public String longToEuro(long betrag){
        return ((betrag/100L) + "," + (betrag%100L) + "€");
    }

}
