package de.othr.grn.entity;

import javax.persistence.Entity;

@Entity
public class Paket extends Lieferung{

    private String inhalt;

    public Paket(){super();}

    /**
     * @param inhalt Bezeichner des Paketinhalts
     * @param gewicht Gewicht des Pakets in Gramm
     */
    public Paket(String inhalt, Adresse adresse, int gewicht, Versandart versandart) {
        super(adresse, gewicht, versandart);
        this.inhalt = inhalt;
    }

    @Override
    public String toString() {
        return "Paket {Inhalt: " + inhalt + ",\'" +
        super.toString() + "}";
    }
}
