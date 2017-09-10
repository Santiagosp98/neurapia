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
            claveAnterior = usuarioFacade.buscarEmail(cs.getUsuario().getCorreoElectronico()).getClaveUsuario();
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

        if (usuario != null) {
            Pattern regexp = Pattern.compile("\\d+");

            if (!regexp.matcher(usuario.getNumeroDocumento()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Numero de documento' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuario.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca una dirección de email válida.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null || usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()).equals(usuario)) {
                if (usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null || usuarioFacade.buscarEmail(usuario.getCorreoElectronico()).equals(usuario)) {
                    usuarioFacade.edit(usuario);
                    finalizarConversacion();
                    return "ConsultarUsuarios?faces-redirect=true";
                } else if (!(usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo ingresado ya está registrado.", "");
                    fc.addMessage(null, message);
                    return "";
                }
            } else if (!(usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de documento ingresado ya está registrado.", "");
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuarioEnSesion.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuarioEnSesion.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuarioEnSesion.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuarioEnSesion.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca una dirección de email válida.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuarioFacade.buscarEmail(usuarioEnSesion.getCorreoElectronico()) == null || usuarioFacade.buscarEmail(usuarioEnSesion.getCorreoElectronico()).equals(usuarioEnSesion)) {
                this.usuarioFacade.edit(usuarioEnSesion);
                return "miPerfil.xhtml?faces-redirect=true";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo ingresado ya está registrado en el sistema", "");
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Numero de documento' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoNombre()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo nombre' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");

            if (!regexp.matcher(usuario.getPrimerApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Primer apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]*$");

            if (!regexp.matcher(usuario.getSegundoApellido()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Segundo apellido' no admite carácteres que no pertenezcan al alfabeto.", "");
                fc.addMessage(null, message);
                return "";
            }

            regexp = Pattern.compile("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}");

            if (!regexp.matcher(usuario.getCorreoElectronico()).matches()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Introduzca una dirección de email válida.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuario.getClaveUsuario().length() < 8) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe contener mínimo 8 carácteres.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (!usuario.getClaveUsuario().equals(confirmarClave)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (usuarioFacade.buscarDocumento(usuario.getNumeroDocumento()) == null) {
                if (usuarioFacade.buscarEmail(usuario.getCorreoElectronico()) == null) {
                    if (usuarioEnSesion.getCodRol().getIdRol().equals(3)) {
                        rol.setIdRol(4);
                        usuario.setCodRol(rol);
                        usuario.setEstadoUsuario("Inactivo");
                        usuarioFacade.create(usuario);
                        return "ConsultarUsuarios.xhtml?faces-redirect=true";
                    } else {
                        usuarioFacade.create(usuario);
                        return "ConsultarUsuarios.xhtml?faces-redirect=true";
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email ingresado ya está registrado.", "");
                    fc.addMessage(null, message);
                    return "";
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de documento ingresado ya está registrado.", "");
                fc.addMessage(null, message);
                return "";
            }
        }

        return "";
    }


    public void registrarUsuario() throws ParseException {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (usuario != null) {
            System.out.println("Registrando :  ");
            Calendar calendar = new GregorianCalendar();
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = anio + "-" + mes + "-" + dia;
            Date fechaDate;
            fechaDate = formato.parse(fecha);
            usuario.setFechaRegistro(fechaDate);
            usuario.setEstadoUsuario("Activo");
            Rol rol = new Rol();
            rol.setIdRol(4);
            usuario.setCodRol(rol);
            if (usuario.getClaveUsuario().equals(this.confirmarClave)) {
                usuarioFacade.create(usuario);
            } else {
                System.out.println("No se creo");
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido crear el usuario", "Clave y confirmar clave diferentes");
                fc.addMessage(null, m);
            }
        }
        usuario = null;
        recargar();
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
                return "ActualizarHistorial";
            }
        }
        return "CrearUsuario.xhtml?faces-redirect=true";
    }

    public String cambiarContrasena() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Usuario usuarioEnSesion = cs.getUsuario();

        if (usuario != null) {
            if (claveAnterior.equals(usuarioEnSesion.getClaveUsuario())) {
                if (usuarioEnSesion.getClaveUsuario().length() < 8) {
                    if (confirmarClave.equals(usuarioEnSesion.getClaveUsuario())) {
                        usuarioFacade.edit(usuarioEnSesion);
                    } else {
                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Las contraseñas no coinciden.", "");
                        facesContext.addMessage(null, m);
                        return "";
                    }
                } else {
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "La contraseña debe contener mínimo 8 carácteres.", "");
                    facesContext.addMessage(null, m);
                    return "";
                }
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña incorrecta.", "");
                facesContext.addMessage(null, m);
                return "";
            }

        }

        return "miPerfil.xhtml";
    }

}
