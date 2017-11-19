/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Entities.ResultadoFinal;
import Facade.ResultadoFinalFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "resultadoFinalConverter")
public class ResultadoFinalConverter implements Converter {
    
    private ResultadoFinalFacadeLocal rffl;
    
    public ResultadoFinalConverter(){
        rffl = CDI.current().select(ResultadoFinalFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {                
                return rffl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getCause());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof ResultadoFinal){
            return ((ResultadoFinal) value).getId().toString();
        }
        return "";
    }
}

