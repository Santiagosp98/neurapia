
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ControllersConfiguracionSistema.ControllerCaracteristicaMovilidad;
import ControllersConfiguracionSistema.ControllerResultadoProceso;
import Entities.Historialclinico;
import Entities.Usuario;
import Facade.HistorialclinicoFacadeLocal;
import Facade.UsuarioFacadeLocal;

import java.text.ParseException;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 * @author jair3
 */
@Named(value = "controllerHistorialClinico")
@ConversationScoped
public class ControllerHistorialClinico extends ControllerApp {

    @Inject
    ControllerUsuario controllerUsuario;
    @Inject
    ControllerAnamnesis ac;
    @Inject
    ControllerResultadoProceso rp;
    @Inject
    ControllerRespuestaActividad ra;
    @Inject
    ControllersConfiguracionSistema.ControllerResultadoObjetivo ro;
    @Inject
    ControllerCaracteristicaMovilidad cm;

    @EJB
    private HistorialclinicoFacadeLocal historialClinicoFacade;
    private Historialclinico historialClinico;
    private List<Historialclinico> listaHistorialClinico;
    private int edad;

    FacesContext fc = FacesContext.getCurrentInstance();

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    private List<Usuario> listaUsuarios;

    public ControllerHistorialClinico() {
    }

    @PostConstruct
    public void init() {
        historialClinico = new Historialclinico();
        listaHistorialClinico = historialClinicoFacade.findAll();
        usuario = new Usuario();
        listaUsuarios = usuarioFacade.findAll();
    }

    public List<Historialclinico> consultarHistorialClinico() {
        this.listaHistorialClinico = historialClinicoFacade.findAll();
        return listaHistorialClinico;
    }

    public String seleccionarHistorialclinico(Historialclinico historialClinico) {
        iniciarConversacion();
        try {
            this.historialClinico = historialClinico;
            int id = historialClinico.getIdHistorialClinico();
            ac.listaAnamnesisObtenidas();
            rp.listaResultadosProcesosObtenidos();
            ra.listarRespuestasActividad();
            rp.inicializarListaOrdenada();
            ro.listaResultadosObjetivo();
            return "informacionbasica.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error al seleccionar el hc: " + e.getMessage() + " " + e.getCause());
        }
        return "";
    }

    public void actualizarHistorialClinico(Integer idHistorialClinico) {
        try {
            this.historialClinico = historialClinicoFacade.find(idHistorialClinico);
            seleccionarHistorialclinico(this.historialClinico);
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage() + " " + e.getCause());
        }
    }

    public String editarHistorialClinico() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (historialClinico != null || !historialClinico.equals("")) {
                System.out.println(historialClinico.getCodEps().getIdEps());
                this.historialClinicoFacade.edit(historialClinico);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("basic-information-edited"), "");
                fc.addMessage(null, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String crearHistorialClinico() throws ParseException {
        iniciarConversacion();
        try {

            for (Historialclinico historialclinico : listaHistorialClinico) {
                if (this.historialClinico.getCodUsuario() != historialclinico.getCodUsuario()) {
                    if (this.historialClinico != null) {
                        System.out.println("Estamos creando un hsitorial clinico");
                        historialClinico.setFechaCreacion(new Date());
                        this.historialClinicoFacade.create(historialClinico);
                        iniciarConversacion();
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Historial Clínico añadido.", "");
                        fc.addMessage(null, message);
                        return "anamnesis?faces-redirect=true";
                    }
                    System.out.println("no se pudo crear");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Historial Clínico no ha podido ser añadido al sistema.", "");
                    fc.addMessage(null, message);
                    return "ConsultarUsuarios?faces-redirect=true";
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El paciente ya cuenta con un Historial Clínico.", "");
                    fc.addMessage(null, message);
                    System.out.println("Historial Existente, no se puede crear mas de un historial por paciente");
                    return "";
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear el hc: " + e.getMessage() + " " + e.getCause());
        }
        return "";
    }

    public void eliminarHistorialClinico(Historialclinico historialclinico) {
        try {
            this.historialClinico = historialclinico;
            historialClinicoFacade.remove(historialclinico);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Historial Clínico enviado a la papelera.", "");
            fc.addMessage(null, message);
            this.consultarHistorialClinico();
        } catch (Exception e) {
            System.out.println("Error Eliminar: " + e.getMessage() + " " + e.getCause());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Historial Clínico no ha podido ser suprimido.", "");
            fc.addMessage(null, message);
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Historialclinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(Historialclinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public List<Historialclinico> getListaHistorialClinico() {
        return listaHistorialClinico;
    }

    public void setListaHistorialClinico(List<Historialclinico> listaHistorialClinico) {
        this.listaHistorialClinico = listaHistorialClinico;
    }
}
