package ControllersCargaDatos;

import Facade.CitamedicaFacadeLocal;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "cargaCita")
@ViewScoped
public class ControllerCargaCitaMedica implements Serializable {

    public ControllerCargaCitaMedica() {
    }

    @Inject
    ControllerCargaDatos cargaDatos;

    @EJB
    private CitamedicaFacadeLocal citaMedicaFacade;

    public void cargarCitas() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (cargaDatos.getFile() == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un archivo.", "");
            context.addMessage(null, message);
            return;
        }
        if (!cargaDatos.getFile().getSubmittedFileName().contains(".csv")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sólo se permiten archivos con extensión .csv", "");
            context.addMessage(null, message);
            return;
        }
        citaMedicaFacade.cargarCitas(cargaDatos.subir());
        cargaDatos.eliminar();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga de datos realizada.", "");
        context.addMessage(null, message);
    }
}
