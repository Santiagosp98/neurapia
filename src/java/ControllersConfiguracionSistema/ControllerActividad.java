/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
import Entities.Actividad;
import Facade.ActividadFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author jair3
 */
@Named(value = "controllerActividad")
@ConversationScoped
public class ControllerActividad extends ControllerApp {

    /**
     * Creates a new instance of ControllerActividad
     */
    @EJB
    ActividadFacadeLocal actividadFacade;
    Actividad actividad;
    List<Actividad> listaActividad;
    private Actividad actividadSeleccionada;

    public ControllerActividad() {
    }

    @PostConstruct
    public void init() {
        actividad = new Actividad();
        listaActividad = actividadFacade.findAll();
    }

    public List<Actividad> consultarActividad() {
        this.listaActividad = actividadFacade.findAll();
        return listaActividad;
    }

    public String crearActividad() {
        if (actividad.getNombreActividad() != null && !actividad.getNombreActividad().equals("")) {
            this.actividadFacade.create(actividad);
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {
        }
        return "";
    }

    public String prepararEditar(Actividad act) {
        iniciarConversacion();
        actividadSeleccionada = act;
        return "editarActividades?faces-redirect=true";
    }

    public String guardarCambios() {
        if (actividadSeleccionada.getNombreActividad() != null && !actividadSeleccionada.getNombreActividad().equals("")) {
            actividadFacade.edit(actividadSeleccionada);
            finalizarConversacion();
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {

        }
        return "";
    }

    public ActividadFacadeLocal getActividadFacade() {
        return actividadFacade;
    }

    public void setActividadFacadeLocal(ActividadFacadeLocal actividadFacade) {
        this.actividadFacade = actividadFacade;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public List<Actividad> getListaActividad() {
        return listaActividad;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }

}
