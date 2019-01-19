package de.othr.grn.service;

import de.othr.grn.entity.Eigenlager;
import de.othr.grn.entity.Lagergut;
import de.othr.grn.entity.Versandart;
import de.othr.grn.ui.model.initModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    ConstantService constantService;

    Logger logger = LoggerFactory.getLogger(InitService.class);

    private Eigenlager klebeband;

    @Transactional
    public void init(){
        logger.info("Init eingeleitet");
        Versandart standart = entityManager.find(Versandart.class, "0.STD");
        if(standart == null){
            standart = new Versandart("0.STD","Standart",1.0f);
            entityManager.persist(standart);
            Versandart express = new Versandart("1.EXP","Express",1.5f);
            entityManager.persist(express);
            Versandart overnight = new Versandart("2.OVN","Overnight",2.0f);
            entityManager.persist(overnight);
            logger.info("Init Versandarten durchgeführt");
        }

        try{
            TypedQuery<Lagergut> query = entityManager.createQuery(
                    "SELECT s FROM Eigenlager AS s WHERE s.ware = :ware",Lagergut.class);
            query.setParameter("ware",constantService.getKlebeband());
            query.getSingleResult();
        }catch (NoResultException e){
            Eigenlager klebeband = new Eigenlager(constantService.getKlebeband(), 150, 50);
            entityManager.persist(klebeband);
            logger.info("Init "+ constantService.getKlebeband() +" durchgeführt");
        }
    }
}
