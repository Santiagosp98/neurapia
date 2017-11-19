/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Respuestaactividad;
import Facade.RespuestaactividadFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "respuestaActividadConverter")
public class RespuestaActividadConverter implements Converter {
    
    private RespuestaactividadFacadeLocal rafl;
    
    public RespuestaActividadConverter(){
        rafl = CDI.current().select(RespuestaactividadFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {                
                return rafl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getCause());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Respuestaactividad){
            return ((Respuestaactividad) value).getIdRespuestaActividad().toString();
        }
        return "";
    }
}

