/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Facade.DepartamentoFacadeLocal;
import Entities.Departamento;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jair3
 */
@FacesConverter(value = "departamentoConverter")
public class DepartamentoConverter implements Converter{
    private DepartamentoFacadeLocal dtl;
    
    public DepartamentoConverter(){
        dtl = CDI.current().select(DepartamentoFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {
                
                return dtl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Departamento){
            return ((Departamento) value).getIdDepartamento().toString();
        }
        return "";
    }
}
