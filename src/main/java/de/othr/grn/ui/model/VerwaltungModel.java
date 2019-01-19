package de.othr.grn.ui.model;

import de.othr.grn.entity.*;
import de.othr.grn.service.WarenWirtschaftService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;

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

    public Collection<Lieferung> getLieferungen() {
        return warenWirtschaftService.getAlleLieferungen();
    }

    public boolean isBestellung(Lieferung lieferung){
        return (lieferung.getClass() == Bestellung.class);

    }

    public Bestellung castToBestellung(Lieferung lieferung){
        return (Bestellung) lieferung;
    }

    public Paket castToPaket(Lieferung lieferung){
        return (Paket) lieferung;
    }

    public Lieferung loescheLieferung(Lieferung lieferung){
        return warenWirtschaftService.loescheLieferung(lieferung);
    }

    public Collection<Lagergut> getLagergueter() {
        return warenWirtschaftService.getAlleLagergueter();
    }

    public boolean isEigenlager(Lagergut lagergut){
        return (lagergut.getClass() == Eigenlager.class);
    }

    public Eigenlager castToEigenlager(Lagergut lagergut){
        return (Eigenlager) lagergut;
    }
}
