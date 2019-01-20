package de.othr.grn.ui.model;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.LieferStatus;
import de.othr.grn.entity.Lieferung;
import de.othr.grn.service.ConstantService;
import de.othr.grn.service.WarenWirtschaftService;
import vilsmeier.StringIdEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Named
@SessionScoped
public class PaketverfolgungModel implements Serializable {

    @Inject
    WarenWirtschaftService warenWirtschaftService;

    @Inject
    ConstantService constantService;

    private long paketNr;
    private Collection<Lieferung> queryErgebnis;
    private Adresse adresse = new Adresse();

    public long getLieferNr() {
        return paketNr;
    }

    public void setLieferNr(long lieferNr) {
        this.paketNr = lieferNr;
    }

    public void sucheLieferungNachLink(){
        if(paketNr != 0) {
            queryErgebnis = new ArrayList<Lieferung>();
            queryErgebnis.add(warenWirtschaftService.findeLieferung(paketNr));
        }
    }

    public void sucheLieferungNachAdresse(){
        queryErgebnis = warenWirtschaftService.lieferungenAnzeigen(adresse);
    }

    public Collection<Lieferung> getQueryErgebnis() {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void reset(){
        paketNr = 0;
        queryErgebnis = null;
        adresse = new Adresse();
    }

    public boolean isEmpty(Collection<Lieferung> lieferungen){
        if (lieferungen == null)
            return true;
        return lieferungen.isEmpty();
    }

    public boolean noResult(Collection<Lieferung> lieferungen){
        if (lieferungen == null)
            return false;
        return lieferungen.isEmpty();
    }
}
