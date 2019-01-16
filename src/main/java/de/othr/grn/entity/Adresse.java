package de.othr.grn.entity;

import de.othr.grn.entity.util.GeneratedIdEntity;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse{

    private String strasse;
    private String plz;
    private String ort;

    public Adresse() {
    }

    public Adresse(String strasse, String plz, String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return "Adresse {" + strasse + ", " + plz + " " + ort + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()==this.getClass()) {
            Adresse adresse = (Adresse) obj;
            if (this.strasse == adresse.strasse &&
                this.plz == adresse.plz &&
                this.ort == adresse.ort) {
                return true;
            }
        }
        return false;
    }
}
