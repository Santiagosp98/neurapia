/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Permiso;
import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    public void init() {
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

    public String iniciarSesion() {
        System.out.println("Estamos en Sesión");
        FacesContext fc = FacesContext.getCurrentInstance();
        if (email != null && !email.equals("") && clave != null && !clave.equals("")) {
            usuario = usuarioFacade.iniciarSesion(email, clave);
            if (usuario != null) {
                if (usuario.getEstadoUsuario().equals("Activo")) {
                    System.out.println("El usuario ingresado es: " + usuario.getPrimerNombre());
                    usuario.setIngresos(usuario.getIngresos() + 1);
                    Calendar calendar = Calendar.getInstance();
                    usuario.setUltimaSesion(calendar.getTime());
                    usuarioFacade.edit(usuario);
                    return "usuario/perfil.xhtml?faces-redirect=true";
                } else {
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("inactive-user"), "");
                    fc.addMessage(null, m);
                }

            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("incorrect-email-or-password"), "");
                fc.addMessage(null, m);
            }
        } else {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("all-fields-required"), "");
            fc.addMessage(null, m);
        }
        return "";
    }

    public void cerrarSesion() {
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
            System.out.println(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ControllerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean inicioSesion() {
        System.out.println("Host name: " + hostName());
        System.out.println("Validando");
        return (usuario != null);
    }

    public void validarSesion() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (inicioSesion()) {
            if (true) {
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String url = req.getRequestURL().toString();
                if (!consultarPermiso(url)) {
                    ec.redirect(hostName() + "usuario/perfil.xhtml?faces-redirect=true");
                }
            } else {
                cerrarSesion();
            }
        }
    }

    public boolean consultarPermiso(String url) {
        if (url.endsWith("historialclinico")) {
            System.out.println("Tiene permiso");
            return true;
        }
        for (Permiso p : usuario.getCodRol().getPermisoList()) {
            if (p.getUrl() != null && url.endsWith(p.getUrl())) {
                System.out.println("No tiene permiso");
                return false;
            }
        }
        return true;
    }

    public boolean accesoSuperAdmin() {
        System.out.println("SuperAdmin");
        System.out.println(usuario.getCodRol().getNombreRol());
        if (usuario.getCodRol().getNombreRol().equals("Super Administrador") || usuario.getCodRol().getNombreRol().equals("Administrador")) {
            System.out.println(usuario.getCodRol().getIdRol());
            return false;
        }
        return true;
    }

    public boolean accesoUsuario() {
        System.out.println("Usuario");
        System.out.println(usuario.getCodRol().getNombreRol());
        if (usuario.getCodRol().getNombreRol().equals("Usuario")) {
            System.out.println("Ingreso igual usuario");
            return false;
        }
        return true;

    }

    public boolean accesoFisioterapeuta() {
        System.out.println("Fisioterapeuta");
        System.out.println(usuario.getCodRol().getNombreRol());
        if (usuario.getCodRol().getNombreRol().equals("Fisioterapeuta")) {
            System.out.println("Ingreso igual a fisioterapeuta");
            return false;
        }
        return true;
    }

    public void validarAccesoFisioterapeuta() {
        System.out.println("Validando");
        if (accesoFisioterapeuta() == false) {
            System.out.println("Validado");
            redireccionPaciente();
        }
    }

    public void validarAccesoUsuario() {
        System.out.println("Validando");
        if (accesoUsuario() == false) {
            System.out.println("Validado");
            redireccionPaciente();
        }
    }

    public void validarAccesoSuperAdmin() {
        System.out.println("Hola validando");
        if (accesoSuperAdmin() == false) {
            System.out.println("Validado");
            redireccionPaciente();
        }
    }

    public void redireccionPaciente() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            System.out.println(hostName() + "usuario/perfil.xhtml");
            ec.redirect(hostName() + "usuario/perfil.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ControllerSession.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
