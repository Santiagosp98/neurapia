/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Objetivotratamiento;
import Facade.ObjetivotratamientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author jair3
 */
@Named(value = "controllerObjetivoTratamiento")
@SessionScoped
public class ControllerObjetivoTratamiento implements Serializable {

    /**
     * Creates a new instance of ControllerObjetivoTratamiento
     */
    public ControllerObjetivoTratamiento() {
    }
    @EJB
    ObjetivotratamientoFacade objetivoTratamientoFacade;
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
}

