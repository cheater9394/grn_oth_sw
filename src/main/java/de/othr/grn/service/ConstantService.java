package de.othr.grn.service;

import de.othr.grn.entity.Adresse;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ConstantService {

    private final Adresse eigeneAdresse = new Adresse("Unterweg 5","99994","Zentral-Posten");
    private final long kontonr = 1000002L;
    private final String klebeband = "Paketband";
    private final String statusUrl = "/pakete/statusverfolgung.xhtml?lieferNr=";
    private final String domain = "im-lamport:8080/grothDHL-0.1";

    public Adresse getEigeneAdresse() {
        return eigeneAdresse;
    }

    public String getKlebeband() {
        return klebeband;
    }

    public long getKontonr() {
        return kontonr;
    }

    public String getStatusUrl() {
        return statusUrl;
    }

    public String getDomain() {
        return domain;
    }
}
