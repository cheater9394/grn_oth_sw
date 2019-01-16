package de.othr.grn.entity;

import de.othr.grn.entity.util.StringIdEntity;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
        name="Versandart.alle",
        query="SELECT s FROM Versandart AS s"
)
public class Versandart extends StringIdEntity {
//    standart(1.0f, "Standart"),
//    express(1.5f, "Express"),
//    overnight(2.0f, "Overnight");

    private String name;
    private float speedfactor;

    public Versandart(){}

    public Versandart(String versandId) {
        super.id = versandId;
    }

    public Versandart(String versandId, String name, float speedfactor){
        this.speedfactor = speedfactor;
        this.name = name;
        super.id = versandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeedfactor(float speedfactor) {
        this.speedfactor = speedfactor;
    }



    public float getSpeedfactor() {
        return speedfactor;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
