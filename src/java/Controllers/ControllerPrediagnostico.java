/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Prediagnostico;
import Facade.PrediagnosticoFacadeLocal;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;


/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerPrediagnostico")
@ConversationScoped
public class ControllerPrediagnostico extends ControllerApp {

    /**
     * Creates a new instance of ControllerPrediagnostico
     */
    public ControllerPrediagnostico() {
    }

     
    @EJB
    private PrediagnosticoFacadeLocal prediagnosticoFacade;
    private Prediagnostico prediagnostico;
    private List<Prediagnostico> listaPrediagnistico;
    private Prediagnostico prediagnosticoSeleccionado;


    @PostConstruct
    public void init() {
        this.prediagnostico = new Prediagnostico();
        this.listaPrediagnistico = prediagnosticoFacade.findAll();
    }

    public List<Prediagnostico> consultarPrediagnostico() {
        this.listaPrediagnistico = prediagnosticoFacade.findAll();
        return listaPrediagnistico;

    }

    public String seleccionarPrediagnostico(Prediagnostico prediagnostico) {
        iniciarConversacion();
        this.prediagnostico = prediagnostico;
        return "ActualizarPrediagnostico?faces-redirect=true";
    }

    public String guardarCambios() {
        prediagnosticoFacade.edit(prediagnostico);
        finalizarConversacion();
        return "ConsultarPrediagnostico?faces-redirect=true";
    }
  

    public String cancelarActualizar() {
        finalizarConversacion();
        return "ConsultarPrediagnostico?faces-redirect=true";
    }

    public PrediagnosticoFacadeLocal getPrediagnosticoFacade() {
        return prediagnosticoFacade;
    }

    public void setPrediagnosticoFacade(PrediagnosticoFacadeLocal prediagnosticoFacade) {
        this.prediagnosticoFacade = prediagnosticoFacade;
    }

    public Prediagnostico getPrediagnostico() {
        return prediagnostico;
    }

    public void setPrediagnostico(Prediagnostico prediagnostico) {
        this.prediagnostico = prediagnostico;
    }

    public List<Prediagnostico> getListaPrediagnistico() {
        return listaPrediagnistico;
    }

    public void setListaPrediagnistico(List<Prediagnostico> listaPrediagnistico) {
        this.listaPrediagnistico = listaPrediagnistico;
    }

    public Prediagnostico getPrediagnosticoSeleccionado() {
        return prediagnosticoSeleccionado;
    }

    public void setPrediagnosticoSeleccionado(Prediagnostico prediagnosticoSeleccionado) {
        this.prediagnosticoSeleccionado = prediagnosticoSeleccionado;
    }

    

}
