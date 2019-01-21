package de.othr.grn.ui.model;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Paket;
import de.othr.grn.entity.Versandart;
import de.othr.grn.service.TransactionException;
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
    private Adresse tmpAdresse = new Adresse();
    private Paket tmpPaket = new Paket();
    private long kontonr;
    private long versandkosten;
    private String message;

    @Inject
    private VersandartService versandartService;

    @Inject
    private WarenWirtschaftService warenWirtschaftService;

    @Inject
    private VersandartConverter versandartConverter;

    public String gotoBezahlung(){
        versandkosten = tmpPaket.versandBerechnen();
        message = null;
        return "bezahlung";
    }

    public String paketBestaetigen(){
        Adresse adr = new Adresse(tmpAdresse.getStrasse(),tmpAdresse.getPlz(),tmpAdresse.getOrt());
        Paket neu = new Paket(tmpPaket.getInhalt(),adr,tmpPaket.getGewicht(),tmpPaket.getVersandart());
        try{
            tmpPaket = (Paket) warenWirtschaftService.aufgeben(neu, kontonr);
            return "paketbestaetigt";
        }
        catch (TransactionException e){
            message = e.getMessage();
        }
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

    public String reset(){
        tmpAdresse = new Adresse();
        tmpPaket = new Paket();
        kontonr = 0;
        return "paketaufgeben";
    }

    public String getVersandkostenAsEuro(){
        return warenWirtschaftService.longToEuro(versandkosten);
    }

    public String getMessage() {
        return message;
    }
}
