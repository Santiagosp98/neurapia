/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Objetivotratamiento;
import Facade.ObjetivotratamientoFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Jair3
 */
@Named(value = "controllerObjetivoTratamientoEliminar")
@ViewScoped
public class ControllerObjetivoTratamientoEliminar implements Serializable {

    /**
     * Creates a new instance of ControllerObjetivoTratamienntoEliminar
     */
    @EJB
    private ObjetivotratamientoFacadeLocal objetivoTratamientoFacade;
    private Objetivotratamiento selecccionarEliminarObjetivoTratamiento;

    @Inject

    public ControllerObjetivoTratamientoEliminar() {
    }

    public ObjetivotratamientoFacadeLocal getObjetivoTratamientoFacade() {
        return objetivoTratamientoFacade;
    }

    public void setObjetivoTratamientoFacadeLocal(ObjetivotratamientoFacadeLocal objetivoTratamientoFacade) {
        this.objetivoTratamientoFacade = objetivoTratamientoFacade;
    }

    public Objetivotratamiento getSelecccionarEliminarObjetivoTratamiento() {
        return selecccionarEliminarObjetivoTratamiento;
    }

    public void setSelecccionarEliminarObjetivoTratamiento(Objetivotratamiento selecccionarEliminarObjetivoTratamiento) {
        this.selecccionarEliminarObjetivoTratamiento = selecccionarEliminarObjetivoTratamiento;
    }

    public void prepararEliminarObjetivoTratamiento(Objetivotratamiento objt) {
        selecccionarEliminarObjetivoTratamiento = objt;
        System.out.println("fjnasjnfjnakjfnjsnkjfna  - " + selecccionarEliminarObjetivoTratamiento.getNombreObjetivo());
    }

    public void eliminarObjetivoTratamiento() {
        try {
            System.out.println("fjnasjnfjnakjfnjsnkjfna  - " + selecccionarEliminarObjetivoTratamiento);
            objetivoTratamientoFacade.remove(selecccionarEliminarObjetivoTratamiento);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Objetivo Tratamiento elimiando correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el Objetivo Tratamiento", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

}

