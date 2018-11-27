package de.othr.grn.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Bestellung extends Lieferung {

    @ManyToOne
    private Lagergut lagergut;
    private int anzahl;

    public Bestellung(){super();}

    /**
     * @param lagergut Referenz auf Inhaltsitem
     * @param anzahl Anzahl der Items
     */
    public Bestellung(Lagergut lagergut, Adresse adresse, int anzahl) {
        super(adresse, lagergut.getGewicht()*anzahl);
        this.lagergut = lagergut;
        this.anzahl = anzahl;
    }

    public Lagergut getLagergut() {
        return lagergut;
    }

    public void setLagergut(Lagergut lagergut) {
        this.lagergut = lagergut;
    }

    @Override
    public String toString() {
        return "Bestellung{" + lagergut.toString() + " x" + anzahl + "\'" +
                super.toString() + "}";
    }
}