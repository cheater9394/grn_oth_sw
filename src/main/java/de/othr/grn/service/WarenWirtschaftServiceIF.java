package de.othr.grn.service;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Bestellung;
import de.othr.grn.entity.Lieferung;

import java.util.List;

public interface WarenWirtschaftServiceIF {

    Lieferung aufgeben(Lieferung neu, long kontoNr);
    Lieferung empfangen(Lieferung versandt);
    void klebebandBestellen();
    List<Lieferung> lieferungenAnzeigen(Adresse adresse);
    /**
     * @param kontoNr Kontonummer des Versandbezahlers
     */
    Lieferung bestellungAufgeben(Bestellung neu, long kontoNr);

}
