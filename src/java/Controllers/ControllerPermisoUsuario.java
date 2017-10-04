/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Permiso;
import Facade.PermisoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerPermiso")
@ConversationScoped
public class ControllerPermisoUsuario extends ControllerApp {

    /**
     * Creates a new instance of ControllerPermisoUsuario
     */
    @EJB
    private PermisoFacadeLocal pfl;
    private Permiso permiso;
    private List<Permiso> listaPermisos;
    
    @Inject ControllerSession cs;
    
    public ControllerPermisoUsuario() {
    }
    
    @PostConstruct
    public void init(){
         permiso = new Permiso();
         listaPermisos = pfl.findAll();
    }
    
    public boolean consultarPermiso(String url){
        if (url.endsWith("historialclinico")) {
            return true;        
        }
        for (Permiso listaPermiso : listaPermisos) {
            if ( permiso.getUrl() != null && url.endsWith(permiso.getUrl())) {
                return true;
            }
        }
        return false;        
    }
    
}
