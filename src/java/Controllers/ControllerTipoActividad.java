/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Actividad;
import Entities.Respuestaactividad;
import Entities.Tipoactividad;
import Facade.ActividadFacadeLocal;
import Facade.TipoactividadFacadeLocal;
import org.jboss.weld.context.http.Http;

import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jair3
 */
@Named(value = "controllerTipoActividad")
@ConversationScoped
public class ControllerTipoActividad extends ControllerApp {

    /**
     * Creates a new instance of ControllerTipoActividad
     */

    @Inject
    private ControllerRespuestaActividad resAct;
    private Respuestaactividad respuestaactividad;

    @EJB
    private TipoactividadFacadeLocal tipoActividadFacade;
    private Tipoactividad tipoActividad;
    private List<Tipoactividad> listaTipoActividades;


    @EJB
    private ActividadFacadeLocal actividadFacade;
    private Actividad actividad;
    private List<Actividad> listaActividad;


    public ControllerTipoActividad() {
    }

    @PostConstruct
    public void init() {
        this.tipoActividad = new Tipoactividad();
        listaTipoActividades = tipoActividadFacade.findAll();

        this.actividad = new Actividad();
        listaActividad = actividadFacade.findAll();
    }

    public TipoactividadFacadeLocal getTipoActividadFacade() {
        return tipoActividadFacade;
    }

    public void setTipoActividadFacade(TipoactividadFacadeLocal tipoActividadFacade) {
        this.tipoActividadFacade = tipoActividadFacade;
    }

    public Tipoactividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(Tipoactividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public List getListaTipoActividades() {
        return listaTipoActividades;
    }

    public void setListaTipoActividades(List listaTipoActividades) {
        this.listaTipoActividades = listaTipoActividades;
    }

    public Respuestaactividad getRespuestaactividad() {
        return respuestaactividad;
    }

    public void setRespuestaactividad(Respuestaactividad respuestaactividad) {
        this.respuestaactividad = respuestaactividad;
    }

    public List<Tipoactividad> listadoTipoActividades() {
        this.listaTipoActividades = tipoActividadFacade.findAll();
        Iterator<Tipoactividad> tipoactividadIterator = listaTipoActividades.iterator();
        while (tipoactividadIterator.hasNext()) {
            Tipoactividad tipoactividad = tipoactividadIterator.next();
            for (Respuestaactividad respuestaactividad:
                 resAct.listarRespuestasActividad()) {
                if (Objects.equals(tipoactividad.getIdTipoActividad(), respuestaactividad.getCodTipoActividad().getIdTipoActividad()) && Objects.equals(respuestaactividad.getEstado(), "En proceso")) {
                    tipoactividadIterator.remove();
                }
            }
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getRequestURI();
        if (uri.endsWith("editaractividad.xhtml")) {
            listaTipoActividades.add(respuestaactividad.getCodTipoActividad());
        }
        return listaTipoActividades;
    }

    public ActividadFacadeLocal getActividadFacade() {
        return actividadFacade;
    }

    public void setActividadFacade(ActividadFacadeLocal actividadFacade) {
        this.actividadFacade = actividadFacade;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public List getListaactividad() {
        return listaActividad;
    }

    public void setListaactividad(List listaactividad) {
        this.listaActividad = listaactividad;
    }

    public void crearTipoActividad() {
        try {
            System.out.println("Estamos creando un tipo de actividad");
            this.tipoActividadFacade.create(tipoActividad);
            System.out.println("Actividad creada con id: " + tipoActividad.getIdTipoActividad());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editarTipoActividad() {
        try {
            if (tipoActividadFacade != null) {
                System.out.println("Estamos editando los tipos de actividad");
                this.tipoActividadFacade.edit(tipoActividad);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public List seleccionTiposdeActividad() {
        ArrayList lista = new ArrayList();
        lista.add("Medios Físicos");
        lista.add("Electroterapia");
        lista.add("Ejercicios prueba");
        return lista;
    }



}
