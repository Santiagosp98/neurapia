package util;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import java.util.PropertyResourceBundle;

@Model
public class BundleProducer{
    @Produces
    public PropertyResourceBundle getBundle() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().evaluateExpressionGet(facesContext, "#{msg}", PropertyResourceBundle.class);
    }
}
