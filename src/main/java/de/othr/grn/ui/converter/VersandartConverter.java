package de.othr.grn.ui.converter;

import de.othr.grn.entity.Versandart;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.Map;

public class VersandartConverter implements Converter {

    private Map<String, Versandart> versandarten;



    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
