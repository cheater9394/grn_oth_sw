package de.othr.grn.entity;

public enum LieferStatus {
    s1 ("Auftrag eingegangen"),
    s2 ("Start-Paketzentrum erreicht"),
    s3 ("in Transit"),
    s4 ("Ziel-Paketzentrum erreicht"),
    s5 ("Bereit zum abholen"),
    s6 ("zugestellt");

    private final String name;

    LieferStatus(String s){
        name = s;
    }

    public LieferStatus forward(){
        switch (this) {
            case s1: return s2;
            case s2: return s3;
            case s3: return s4;
            case s4: return s5;
            case s5: return s6;
            default: return s6;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
