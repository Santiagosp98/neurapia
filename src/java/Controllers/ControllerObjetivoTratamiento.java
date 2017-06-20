/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Objetivotratamiento;
import Facade.ObjetivotratamientoFacadeLocal;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
/**
 *
 * @author jair3
 */
@Named(value = "controllerObjetivoTratamiento")
@ConversationScoped
public class ControllerObjetivoTratamiento extends ControllerApp {

    /**
     * Creates a new instance of ControllerObjetivoTratamiento
     */
    public ControllerObjetivoTratamiento() {
    }
    @EJB
    ObjetivotratamientoFacadeLocal objetivoTratamientoFacade;
    Objetivotratamiento objetivotratamiento;
    List<Objetivotratamiento> listaobjetivoTratamiento;

    @PostConstruct
    public void init(){
        objetivotratamiento = new Objetivotratamiento();
        listaobjetivoTratamiento = objetivoTratamientoFacade.findAll();
    }
    
    public List<Objetivotratamiento> consultarObjetivoTratamiento(){
        this.listaobjetivoTratamiento = objetivoTratamientoFacade.findAll();
        return listaobjetivoTratamiento;
    }
    
    public String eliminarObjetivoTratamiento(Objetivotratamiento objetivoTratamiento){
        this.objetivoTratamientoFacade.remove(objetivoTratamiento);
        return "ConsultarDatosSistema";
    }
    
    public String seleccionarObjetivoTratamiento(Objetivotratamiento objetivoTratamiento){
        iniciarConversacion();
        this.objetivotratamiento = objetivoTratamiento;
        return "ActualizarDatosSistema";
    }
    
    public String crearObjetivoTratamiento(){
        this.objetivoTratamientoFacade.create(objetivotratamiento);
        return "ConsultarDatosSistema";
    }
    
    public String actualizarObjetivoTratamiento(){
        this.objetivoTratamientoFacade.edit(objetivotratamiento);
        finalizarConversacion();
        return "ConsultarDatosSistema";
    }
    
    public ObjetivotratamientoFacadeLocal getObjetivoTratamientoFacadeLocal() {
        return objetivoTratamientoFacade;
    }

    public void setObjetivoTratamientoFacadeLocal(ObjetivotratamientoFacadeLocal objetivoTratamientoFacade) {
        this.objetivoTratamientoFacade = objetivoTratamientoFacade;
    }

    public Objetivotratamiento getObjetivotratamiento() {
        return objetivotratamiento;
    }

    public void setObjetivotratamiento(Objetivotratamiento objetivotratamiento) {
        this.objetivotratamiento = objetivotratamiento;
    }

    public List<Objetivotratamiento> getListaobjetivoTratamiento() {
        return listaobjetivoTratamiento;
    }

    public void setListaobjetivoTratamiento(List<Objetivotratamiento> listaobjetivoTratamiento) {
        this.listaobjetivoTratamiento = listaobjetivoTratamiento;
    }
    
    
    
}

