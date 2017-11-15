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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import java.util.*;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author Santiago
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
    private String contrasenaAntigua;
    private String claveAnterior;
    private String estado;
    private int cantidadUsuarios;
    private int usuariosInhabilitados;
    private int fisioterapeutasRegistrados;

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
        cantidadUsuarios = usuarioFacade.count();
        usuariosInhabilitados = usuarioFacade.cantidadUsuariosPorEstado("Inactivo");
        fisioterapeutasRegistrados = usuarioFacade.countCantidadUsuariosPorRol(3);
        if (cs.getUsuario() != null) {
            contrasenaAntigua = usuarioFacade.buscarEmail(cs.getUsuario().getCorreoElectronico()).getClaveUsuario();
        }
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
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("delete-user-action"), null);
                    fc.addMessage(null, m);
                } else {
                    FacesMessage m1 = new FacesMessage(FacesMessage.SEVERITY_WARN, msg.getString("request-denied"), "");
                    fc.addMessage(null, m1);
                }
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("delete-logged-user"), "");
                fc.addMessage(null, m);
            }
        } catch (Exception e) {
        }

    }

    public String seleccionarUsuario(Usuario usuario) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        iniciarConversacion();
        this.usuario = usuario;
        if (!usuario.getCodRol().getIdRol().equals(1)) {
            estado = usuario.getEstadoUsuario();
            System.out.println(estado);
            return "editarusuario?faces-redirect=true";
        } else {
            finalizarConversacion();
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, msg.getString("request-denied"), "");
            fc.addMessage(null, m);
        }
        return "";
    }

    public String editarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (usuario != null) {
            Pattern regexp = Pattern.compile("\\d+");

            if (!regexp.matcher(usuario.getNumeroDocumento()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("document-number") + " " + msg.getString("not-numeric"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + msg.getString("first-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("middle-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("second-surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuario.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("mail"), "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null || usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()).equals(usuario)) {
                if (usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null || usuarioFacade.buscarEmail(usuario.getCorreoElectronico()).equals(usuario)) {
                    usuarioFacade.edit(usuario);
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("edit-user-action"), "");
                    fc.addMessage(null, message);
                    fc.getExternalContext().getFlash().setKeepMessages(true);
                    finalizarConversacion();
                    return "consultarusuarios?faces-redirect=true";
                } else if (!(usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-email"), "");
                    fc.addMessage(null, message);
                    return "";
                }
            } else if (!(usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-document"), "");
                fc.addMessage(null, message);
                return "";
            }
        }
        return "";
    }

    public String editarPerfil() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario usuarioEnSesion = cs.getUsuario();
        if (usuario != null) {
            Pattern regexp;
            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuarioEnSesion.getPrimerNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("first-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuarioEnSesion.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("middle-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuarioEnSesion.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuarioEnSesion.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("second-surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuarioEnSesion.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("mail"), "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuarioFacade.buscarEmail(usuarioEnSesion.getCorreoElectronico()) == null || usuarioFacade.buscarEmail(usuarioEnSesion.getCorreoElectronico()).equals(usuarioEnSesion)) {
                this.usuarioFacade.edit(usuarioEnSesion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("profile-edited"), "");
                fc.addMessage(null, message);
                fc.getExternalContext().getFlash().setKeepMessages(true);
                return "editarperfil.xhtml?faces-redirect=true";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-email"), "");
                fc.addMessage(null, message);
                return "";
            }
        }

        return "";
    }

    public String seleccionarCrear() {
        iniciarConversacion();
        return "CrearUsuario";
    }

    public String crearUsuario() throws ParseException {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (usuario != null) {
            Usuario usuarioEnSesion = cs.getUsuario();

            Calendar calendar = Calendar.getInstance();
            usuario.setFechaRegistro(calendar.getTime());

            Pattern regexp = Pattern.compile("\\d+");

            if (!regexp.matcher(usuario.getNumeroDocumento()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("document-number") + " " + msg.getString("not-numeric"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("first-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("middle-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("second-surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuario.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("mail"), "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuario.getClaveUsuario().length() < 8) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("password-length"), "");
                fc.addMessage(null, message);
                return "";
            }

            if (!usuario.getClaveUsuario().equals(confirmarClave)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("equal-pass"), "");
                fc.addMessage(null, message);
                return "";
            }


            if (usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null) {
                if (usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null) {
                    if (usuarioEnSesion.getCodRol().getIdRol().equals(3)) {
                        rol.setIdRol(4);
                        usuario.setCodRol(rol);
                        usuario.setEstadoUsuario("Inactivo");
                        usuario.setIngresos(usuario.getIngresos());
                        usuario.setUltimaSesion(calendar.getTime());
                        usuarioFacade.create(usuario);
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("create-user-action"), "");
                        fc.addMessage(null, message);
                        fc.getExternalContext().getFlash().setKeepMessages(true);
                        return "crearusuarios.xhtml?faces-redirect=true";
                    } else {
                        usuario.setIngresos(usuario.getIngresos());
                        usuario.setUltimaSesion(calendar.getTime());
                        usuarioFacade.create(usuario);
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("create-user-action"), "");
                        fc.addMessage(null, message);
                        fc.getExternalContext().getFlash().setKeepMessages(true);
                        return "crearusuario.xhtml?faces-redirect=true";
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-email"), "");
                    fc.addMessage(null, message);
                    return "";

                }

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-document"), "");
                fc.addMessage(null, message);
                return "";
            }
        }

        return "";
    }


    public void registrarUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (usuario != null) {

            Pattern regexp = Pattern.compile("\\d+");

            if (!regexp.matcher(usuario.getNumeroDocumento()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:numero-documento");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("document-number") + " " + msg.getString("not-numeric"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerNombre()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:primer-nombre");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("first-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoNombre()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:segundo-nombre");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("middle-name") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerApellido()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:primer-apellido");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoApellido()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:segundo-apellido");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("the-field") + " " + msg.getString("second-surname") + " " + msg.getString("not-alphabet"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuario.getCorreoElectronico()).matches()) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:correo");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("mail"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            if (usuario.getClaveUsuario().length() < 8) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:clave-usuario");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("password-length"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            if (!usuario.getClaveUsuario().equals(confirmarClave)) {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:confirmar-clave");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("equal-pass"), "");
                fc.addMessage(component.getClientId(fc), message);
                return;
            }

            if (usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null) {
                if (usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null) {
                    Calendar calendar = Calendar.getInstance();
                    usuario.setFechaRegistro(calendar.getTime());
                    usuario.setEstadoUsuario("Activo");
                    Rol rol = new Rol();
                    rol.setIdRol(4);
                    usuario.setCodRol(rol);
                    usuario.setIngresos(usuario.getIngresos() + 1);
                    usuario.setUltimaSesion(calendar.getTime());
                    usuarioFacade.create(usuario);
                    UIComponent root = fc.getViewRoot();
                    UIComponent component = root.findComponent("registro:correo");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("account-registered"), "");
                    fc.addMessage(component.getClientId(fc), message);
                } else {
                    UIComponent root = fc.getViewRoot();
                    UIComponent component = root.findComponent("registro:correo");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-email"), "");
                    fc.addMessage(component.getClientId(fc), message);
                }

            } else {
                UIComponent root = fc.getViewRoot();
                UIComponent component = root.findComponent("registro:numero-documento");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("existing-document"), "");
                fc.addMessage(component.getClientId(fc), message);
            }
        }
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

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(int cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }

    public long getUsuariosInhabilitados() {
        return usuariosInhabilitados;
    }

    public void setUsuariosInhabilitados(int usuariosInhabilitados) {
        this.usuariosInhabilitados = usuariosInhabilitados;
    }

    public int getFisioterapeutasRegistrados() {
        return fisioterapeutasRegistrados;
    }

    public void setFisioterapeutasRegistrados(int fisioterapeutasRegistrados) {
        this.fisioterapeutasRegistrados = fisioterapeutasRegistrados;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }


    public String prepararCrearHistorial(Usuario usuario) {
        iniciarConversacion();
        this.usuario = usuario;
        for (Historialclinico historialClinico : hc.getListaHistorialClinico()) {
            if (historialClinico.getCodUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                hc.seleccionarHistorialclinico(historialClinico);
                return "informacionbasica.xhtml?faces-redirect=true";
            }
        }
        return "nuevohistorialclinico.xhtml?faces-redirect=true";
    }

    public void cambiarContrasena() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Usuario usuarioEnSesion = cs.getUsuario();

        if (usuario != null) {
            if (contrasenaAntigua.equals(claveAnterior)) {
                System.out.println(usuarioEnSesion.getClaveUsuario());
                if (usuarioEnSesion.getClaveUsuario().length() > 7) {
                    if (confirmarClave.equals(usuarioEnSesion.getClaveUsuario())) {
                        usuarioFacade.edit(usuarioEnSesion);
                        UIComponent root = facesContext.getViewRoot();
                        UIComponent component = root.findComponent("cambiar-contrasena:nueva-contrasena");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("modify-password-action"), "");
                        facesContext.addMessage(component.getClientId(facesContext), message);
                    } else {
                        UIComponent root = facesContext.getViewRoot();
                        UIComponent component = root.findComponent("cambiar-contrasena:nueva-contrasena");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("equal-pass"), "");
                        facesContext.addMessage(component.getClientId(facesContext), message);
                    }
                } else {
                    UIComponent root = facesContext.getViewRoot();
                    UIComponent component = root.findComponent("cambiar-contrasena:nueva-contrasena");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("password-length"), "");
                    facesContext.addMessage(component.getClientId(facesContext), message);
                }
            } else {
                UIComponent root = facesContext.getViewRoot();
                UIComponent component = root.findComponent("cambiar-contrasena:contrasena-anterior");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getString("password-not-match"), "");
                facesContext.addMessage(component.getClientId(facesContext), message);
            }

        }
    }

}
