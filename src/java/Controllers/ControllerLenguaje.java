package Controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named(value = "lenguaje")
@SessionScoped
public class ControllerLenguaje implements Serializable {

    private static final long serialVersionUID = 1L;

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLenguaje() {
        return locale.getLanguage();
    }

    public void cambiarLenguaje(String lenguaje) {
        locale = new Locale(lenguaje);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(lenguaje));
    }
}
