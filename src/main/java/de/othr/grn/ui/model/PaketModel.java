package de.othr.grn.ui.model;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Paket;
import de.othr.grn.entity.Versandart;
import de.othr.grn.service.VersandartService;
import de.othr.grn.service.WarenWirtschaftService;
import de.othr.grn.ui.converter.VersandartConverter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
public class PaketModel implements Serializable {

    private Versandart versandart;
    private Collection<Versandart> versandarten;
    private Adresse tmpAdresse = new Adresse("Strasse","PLZ","Ort");
    private Paket tmpPaket = new Paket("Inhalt", tmpAdresse,0);
    private long kontonr;

    @Inject
    private VersandartService versandartService;

    @Inject
    private WarenWirtschaftService warenWirtschaftService;

    @Inject
    private VersandartConverter versandartConverter;

    public String gotoBezahlung(){
        return "bezahlung";
    }

    public Paket getTmpPaket() {
        return tmpPaket;
    }

    public void setTmpPaket(Paket tmpPaket) {
        this.tmpPaket = tmpPaket;
    }

    public Adresse getTmpAdresse() {
        return tmpAdresse;
    }

    public void setTmpAdresse(Adresse tmpAdresse) {
        this.tmpAdresse = tmpAdresse;
    }

    public Versandart getVersandart() {
        return versandart;
    }

    public void setVersandart(Versandart versandart) {
        this.versandart = versandart;
    }

    public Collection<Versandart> getVersandarten() {
        if(this.versandarten == null)
            this.versandarten =versandartService.getAlleVersandarten();
        return versandarten;
    }

    public void setVersandarten(Collection<Versandart> versandarten) {
        this.versandarten = versandarten;
    }

    public VersandartService getVersandartService() {
        return versandartService;
    }

    public long getKontonr() {
        return kontonr;
    }

    public void setKontonr(long kontonr) {
        this.kontonr = kontonr;
    }

    public void setVersandartService(VersandartService versandartService) {
        this.versandartService = versandartService;
    }

    public WarenWirtschaftService getWarenWirtschaftService() {
        return warenWirtschaftService;
    }

    public void setWarenWirtschaftService(WarenWirtschaftService warenWirtschaftService) {
        this.warenWirtschaftService = warenWirtschaftService;
    }

    public VersandartConverter getVersandartConverter() {
        return versandartConverter;
    }

    public void setVersandartConverter(VersandartConverter versandartConverter) {
        this.versandartConverter = versandartConverter;
    }
}
