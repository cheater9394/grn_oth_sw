package de.othr.grn.ui.model;


import de.othr.grn.entity.Lieferung;
import de.othr.grn.entity.Paket;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class TestModel implements Serializable {

    private String text = "Hijdfgkl";
    private int counter = 0;

    //"Action-Methoden"
    public void aendere(){
        System.out.println("--> aendere()");
        counter++;
        setText(text);
    }

    // GETTER/SETTER
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getText() {
        System.out.println("--> getText() = " + this.text);
        return text;
    }

    public void setText(String text) {
        System.out.println("--> setText() = " + this.text);
        this.text = text;
    }
}
