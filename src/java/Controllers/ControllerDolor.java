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
import javax.inject.Inject;

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
    
    @Inject ControllerAnamnesis ac;
    
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
    
    public Dolor crearDolor(){
        if (dolor != null) {
            System.out.println("Estamos creando el dolor con localizacion: " + dolor.getLocalizacion());
            dolorFacade.create(dolor);
            return dolor;
        }
        System.out.println("Dolor no creado");
        return dolor;
    }
    
    public void editarDolor() {
        dolor = ac.getAnamnesis().getCodDolor();
        System.out.println("Estamos editando Dolor");
        this.dolorFacade.edit(dolor);
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
