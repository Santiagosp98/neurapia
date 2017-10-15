package ControllersCargaDatos;

import Facade.UsuarioFacadeLocal;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "cargaUsuario")
@ViewScoped
public class ControllerCargaUsuario implements Serializable {

    public ControllerCargaUsuario() {
    }

    @Inject
    private ControllerCargaDatos cargaDatos;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public void cargarUsuarios() {
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
        usuarioFacade.cargarUsuarios(cargaDatos.subir());
        cargaDatos.eliminar();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga de datos realizada.", "");
        context.addMessage(null, message);
    }
}
