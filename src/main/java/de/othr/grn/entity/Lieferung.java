package de.othr.grn.entity;

import de.othr.grn.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public abstract class Lieferung extends GeneratedIdEntity {

    private Adresse adresse;
    private int gewicht;
    private LieferStatus lieferStatus;
    private Versandart versandart;

    public Lieferung(){}

    /**
     * @param gewicht Gewicht des Pakets in Gramm
     */
    public Lieferung(Adresse adresse, int gewicht, Versandart versandart) {
        this.adresse = adresse;
        this.gewicht = gewicht;
        this.lieferStatus = LieferStatus.s1;
        this.versandart = versandart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieferung paket = (Lieferung) o;
        return id == paket.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long versandBerechnen(){
        int versand;
        if (gewicht <= 20) versand=70;
        else if (gewicht <= 50) versand=85;
        else if (gewicht <= 500) versand=145;
        else if (gewicht <= 1000) versand=260;
        else if (gewicht <= 2000) versand=480;
        else versand=2090;

        return (long) (versand*versandart.getSpeedfactor());
    }

    public long getLieferNr() {
        return id;
    }

    public LieferStatus getLieferStatus() {
        return lieferStatus;
    }

    public void setLieferStatus(LieferStatus lieferStatus) {
        this.lieferStatus = lieferStatus;
    }

    @Override
    public String toString() {
        return "Lieferung {" +
                "ID: " + id + ", " +
                adresse.toString() +
                ", Gewicht: " + gewicht + "g, " +
                "Lieferstatus: " + lieferStatus +
                '}';
    }
}
