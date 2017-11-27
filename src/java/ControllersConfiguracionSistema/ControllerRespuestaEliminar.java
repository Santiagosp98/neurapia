/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
import Entities.Respuesta;
import Facade.RespuestaFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerRespuestaEliminar")
@ViewScoped
public class ControllerRespuestaEliminar extends ControllerApp {

    /**
     * Creates a new instance of ControllerRespuestaEliminar
     */
    @EJB
    private RespuestaFacadeLocal respuestaFacade;
    private Respuesta seleccionarEliminarRespuesta;
    
    public ControllerRespuestaEliminar() {
    }

    public Respuesta getSeleccionarEliminarRespuesta() {
        return seleccionarEliminarRespuesta;
    }

    public void setSeleccionarEliminarRespuesta(Respuesta seleccionarEliminarRespuesta) {
        this.seleccionarEliminarRespuesta = seleccionarEliminarRespuesta;
    }
    
    public void prepararEliminarRespuesta(Respuesta resp) {
        seleccionarEliminarRespuesta = resp;
    }

    public void eliminarRespuesta() {
        try {
            System.out.println(seleccionarEliminarRespuesta.getNombreRespuesta());
            respuestaFacade.remove(seleccionarEliminarRespuesta);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("response-deleted"), "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la RESPUESTA", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }
    
}
