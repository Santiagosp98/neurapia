/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author jair3
 */
@Named(value = "controllerSession")
@SessionScoped
public class ControllerSession extends ControllerApp {

    /**
     * Creates a new instance of ControllerSession
     */
    public ControllerSession() {
    }
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private String email;
    private String clave;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        System.out.println("Estamos en Sesión");
        FacesContext fc = FacesContext.getCurrentInstance();
        if (email != null && !email.equals("") && clave != null && !clave.equals("") ) {
            usuario = usuarioFacade.iniciarSesion(email, clave);
            System.out.println(usuario.getClaveUsuario() + " " + usuario.getCorreoElectronico());
            if (usuario != null) {
                System.out.println("El usuario ingresado es: " + usuario.getPrimerNombre());
//                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,"Datos de acceso correctos", null);
//                fc.addMessage(null, m);
                return "Usuarios/ConsultarUsuarios";
            }else{
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email o clave incorrectas", "verifique los datos");
                fc.addMessage(null, m);
            }
        }else{
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Todos los datos son obligatorios", "Diligencie todos los datos");
                fc.addMessage(null, m);
        }return "";
    }
    
    public void cerrarSesion(){
        try {
            System.out.println("Estamos cerrando Sesion");
            
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            System.out.println(ec.getContext());
            ec.invalidateSession();
            this.clave = "";
            this.email = "";
            this.usuario = null;
            ec.redirect(hostName() + "index.xhtml");
            System.out.println(ec.getRequestContextPath()+ "/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControllerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean inicioSesion(){
        return(usuario != null);
    }
    
    public void validarSesion(){
        if (!inicioSesion()) {
            cerrarSesion();
        }
    }
}
