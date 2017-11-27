/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
import Entities.Eps;
import Facade.EpsFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Jair3
 */
@Named(value = "controllerEpsEliminar")
@ViewScoped
public class ControllerEpsEliminar extends ControllerApp{

    /**
     * Creates a new instance of ControllerEpsEliminar
     */
    
    @EJB
    private EpsFacadeLocal epsFacade;
    private Eps seleccionarEliminarEps;
    
    public ControllerEpsEliminar() {
    }

    public Eps getSeleccionarEliminarEps() {
        return seleccionarEliminarEps;
    }

    public void setSeleccionarEliminarEps(Eps seleccionarEliminarEps) {
        this.seleccionarEliminarEps = seleccionarEliminarEps;
    }
    
    public void prepararEliminarEps(Eps ep) {
        seleccionarEliminarEps = ep;
    }

    public void eliminarEps() {
        try {
            System.out.println(seleccionarEliminarEps.getNombreEps());
            epsFacade.remove(seleccionarEliminarEps);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("eps-deleted"), "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la EPS", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }
    
}

