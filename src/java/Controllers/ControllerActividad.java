/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Actividad;
import Facade.ActividadFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author jair3
 */
@Named(value = "controllerActividad")
@Dependent
public class ControllerActividad {

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
}
