package de.othr.grn.entity;

import de.othr.grn.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public abstract class Lieferung extends GeneratedIdEntity {

    private Adresse adresse;
    private int gewicht;
    private boolean zugestellt;

    public Lieferung(){}

    /**
     * @param gewicht Gewicht des Pakets in Gramm
     */
    public Lieferung(Adresse adresse, int gewicht) {
        this.adresse = adresse;
        this.gewicht = gewicht;
        this.zugestellt = false;
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
        if (gewicht <= 20) return 70;
        else if (gewicht <= 50) return 85;
        else if (gewicht <= 500) return 145;
        else if (gewicht <= 1000) return 260;
        else if (gewicht <= 2000) return 480;
        else return 2090;
    }

    public long getLieferNr() {
        return id;
    }

    public boolean isZugestellt() {
        return zugestellt;
    }

    public void setZugestellt(boolean zugestellt) {
        this.zugestellt = zugestellt;
    }

    @Override
    public String toString() {
        return "Lieferung{" +
                "Id=" + id + "\'" +
                adresse.toString() + "\'" +
                "Gewicht : " + gewicht + "g\'" +
                "Lieferung " + (zugestellt?"zugestellt":"unterwegs") +
                '}';
    }
}
