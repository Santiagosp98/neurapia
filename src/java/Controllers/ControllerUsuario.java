/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Historialclinico;
import Entities.Rol;
import Entities.Usuario;
import Facade.RolFacadeLocal;
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
 * @author Bryan
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

    @Inject
    private ControllerHistorialClinico hc;

    @EJB
    private UsuarioFacadeLocal usuarioFacade; //Facade
    private Usuario usuario; //Entidades
    private List<Usuario> listaUsuarios;
    private String confirmarClave;
    private String claveAnterior;
    private String estado;

    @EJB
    private RolFacadeLocal rolFacade;
    private List<Rol> listaRol;
    private Rol rol;
    private Rol rol2;

    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacade.findAll();
        usuario = new Usuario();
        listaRol = rolFacade.findAll();
        rol = new Rol();
        rol2 = new Rol();
    }

    public List<Usuario> consultarUsuarios() {
        Usuario uS = cs.getUsuario();
        System.out.println("rol " + uS.getCodRol());
        if (uS.getCodRol() != null) {
            if (uS.getCodRol().getNombreRol().equals("Super Administrador")) {
                this.listaUsuarios = usuarioFacade.findAll();
            } else if (uS.getCodRol().getNombreRol().equals("Administrador")) {
                rol.setIdRol(4);
                rol2.setIdRol(3);
                try {
                    System.out.println("Listando por Paciente y Fisioterapeuta");
                    System.out.println("N° " + listaUsuarios.size());
                    this.listaUsuarios = usuarioFacade.listaUsuariosPorRolDoble(rol, rol2);
                    return listaUsuarios;
                } catch (Exception e) {
                }
            } else if (uS.getCodRol().getNombreRol().equals("Fisioterapeuta")) {
                rol.setIdRol(4);
                try {
                    System.out.println("Estamos listando los pacientes");
                    System.out.println("" + listaUsuarios.size());
                    this.listaUsuarios = usuarioFacade.listaUsuariosPorRol(rol);
                    return listaUsuarios;
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        } else {
            System.out.println("Campo nulo getCodRol");
        }

        return listaUsuarios;
    }

    public List<Usuario> listarPacientes() {
        rol.setIdRol(4);
        try {
            System.out.println("Estamos listando los pacientes");
            System.out.println("" + listaUsuarios.size());
            this.listaUsuarios = usuarioFacade.listaUsuariosPorRol(rol);
            return listaUsuarios;
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaUsuarios;
    }

    public void prepararEliminarUsuario(Usuario usuario) {
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
        if (!usuario.getCodRol().equals(1)) {
            estado = usuario.getEstadoUsuario();
            System.out.println(estado);
            return "ActualizarUsuario?faces-redrect=true";
        } else {

            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No puede modificar un Super administrador", "No tiene permisos para esta acción");
            fc.addMessage(null, m);
        }
        return "";
    }

    public String editarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario uS = cs.getUsuario();
        System.out.println("editar Usuario");
        if (usuario != null) {

            if (usuario.getEstadoUsuario().equals(estado) || 1 == uS.getCodRol().getIdRol()) {
                if (usuario.getClaveUsuario().equals(this.confirmarClave) || 1 != uS.getCodRol().getIdRol()) {
                    this.usuarioFacade.edit(usuario);
                    finalizarConversacion();
                    return "ConsultarUsuarios?faces-redrect=true";
                } else {
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario", "Clave y confirmar clave diferentes");
                    fc.addMessage(null, m);
                }
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario;", " El estado de usuario solo se puede cambiar por Administrador");
                fc.addMessage(null, m);
            }

        } else {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario", "Intentelo de nuevo");
            fc.addMessage(null, m);
        }
        return "";
    }

    public String editarMiUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario uS = cs.getUsuario();
        System.out.println("editar Usuario");
        if (usuario != null) {

            if (uS.getClaveUsuario().equals(this.confirmarClave)) {
                this.usuarioFacade.edit(uS);
                return "miPerfil?faces-redrect=true";
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario", "Clave y confirmar clave diferentes");
                fc.addMessage(null, m);
            }

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
            Usuario uS = cs.getUsuario();

            Calendar datosFecha = new GregorianCalendar();
            int anio = datosFecha.get(Calendar.YEAR);
            int mes = datosFecha.get(Calendar.MONTH);
            int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = anio + "-" + mes + "-" + dia;
            Date fechaDate = null;
            fechaDate = formato.parse(strFecha);
            usuario.setFechaRegistro(fechaDate);
            if(uS.getCodRol().getIdRol()==3){
                if (usuario.getClaveUsuario().equals(this.confirmarClave)) {
                    rol.setIdRol(4);
                    usuario.setCodRol(rol);
                    usuario.setEstadoUsuario("Inactivo");
                    usuarioFacade.create(usuario);
                    return "ConsultarUsuarios";
                }
            }else{
                if (usuario.getClaveUsuario().equals(this.confirmarClave)) {
                    usuarioFacade.create(usuario);
                    return "ConsultarUsuarios";
                }
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
        for (Historialclinico historialClinico : hc.getListaHistorialClinico()) {
            if (historialClinico.getCodUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                hc.seleccionarHistorialclinico(historialClinico);
                return "ActualizarHistorial";
            }
        }
        return "CrearUsuario.xhtml?faces-redirect=true";
    }

}
