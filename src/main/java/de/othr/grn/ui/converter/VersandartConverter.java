package de.othr.grn.ui.converter;

import de.othr.grn.entity.Versandart;
import de.othr.grn.service.VersandartService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class VersandartConverter implements Converter {

    private Map<String, Versandart> versandarten;

    @Inject
    private VersandartService versandartService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String versandId) {
        return this.versandarten.get(versandId);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object versandartO) {
        if(versandartO.getClass().equals(Versandart.class)) {
            Versandart versandart = (Versandart)versandartO;
            return versandart.getId();
        }else
            throw new IllegalArgumentException("Object must be type Versandart.class but is " + versandartO.getClass() + ".class");
    }

    @PostConstruct
    private void initVersandartMap(){
        if(this.versandarten == null){
            Collection<Versandart> versandarten = versandartService.getAlleVersandarten();
            this.versandarten = new HashMap<>();
            for(Versandart versart : versandarten)
                this.versandarten.put(versart.getId(), versart);
        }
    }
}
