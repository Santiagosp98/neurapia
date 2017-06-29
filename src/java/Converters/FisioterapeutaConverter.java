/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;


import Entities.Fisioterapeuta;
import Facade.FisioterapeutaFacadeLocal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author jair3
 */
@FacesConverter(value = "fisioterapeutaConverter")
public class FisioterapeutaConverter implements Converter {
    
    private FisioterapeutaFacadeLocal ftl;
    
    public FisioterapeutaConverter(){
        ftl = CDI.current().select(FisioterapeutaFacadeLocal.class).get(); 
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value != null && !value.equals("")){
            try {
                
                return ftl.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Fisioterapeuta){
            return ((Fisioterapeuta) value).getIdFisioterapeuta().toString();
        }
        return "";
    }
}
