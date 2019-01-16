package de.othr.grn.service;

import de.othr.grn.entity.*;

import javax.enterprise.context.RequestScoped;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Name;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@WebService
public class WarenWirtschaftService implements WarenWirtschaftServiceIF{

    //Zugang: http://localhost:8080/grothDHL-0.1/WarenWirtschaftService?wsdl

    @PersistenceContext(unitName = "grPU")
    private EntityManager entityManager;

    @Override
    @Transactional
    @WebMethod
    public Lieferung aufgeben(@WebParam(name = "Lieferung") Lieferung neu,@WebParam(name = "Kontonummer") long kontoNr){

        if (neu.getClass() == Bestellung.class){
            Bestellung bestellung = (Bestellung) neu;
            bestellung.setLagergut(createLagergut(bestellung.getLagergut()));

            //Eigenlager managen
            if(bestellung.getLagergut().manageEigenlager(bestellung.getAnzahl())){
                //TODO: Mit eigener Adresse vergleichen
                entityManager.persist(neu);
                bestellung.setLieferStatus(LieferStatus.s6);
                return neu;
            }

        }

        entityManager.persist(neu);

        //TODO: Versand überweißen (lassen)
        //TODO Klebeband reduzieren

        return neu;
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

}
