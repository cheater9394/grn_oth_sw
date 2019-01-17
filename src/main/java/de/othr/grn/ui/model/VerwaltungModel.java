package de.othr.grn.ui.model;

import de.othr.grn.entity.Eigenlager;
import de.othr.grn.entity.Lagergut;
import de.othr.grn.service.WarenWirtschaftService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@Named
@ApplicationScoped
public class VerwaltungModel implements Serializable {

    @Inject
    WarenWirtschaftService warenWirtschaftService;

    private int klebebandAnzahl;

    public int getKlebebandAnzahl(){
        klebebandAnzahl = warenWirtschaftService.getKlebebandAnzahl();
        return klebebandAnzahl;
    }
}
