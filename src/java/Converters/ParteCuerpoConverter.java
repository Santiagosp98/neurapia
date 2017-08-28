/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Partecuerpo;
import Facade.PartecuerpoFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "parteCuerpoConverter")
public class ParteCuerpoConverter implements Converter{
    private PartecuerpoFacadeLocal pcfl;
    
    public ParteCuerpoConverter(){
        pcfl = CDI.current().select(PartecuerpoFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {
                
                return pcfl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Partecuerpo){
            return ((Partecuerpo) value).getIdParteCuerpo().toString();
        }
        return "";
    }
}
