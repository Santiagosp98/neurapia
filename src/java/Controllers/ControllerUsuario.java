/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facade.UsuarioFacade;
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
@Named(value = "controllerUsuario")
@SessionScoped
public class ControllerUsuario implements Serializable {

    /**
     * Creates a new instance of ControllerUsuario
     */
    public ControllerUsuario() {
    }
    @EJB
    private UsuarioFacade usuarioFacade; //Facade
    private Usuario usuario; //Entidades
    private List<Usuario> listaUsuarios;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        listaUsuarios = usuarioFacade.findAll();
    }
    
    public List<Usuario> consultarUsuarios(){
        this.listaUsuarios = usuarioFacade.findAll();
        return listaUsuarios;
    }
    
    public String eliminarUsuario(Usuario usuario){
        usuarioFacade.remove(usuario);
        return "ConsultarUsuarios";
    }
    
    public String seleccionarUsuario(Usuario usuario){
        this.usuario = usuario;
        return "ActualizarUsuario";
    }
    
    
    public String actualizarUsuario(){
        usuarioFacade.edit(usuario);
        return "ConsultarUsuarios";
    }
    
    public String crearUsuario(){
        if (usuario != null) {
            usuarioFacade.create(usuario); 
            init();            
        }
        return "ConsutarUsuarios";   
        
    }
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioFacade
     */
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    /**
     * @param usuarioFacade the usuarioFacade to set
     */
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
