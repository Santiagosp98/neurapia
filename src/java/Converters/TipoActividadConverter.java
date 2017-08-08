/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Tipoactividad;
import Facade.TipoactividadFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "tipoActividadConverter")
public class TipoActividadConverter implements Converter {
    
    private TipoactividadFacadeLocal tafl;
    
    public TipoActividadConverter(){
        tafl = CDI.current().select(TipoactividadFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {                
                return tafl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getCause());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Tipoactividad){
            return ((Tipoactividad) value).getIdTipoActividad().toString();
        }
        return "";
    }
}

