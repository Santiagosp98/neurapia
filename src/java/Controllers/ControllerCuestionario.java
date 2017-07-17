/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cuestionario;
import Facade.CuestionarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerCuestionario")
@ConversationScoped
public class ControllerCuestionario extends ControllerApp{

    /**
     * Creates a new instance of ControllerCuestionario
     */
    public ControllerCuestionario() {
    }
    
    @EJB
    private CuestionarioFacadeLocal cuestionarioFacade;
    private Cuestionario cuestionario;
    private List<Cuestionario> listaCuestionario;
    private Cuestionario cuestionarioSeleccionado;
    
    @PostConstruct
    public void init() {
        cuestionario = new Cuestionario();
        listaCuestionario = cuestionarioFacade.findAll();
    }
    
    public List<Cuestionario> consultarCuestionario() {
        this.listaCuestionario = cuestionarioFacade.findAll();
        return listaCuestionario;
    }
    
    public String prepararEditar(Cuestionario cuestio) {
        iniciarConversacion();
        cuestionarioSeleccionado = cuestio;
        return "CrearPrediagnostico?faces-redirect=true";
    }

    public CuestionarioFacadeLocal getCuestionarioFacade() {
        return cuestionarioFacade;
    }

    public void setCuestionarioFacade(CuestionarioFacadeLocal cuestionarioFacade) {
        this.cuestionarioFacade = cuestionarioFacade;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public List<Cuestionario> getListaCuestionario() {
        return listaCuestionario;
    }

    public void setListaCuestionario(List<Cuestionario> listaCuestionario) {
        this.listaCuestionario = listaCuestionario;
    }

    public Cuestionario getCuestionarioSeleccionado() {
        return cuestionarioSeleccionado;
    }

    public void setCuestionarioSeleccionado(Cuestionario cuestionarioSeleccionado) {
        this.cuestionarioSeleccionado = cuestionarioSeleccionado;
    }

    
    
    
    
    
}
