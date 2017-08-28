/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Caracteristicamovilidad;
import Entities.Usuario;
import Facade.CaracteristicamovilidadFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "caracteristicaMovilidadConverter")
public class CaracteristicaMovilidadConverter implements Converter{
    private CaracteristicamovilidadFacadeLocal cmfl;
    
    public CaracteristicaMovilidadConverter(){
        cmfl = CDI.current().select(CaracteristicamovilidadFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {
                
                return cmfl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Caracteristicamovilidad){
            return ((Caracteristicamovilidad) value).getIdCaracteristicaMovilidad().toString();
        }
        return "";
    }
}
