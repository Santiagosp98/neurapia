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
import javax.faces.view.ViewScoped;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerPrediagnosticoEliminar")
@ViewScoped
public class ControllerPrediagnosticoEliminar extends ControllerApp {

    /**
     * Creates a new instance of ControllerPrediagnosticoEliminar
     */
    
    @EJB
    private PrediagnosticoFacadeLocal pfl;
    private Prediagnostico seleccionarEliminarPrediagnostico;
    
    public ControllerPrediagnosticoEliminar() {
    }

    
    
    public PrediagnosticoFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(PrediagnosticoFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public Prediagnostico getSeleccionarEliminarPrediagnostico() {
        return seleccionarEliminarPrediagnostico;
    }

    public void setSeleccionarEliminarPrediagnostico(Prediagnostico seleccionarEliminarPrediagnostico) {
        this.seleccionarEliminarPrediagnostico = seleccionarEliminarPrediagnostico;
    }
       
    public void prepararEliminarPrediagnostico(Prediagnostico predi) {
        iniciarConversacion();
        seleccionarEliminarPrediagnostico = predi;
        System.out.println(seleccionarEliminarPrediagnostico.getIdPrediagnostico());
    }

    public void eliminarPrediagnostico() {        
        pfl.remove(seleccionarEliminarPrediagnostico);
    }
}
