/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Actividad;
import Facade.ActividadFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "controllerActividadEliminar")
@ViewScoped
public class ControllerActividadEliminar implements Serializable{

    /**
     * Creates a new instance of ControllerActividadEliminar
     */
    
    @EJB    
    private ActividadFacadeLocal actividadFacade;
    private Actividad seleccionarEliminarActividad;
    
    public ControllerActividadEliminar() {
    }

    public Actividad getSeleccionarEliminarActividad() {
        return seleccionarEliminarActividad;
    }

    public void setSeleccionarEliminarActividad(Actividad seleccionarEliminarActividad) {
        this.seleccionarEliminarActividad = seleccionarEliminarActividad;
    }
    
    
     public void prepararEliminarActividad(Actividad elimact){
        seleccionarEliminarActividad = elimact;
        System.out.println(seleccionarEliminarActividad.getNombreActividad());
    }
    
        public void eliminarActividad(){
        try {
            System.out.println(seleccionarEliminarActividad);
            actividadFacade.remove(seleccionarEliminarActividad);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad eliminada correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la Actividad", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }
}

