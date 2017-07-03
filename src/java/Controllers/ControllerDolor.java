/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Dolor;
import Facade.DolorFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;

/**
 *
 * @author jair3
 */
@Named(value = "controllerDolor")
@ConversationScoped
public class ControllerDolor extends ControllerApp {

    /**
     * Creates a new instance of ControllerDolor
     */
    @EJB
    private DolorFacadeLocal dolorFacade;
    private Dolor dolor;
    private List<Dolor> listaDolor;
    
    public ControllerDolor() {
    }
    
    @PostConstruct
    public void init(){
        this.dolor = new Dolor();
        this.listaDolor = dolorFacade.findAll();
    }
    
    public List<Dolor> consultarDolor(){
        return listaDolor;
    }

    public DolorFacadeLocal getDolorFacade() {
        return dolorFacade;
    }
    
    

    public void setDolorFacade(DolorFacadeLocal dolorFacade) {
        this.dolorFacade = dolorFacade;
    }

    public Dolor getDolor() {
        return dolor;
    }

    public void setDolor(Dolor dolor) {
        this.dolor = dolor;
    }

    public List<Dolor> getListaDolor() {
        return listaDolor;
    }

    public void setListaDolor(List<Dolor> listaDolor) {
        this.listaDolor = listaDolor;
    }
    
    
    
    
}
