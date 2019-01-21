package de.othr.grn.service;

import de.othr.grn.entity.Adresse;
import de.othr.grn.entity.Bestellung;
import de.othr.grn.entity.Lieferung;

import java.io.Serializable;
import java.util.List;

public interface WarenWirtschaftServiceIF extends Serializable {

    /**
     * @param kontoNr Kontonummer des Versandbezahlers
     */
    String bestellungAufgeben(Bestellung neu, long kontoNr) throws TransactionException;

}
