/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Departamento;
import Facade.DepartamentoFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jair3
 */
@Named(value = "controllerDepartamentoEliminar")
@ViewScoped
public class ControllerDepartamentoEliminar implements Serializable {

    /**
     * Creates a new instance of ControllerDepartamentoEliminar
     */
    @EJB
    private DepartamentoFacadeLocal departamentoFacade;
    private Departamento seleccionarEliminarDepartamento;

    public ControllerDepartamentoEliminar() {
    }

    public Departamento getSeleccionarEliminarDepartamento() {
        return seleccionarEliminarDepartamento;
    }

    public void setSeleccionarEliminarDepartamento(Departamento seleccionarEliminarDepartamento) {
        this.seleccionarEliminarDepartamento = seleccionarEliminarDepartamento;
    }

    public void prepararEliminarDepartamento(Departamento dep) {
        seleccionarEliminarDepartamento = dep;
        System.out.println(seleccionarEliminarDepartamento.getNombreCiudad());
    }

    public void eliminarDepartamento() {
        try {
            System.out.println(seleccionarEliminarDepartamento.getNombreCiudad());
            departamentoFacade.remove(seleccionarEliminarDepartamento);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el Departamento", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }

}
