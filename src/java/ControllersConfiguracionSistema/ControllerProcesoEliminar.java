/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Proceso;
import Facade.ProcesoFacadeLocal;
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
@Named(value = "controllerProcesoEliminar")
@ViewScoped
public class ControllerProcesoEliminar implements Serializable{

    /**
     * Creates a new instance of ControllerProcesoEliminar
     */
    @EJB
    private ProcesoFacadeLocal procesoFacade;
    private Proceso seleccionarEliminarProceso;
    
    public ControllerProcesoEliminar() {
    }

    public Proceso getSeleccionarEliminarProceso() {
        return seleccionarEliminarProceso;
    }

    public void setSeleccionarEliminarProceso(Proceso seleccionarEliminarProceso) {
        this.seleccionarEliminarProceso = seleccionarEliminarProceso;
    }
    
     public void prepararEliminarProceso(Proceso proc) {
        seleccionarEliminarProceso = proc;
        System.out.println("fjnasjnfjnakjfnjsnkjfna  - " + seleccionarEliminarProceso.getNombreProceso());
    }

    public void eliminarProceso() {
        try {
            System.out.println("dksakdnaskndkanskndknaskldasd - - " + seleccionarEliminarProceso);
            procesoFacade.remove(seleccionarEliminarProceso);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso eliminada correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el Proceso", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }
}

