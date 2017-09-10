/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.Resultadoproceso;
import Facade.ResultadoprocesoFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "resultadoProcesoConverter")
public class ResultadoProcesoConverter implements Converter {
    
    private ResultadoprocesoFacadeLocal rpfl;
    
    public ResultadoProcesoConverter(){
        rpfl = CDI.current().select(ResultadoprocesoFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {                
                return rpfl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getCause());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Resultadoproceso){
            return ((Resultadoproceso) value).getIdResultadoProceso().toString();
        }
        return "";
    }
}

