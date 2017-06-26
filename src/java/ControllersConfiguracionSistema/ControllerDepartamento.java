/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Departamento;
import Facade.DepartamentoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerDepartamento")
@ConversationScoped
public class ControllerDepartamento extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerDepartamento
     */
    public ControllerDepartamento() {
    }

    @EJB
    DepartamentoFacadeLocal departamentoFacade;
    Departamento departamento;
    List<Departamento> listaDepartamento;
    private Departamento departamentoSeleccionado;

    @PostConstruct
    public void init() {
        departamento = new Departamento();
        listaDepartamento = departamentoFacade.findAll();
    }

    public List<Departamento> consultarDepartamento() {
        this.listaDepartamento = departamentoFacade.findAll();
        return listaDepartamento;
    }

    public String crearDepartamento() {        
        FacesContext fcDep = FacesContext.getCurrentInstance();
        if (departamento.getNombreCiudad()!= null && !departamento.getNombreCiudad().equals("")) {
            this.departamentoFacade.create(departamento);
        return "ConsultarDatosSistema?faces-redirect=true";
        }else{
            FacesMessage mAct = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "El campo 'NUEVA DEPARTAMENTO' no debe ser nulo,", "Por favor ingreselo nuevamente");
            fcDep.addMessage(null, mAct);
        }
        return "";
    }

    public String prepararEditar(Departamento dep) {
        iniciarConversacion();
        departamentoSeleccionado = dep;
        return "EditarDepartamento?faces-redirect=true";
    }

    public String guardarCambios() {
        FacesContext fcDep = FacesContext.getCurrentInstance();
        if (departamentoSeleccionado.getNombreCiudad() != null && !departamentoSeleccionado.getNombreCiudad().equals("")) {
            departamentoFacade.edit(departamentoSeleccionado);
            finalizarConversacion();
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {
            FacesMessage mDep = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "El campo 'DEPARTAMENTO' no debe ser nulo,", "Por favor ingreselo nuevamente");
            fcDep.addMessage(null, mDep);
        }
        return "";
    }

    public DepartamentoFacadeLocal getDepartamentoFacade() {
        return departamentoFacade;
    }

    public void setDepartamentoFacadeLocal(DepartamentoFacadeLocal departamentoFacade) {
        this.departamentoFacade = departamentoFacade;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Departamento getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Departamento departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }


}

