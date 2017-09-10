/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Objetivotratamiento;
import Facade.ObjetivotratamientoFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "objetivoTratamientoConverter")
public class ObjetivoTratamientoConverter implements Converter {
    
    private ObjetivotratamientoFacadeLocal otfl;
    
    public ObjetivoTratamientoConverter(){
        otfl = CDI.current().select(ObjetivotratamientoFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {                
                return otfl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getCause());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Objetivotratamiento){
            return ((Objetivotratamiento) value).getIdObjetivoTratamiento().toString();
        }
        return "";
    }
}

