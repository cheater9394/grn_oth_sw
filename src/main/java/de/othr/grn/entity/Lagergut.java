package de.othr.grn.entity;

import de.othr.grn.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;

@Entity
public class Lagergut extends GeneratedIdEntity{

    private String ware;
    private int gewicht;

    public Lagergut(){}

    public Lagergut(String ware, int gewicht) {
        this.ware = ware;
        this.gewicht = gewicht;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public boolean manageEigenlager(int anzahl){
        return false;
    }

    @Override
    public String toString() {
        return "Lagergut: { ID: " + id + ", Ware: " + ware + ", Gewicht: " + gewicht + "g}";
    }
}
