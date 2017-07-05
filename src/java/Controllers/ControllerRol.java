/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Rol;
import Facade.RolFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author jair3
 */
@Named(value = "controllerRol")
@ConversationScoped
public class ControllerRol implements Serializable {

    /**
     * Creates a new instance of ControllerRol
     */
    @EJB
    private RolFacadeLocal rolFacade;
    private Rol rol;
    private List<Rol> listaRol;
     
    public ControllerRol() {
    }

    @PostConstruct
    public void init(){
        rol = new Rol();
        listaRol = rolFacade.findAll();
    }
    
    public RolFacadeLocal getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacadeLocal rolFacade) {
        this.rolFacade = rolFacade;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }
    
    
    
}
