package de.othr.grn.service;

import de.othr.grn.entity.Adresse;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ConstantService {

    private final Adresse eigeneAdresse = new Adresse("Unterweg 5","99994","Zentral-Posten");
    private final long kontonr = 1000005L;
    private final String klebeband = "Paketband";

    public Adresse getEigeneAdresse() {
        return eigeneAdresse;
    }

    public String getKlebeband() {
        return klebeband;
    }

    public long getKontonr() {
        return kontonr;
    }
}
