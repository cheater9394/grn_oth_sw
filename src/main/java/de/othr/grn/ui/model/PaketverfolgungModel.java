package de.othr.grn.ui.model;

import de.othr.grn.entity.LieferStatus;
import de.othr.grn.entity.Lieferung;
import de.othr.grn.service.ConstantService;
import de.othr.grn.service.WarenWirtschaftService;
import vilsmeier.StringIdEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class PaketverfolgungModel implements Serializable {

    @Inject
    WarenWirtschaftService warenWirtschaftService;

    @Inject
    ConstantService constantService;

    private long paketNr;
    private Lieferung queryErgebnis;

    public long getLieferNr() {
        return paketNr;
    }

    public void setLieferNr(long lieferNr) {
        this.paketNr = lieferNr;
    }

    public void sucheLieferung(){
        queryErgebnis = warenWirtschaftService.findeLieferung(paketNr);
    }

    public Lieferung getQueryErgebnis() {
        return queryErgebnis;
    }

    public String getStatusUrl(){
        return constantService.getStatusUrl();
    }

    public boolean empfangbar(Lieferung lieferung){
        return  (lieferung.getLieferStatus() == LieferStatus.s5);
    }

    public void empfangen(Lieferung lieferung){
        warenWirtschaftService.empfangen(lieferung);
    }
}
