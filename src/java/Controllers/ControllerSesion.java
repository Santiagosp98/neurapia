/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jair3
 */
@Named(value = "controllerSesion")
@ViewScoped
public class ControllerSesion implements Serializable {
    private String email;
    private String clave;
    private Usuario usuarioSesion;
    /**
     * Creates a new instance of ControllerSesion
     */
    public ControllerSesion() {
    }
    @PostConstruct
    public void init(){
        
    }
    public String iniciar(){
        FacesContext fc = FacesContext.getCurrentInstance();
        if (email !=null && !email.equals("") && clave != null && !clave.equals("")) {
            if (email.equals("ias@gmail.com") && clave.equals("123456")) {
                usuarioSesion = new Usuario();
                return "ConsultarUsuarios.xhtml?faces-redirect";
            }else{
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Email o clave incorrecto", "verifique los datos");
            }
        } else{
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Todos los campos son obligatorios", "Diligencie todos los datos");
            fc.addMessage(null,m);
        }
        return "";
    }
    public boolean inicioSesion(){
        if (usuarioSesion != null) {
            return true;
        } return false;
    }
    
    public void validarSesion() throws IOException{
        if (inicioSesion()) {
            cerrarSesion();
        }
    }
    public void cerrarSesion() throws IOException{
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        this.clave = "";
        this.email = "";
        ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        this.usuarioSesion = null;
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
    
}
