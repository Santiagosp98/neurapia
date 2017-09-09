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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            if (usuario != null) {
                if(usuario.getEstadoUsuario().equals("Activo")){
                    System.out.println("El usuario ingresado es: " + usuario.getPrimerNombre());
                    usuario.setIngresos(usuario.getIngresos() + 1);
                    Calendar calendar = Calendar.getInstance();
                    usuario.setUltimaSesion(calendar.getTime());
                    usuarioFacade.edit(usuario);
                    return "Usuarios/miPerfil?faces-redirect=true";     
                }else{
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Lo sentimos tu usuario esta inactivo", "Hable con el administrador para activar");
                    fc.addMessage(null, m);
                }
                        
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
        System.out.println("Validando");
        return(usuario != null);
    }
    
    public void validarSesion(){
        if (!inicioSesion()) {
            cerrarSesion();
        }
    }
    public boolean accesoSuperAdmin(){
        System.out.println("SuperAdmin");
        System.out.println(usuario.getCodRol().getNombreRol());
        if(usuario.getCodRol().getNombreRol().equals("Super Administrador") || usuario.getCodRol().getNombreRol().equals("Administrador")){
            System.out.println(usuario.getCodRol().getIdRol());
            return false;
        }
        return true;
    }
    public boolean accesoUsuario(){
        System.out.println("Usuario");
        System.out.println(usuario.getCodRol().getNombreRol());
        if(usuario.getCodRol().getNombreRol().equals("Usuario")){
            System.out.println("Ingreso igual usuario");
            return false;
        }
        return true;

    }
    
    public boolean accesoFisioterapeuta(){
        System.out.println("Fisioterapeuta");
        System.out.println(usuario.getCodRol().getNombreRol());
        if(usuario.getCodRol().getNombreRol().equals("Fisioterapeuta")){
            System.out.println("Ingreso igual a fisioterapeuta");
            return false;
        }
        return true;
    }
    
    public void validarAccesoFisioterapeuta(){
        System.out.println("Validando");
        if(accesoFisioterapeuta() == false){
            System.out.println("Validado");
            redireccionPaciente();
        }
    }
    
    public void validarAccesoUsuario(){
        System.out.println("Validando");
        if(accesoUsuario() == false){
            System.out.println("Validado");
            redireccionPaciente();
        }
    }
    
    public void validarAccesoSuperAdmin(){
        System.out.println("Hola validando");
        if(accesoSuperAdmin() == false){
            System.out.println("Validado");
            redireccionPaciente();
        }
    }

    public void redireccionPaciente(){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            System.out.println(hostName() + "Usuarios/miPerfil.xhtml");
            ec.redirect(hostName() + "Usuarios/miPerfil.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(ControllerSession.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
