package de.othr.grn.service;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Lieferung;

import java.util.List;

public interface WarenWirtschaftServiceIF {

    /**
     * @param kontoNr Kontonummer des Versandbezahlers
     */
    Lieferung aufgeben(Lieferung neu, long kontoNr);
    Lieferung empfangen(Lieferung versandt);
    void klebebandBestellen();
    List<Lieferung> lieferungenAnzeigen(Adresse adresse);

}
