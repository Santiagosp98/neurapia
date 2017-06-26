/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Educacion;
import Facade.EducacionFacadeLocal;
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
@Named(value = "controllerEducacionEliminar")
@ViewScoped
public class ControllerEducacionEliminar implements Serializable {

    /**
     * Creates a new instance of ControllerEducacionEliminar
     */
    @EJB
    private EducacionFacadeLocal educacionFacade;
    private Educacion seleccionarEliminarEducacion;

    public ControllerEducacionEliminar() {
    }

    public Educacion getSeleccionarEliminarEducacion() {
        return seleccionarEliminarEducacion;
    }

    public void setSeleccionarEliminarEducacion(Educacion seleccionarEliminarEducacion) {
        this.seleccionarEliminarEducacion = seleccionarEliminarEducacion;
    }

    public void prepararEliminarEducacion(Educacion educ) {
        seleccionarEliminarEducacion = educ;
        System.out.println(seleccionarEliminarEducacion.getTipoEducacion());
    }

    public void eliminarEducacion() {
        try {
            System.out.println(seleccionarEliminarEducacion);
            educacionFacade.remove(seleccionarEliminarEducacion);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad eliminada correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar la Actividad", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }
}

