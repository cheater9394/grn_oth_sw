package de.othr.grn.service;

import de.othr.grn.entity.Eigenlager;
import de.othr.grn.entity.Lagergut;
import de.othr.grn.entity.Versandart;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void init(){
        Versandart standart = entityManager.find(Versandart.class, "STD");
        if(standart == null){
            standart = new Versandart("STD","Standart",1.0f);
            entityManager.persist(standart);
            Versandart express = new Versandart("EXP","Express",1.5f);
            entityManager.persist(express);
            Versandart overnight = new Versandart("OVN","Overnight",2.0f);
            entityManager.persist(overnight);
        }

        try{
            TypedQuery<Lagergut> query = entityManager.createQuery(
                    "SELECT s FROM Lagergut AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware","Klebeband");
            query.getSingleResult();
        }catch (NoResultException e){
            Eigenlager klebeband = new Eigenlager("Klebeband", 150, 5);
            entityManager.persist(klebeband);
        }
    }
}
