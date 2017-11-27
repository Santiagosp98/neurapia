/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
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
public class ControllerDepartamentoEliminar extends ControllerApp {

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
    }

    public void eliminarDepartamento() {
        try {
            System.out.println(seleccionarEliminarDepartamento.getNombreCiudad());
            departamentoFacade.remove(seleccionarEliminarDepartamento);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("department-deleted"), "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el Departamento", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }

}
