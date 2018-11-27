package de.othr.grn.entity;

public enum Versandart {
    standart(1.0f, "Standart"),
    express(1.5f, "Express"),
    overnight(2.0f, "Overnight");

    float speedfactor;
    String name;

    Versandart(float speedfactor, String name){
        this.speedfactor = speedfactor;
        this.name = name;
    }

    public float getSpeedfactor() {
        return speedfactor;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
