package de.othr.grn.ui.model;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Paket;
import de.othr.grn.entity.Versandart;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
public class PaketModel implements Serializable {

    private Paket tmpPaket = new Paket();
    private Adresse tmpAdresse = new Adresse();
    private Versandart versandart;
//    private Collection<Versandart> = versandarten;

}
