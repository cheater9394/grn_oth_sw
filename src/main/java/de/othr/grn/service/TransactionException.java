package de.othr.grn.service;

public class TransactionException extends Exception{

    public TransactionException(){}

    public TransactionException(String message){
        super(message);
    }
}
