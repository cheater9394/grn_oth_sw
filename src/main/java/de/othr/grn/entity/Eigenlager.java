package de.othr.grn.entity;

import de.othr.grn.entity.Lagergut;

import javax.persistence.Entity;

@Entity
public class Eigenlager extends Lagergut {

    private int anzahl;

    public Eigenlager(){super();}

    public Eigenlager(String ware, int gewicht, int anzahl){
        super(ware, gewicht);
        this.anzahl = anzahl;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public boolean manageEigenlager(int anzahl) {
        this.anzahl += anzahl;
        return true;
    }

    @Override
    public String toString() {
        return ("Eigenlager: {" + super.toString() + " auf Lager: " + anzahl + "}");
    }
}
