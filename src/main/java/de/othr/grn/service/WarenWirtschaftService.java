package de.othr.grn.service;

import de.othr.grn.entity.*;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class WarenWirtschaftService implements WarenWirtschaftServiceIF{

    @PersistenceContext(unitName = "grPU")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Lieferung aufgeben(Lieferung neu){

        if (neu.getClass() == Bestellung.class){
            Bestellung bestellung = (Bestellung) neu;
            bestellung.setLagergut(createLagergut(bestellung.getLagergut()));
        }

        entityManager.persist(neu);

        return neu;
    }

    @Override
    @Transactional
    public Lieferung empfangen(Lieferung versandt){
        Lieferung temp = entityManager.find(Lieferung.class, versandt.getLieferNr());
        temp.setZugestellt(true);
        return temp;
    }

    @Override
    @Transactional
    public void klebebandBestellen() {

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

        System.out.println("GEFUNDEN" + gefunden);

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
