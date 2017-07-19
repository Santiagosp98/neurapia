/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Prediagnostico;
import Facade.PrediagnosticoFacade;
import Facade.PrediagnosticoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerPrediagnosticoEliminar")
@ConversationScoped
public class ControllerPrediagnosticoEliminar extends ControllerApp {

    /**
     * Creates a new instance of ControllerPrediagnosticoEliminar
     */
    public ControllerPrediagnosticoEliminar() {
    }

    @EJB
    private PrediagnosticoFacadeLocal prediagnosticoFacade;
    private Prediagnostico seleccionarEliminarPrediagnostico;

    public PrediagnosticoFacadeLocal getPrediagnosticoFacade() {
        return prediagnosticoFacade;
    }

    public void setPrediagnosticoFacade(PrediagnosticoFacadeLocal prediagnosticoFacade) {
        this.prediagnosticoFacade = prediagnosticoFacade;
    }

    public Prediagnostico getSeleccionarEliminarPrediagnostico() {
        return seleccionarEliminarPrediagnostico;
    }

    public void setSeleccionarEliminarPrediagnostico(Prediagnostico seleccionarEliminarPrediagnostico) {
        this.seleccionarEliminarPrediagnostico = seleccionarEliminarPrediagnostico;
    }

    
    public void prepararEliminarPrediagnostico(Prediagnostico prediagnostico) {
        iniciarConversacion();
        seleccionarEliminarPrediagnostico = prediagnostico;
        System.out.println(seleccionarEliminarPrediagnostico.getIdPrediagnostico());
    }

    public void eliminarPrediagnostico() {
        
        prediagnosticoFacade.remove(seleccionarEliminarPrediagnostico);

    }
}
