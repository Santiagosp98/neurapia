/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Actividad;
import Facade.ActividadFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author jair3
 */
@Named(value = "controllerActividad")
@ConversationScoped
public class ControllerActividad extends ControllerApp{

    /**
     * Creates a new instance of ControllerActividad
     */
    public ControllerActividad() {
    }
    @EJB
    ActividadFacadeLocal actividadFacade;
    Actividad actividad;
    List<Actividad> listaActividad;
    
    @PostConstruct
    public void init(){
        actividad = new Actividad();
        listaActividad = actividadFacade.findAll();
    }
    
    public List<Actividad> consultarActividad(){
        this.listaActividad = actividadFacade.findAll();
        return listaActividad;
    }
    
    public String selecionarEliminar(Actividad actividad){
        this.actividad = actividad;
        return "ModalEliminar";
    }
    
    public String eliminarActividad(Actividad actividad){
        this.actividadFacade.remove(actividad);
        return "ConsultarDatosSistema";
    }
    
    public String seleccionarActividad(Actividad actividad){
        iniciarConversacion();
        try {
            this.actividad = actividad;
            return "ActualizarDatosSistema";
        } catch (Exception e) {
            System.out.println(e);
        } return "";
    }
    
    public String crearActividad(){
        this.actividadFacade.create(actividad);
        return "ConsultarDatosSistema";
    }
    
    public String actualizarActividad(){
        this.actividadFacade.edit(actividad);
        finalizarConversacion();
        return "ConsultarDatosSistema";
    }

    public ActividadFacadeLocal getActividadFacadeLocal() {
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

    public void setListaActividad(List<Actividad> listaActividad) {
        this.listaActividad = listaActividad;
    }
    
    
    
}
