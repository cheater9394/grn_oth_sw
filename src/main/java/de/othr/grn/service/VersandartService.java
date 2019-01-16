package de.othr.grn.service;

import de.othr.grn.entity.Versandart;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@RequestScoped
public class VersandartService {

    @PersistenceContext
    private EntityManager entityManager;

    public Versandart getVersandartById(String id) {
        return entityManager.find(Versandart.class, id);
    }

    public Collection<Versandart> getAlleVersandarten(){
        TypedQuery<Versandart> query = entityManager.createQuery("SELECT s FROM Versandart AS s", Versandart.class);
        return query.getResultList();
    }

    public Versandart findVersandart(String versandId){
        return entityManager.find(Versandart.class,versandId);
    }
}
