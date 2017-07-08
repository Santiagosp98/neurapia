/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerUsuario")
@ConversationScoped
public class ControllerUsuario extends ControllerApp {

    /**
     * Creates a new instance of ControllerUsuario
     */
    public ControllerUsuario() {
    }

    @Inject
    private ControllerSession cs;

    @EJB
    private UsuarioFacadeLocal usuarioFacade; //Facade
    private Usuario usuario; //Entidades
    private List<Usuario> listaUsuarios;
    private String confirmarClave;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuarios = usuarioFacade.findAll();
    }

    public List<Usuario> consultarUsuarios() {
        this.listaUsuarios = usuarioFacade.findAll();
        return listaUsuarios;
    }

    public void prepararEliminarUsuario(Usuario usuario){
        iniciarConversacion();
        this.usuario = usuario;
        System.out.println(usuario.getFullNameUsuario());
    }
    
    public void eliminarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Usuario uS = cs.getUsuario();
            System.out.println("Voy a eliminar el usuario: " + usuario.getPrimerNombre());
            System.out.println("Inicio sesion: " + uS.getPrimerNombre());
            if (uS.getIdUsuario().intValue() != usuario.getIdUsuario()) {
                System.out.println(usuario.getCodRol());
                if (!usuario.getCodRol().equals(1)) {
                    usuarioFacade.remove(usuario);
                    finalizarConversacion();
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ha sido eliminado correctamente", null);
                    fc.addMessage(null, m);
                } else {
                    FacesMessage m1 = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al eliminar", "No se puede eliminar un Super Administrador");
                    fc.addMessage(null, m1);
                }
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar", "No se puede eliminar a sí mismo");
                fc.addMessage(null, m);
            }
        } catch (Exception e) {
        }
        
    }

    public String seleccionarUsuario(Usuario usuario) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        iniciarConversacion();
        this.usuario = usuario;
        if (Objects.equals(cs.getUsuario().getIdUsuario(), this.usuario.getIdUsuario())) {
            System.out.println(cs.getUsuario().getPrimerNombre() + " es igual a: " + this.usuario.getPrimerNombre());
            return "ActualizarUsuario?faces-redrect=true";
        }else
        if (!usuario.getCodRol().equals(1)) {            
            return "ActualizarUsuario?faces-redrect=true";
        } else {

            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No puede modificar un Super administrador", "No tiene permisos para esta acción");
            fc.addMessage(null, m);
        }
        return "";
    }

    public String editarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("editar Usuario");
        if (usuario != null) {
            this.usuarioFacade.edit(usuario);
            finalizarConversacion();
            return "ConsultarUsuarios?faces-redrect=true";

        } else {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario", "Intentelo de nuevo");
            fc.addMessage(null, m);
        }
        return "";
    }

    public String seleccionarCrear() {
        iniciarConversacion();
        return "CrearUsuario";
    }

    public String crearUsuario() throws ParseException {
        if (usuario != null) {

            Calendar datosFecha = new GregorianCalendar();
            int anio = datosFecha.get(Calendar.YEAR);
            int mes = datosFecha.get(Calendar.MONTH);
            int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = anio + "-" + mes + "-" + dia;
            Date fechaDate = null;
            fechaDate = formato.parse(strFecha);
            usuario.setFechaRegistro(fechaDate);
            if (usuario.getClaveUsuario().equals(this.confirmarClave)) {
                usuarioFacade.create(usuario);
                return "ConsultarUsuarios";
            } 

        } else {

        }
        return "";
    }
    public String crearUsuarioindex() throws ParseException {
        if (usuario != null) {
            System.out.println("indexxxxx");
            Calendar datosFecha = new GregorianCalendar();
            int anio = datosFecha.get(Calendar.YEAR);
            int mes = datosFecha.get(Calendar.MONTH);
            int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = anio + "-" + mes + "-" + dia;
            Date fechaDate = null;
            fechaDate = formato.parse(strFecha);
            usuario.setFechaRegistro(fechaDate);
            if (usuario.getClaveUsuario().equals(this.confirmarClave)) {
                usuarioFacade.create(usuario);
                return "index.html";
            } 

        } else {
                        System.out.println("indexxxxx");

        }
        return "";
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

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacade;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacade) {
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

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    public String prepararCrearHistorial(Usuario usuario) {
        iniciarConversacion();
        this.usuario = usuario;
        return "CrearUsuario.xhtml?faces-redirect=true";
    }

}
