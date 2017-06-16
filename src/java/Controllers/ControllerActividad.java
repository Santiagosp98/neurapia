/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Actividad;
import Facade.ActividadFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author jair3
 */
@Named(value = "controllerActividad")
@ViewScoped
public class ControllerActividad implements Serializable {

    /**
     * Creates a new instance of ControllerActividad
     */
    public ControllerActividad() {
    }
    @EJB
    ActividadFacade actividadFacade;
    Actividad actividad;
    List<Actividad> listaActividad;
    
    
    @PostConstruct
    public void init(){
        actividad = new Actividad();
        listaActividad = actividadFacade.findAll();
    }
    
    public List<Actividad> consultarActividad(){
        this.listaActividad = actividadFacade.findAll();
        return listaActividad;
    }
    
    public String eliminarActividad(Actividad actividad){
        this.actividadFacade.remove(actividad);
        return "RegistroInformacion";
    }
    
    public String SeleccionarActividad(Actividad actividad){
        this.actividad = actividad;
        return "ActualizarRegistro";
    }
    
    public String crearActividad(){
        this.actividadFacade.create(actividad);
        return "registroInformacion";
    }
    
    
    
}
