/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ControllersConfiguracionSistema.ControllerActividad;
import Entities.Historialclinico;
import Entities.Respuestaactividad;
import Facade.HistorialclinicoFacadeLocal;
import Facade.RespuestaactividadFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerRespuestaActividad")
@ConversationScoped
public class ControllerRespuestaActividad extends ControllerApp {
    
    @Inject
    private ControllerHistorialClinico hc;
    
    @Inject
    private ControllerActividad act;
    
    @EJB
    RespuestaactividadFacadeLocal respActFac;
    Respuestaactividad respuestaAct;    
    List<Respuestaactividad> listRespAct;
    List<Respuestaactividad> listRespActSeleccionadas;
    
    @EJB
    HistorialclinicoFacadeLocal hcf;
    List<Historialclinico> listaHc;
    List<Historialclinico> listaHcSeleccionado;
    
    public ControllerRespuestaActividad() {
    }
    
    @PostConstruct
    public void init(){
        this.respuestaAct = new Respuestaactividad();
        this.listRespAct = respActFac.findAll();
        this.listRespActSeleccionadas = new ArrayList();
        
        this.listaHc = hcf.findAll();
        this.listaHcSeleccionado = new ArrayList();
        iniciarConversacion();
    }
    
    public List consultarListaRespAct(){
        this.listRespAct = respActFac.findAll();
        System.out.println("Entrando en la consulta de Respuesa Actividad");
        return listRespAct;
    }
    
    public String redireccionaraReporteTratamiento(){
        return "ReporteTratamiento";
    }
    
    public Respuestaactividad getRespuestaAct() {
        return respuestaAct;
    }

    public void setRespuestaAct(Respuestaactividad respuestaAct) {
        this.respuestaAct = respuestaAct;
    }

    public RespuestaactividadFacadeLocal getRespActFac() {
        return respActFac;
    }

    public void setRespActFac(RespuestaactividadFacadeLocal respActFac) {
        this.respActFac = respActFac;
    }

    public List<Respuestaactividad> getListRespAct() {
        return listRespAct;
    }

    public void setListRespAct(List<Respuestaactividad> listRespAct) {
        this.listRespAct = listRespAct;
    }

    public List<Respuestaactividad> getListRespActSeleccionadas() {
        return listRespActSeleccionadas;
    }

    public void setListRespActSeleccionadas(List<Respuestaactividad> listRespActSeleccionadas) {
        this.listRespActSeleccionadas = listRespActSeleccionadas;
    }
    
    public List<Respuestaactividad> listarRespuestasActividad(){
        int id = hc.getHistorialClinico().getIdHistorialClinico();
        listRespActSeleccionadas = respActFac.respuestasActividadObtenidas(id);
        for (Respuestaactividad ra : listRespActSeleccionadas) {
            System.out.println("Respuestas Actividad: " + ra.getIdRespuestaActividad());
        }
        return listRespActSeleccionadas;
    }
    
    public void crearRespuestaActividad(){
        System.out.println("Estamos creando");
        System.out.println(hc.getHistorialClinico().getIdHistorialClinico());
        respuestaAct.setIdHistorialclinico(hc.getHistorialClinico());
        respuestaAct.setFechaActividad(new Date());
        this.respActFac.create(respuestaAct);        
        System.out.println("Respuesta id creada: " + respuestaAct.getIdRespuestaActividad());
        //init();
        hc.actualizarHistorialClinico(respuestaAct.getIdHistorialclinico().getIdHistorialClinico());
    }
    
    public void editarRespuestaActividad(){
        FacesContext fc = FacesContext.getCurrentInstance();
        iniciarConversacion();
        try {
            for (Respuestaactividad respuestaObtenida : listRespActSeleccionadas) {
                this.respuestaAct = respuestaObtenida;
                System.out.println(respuestaAct.getCodTipoActividad());
                System.out.println("Respuestas de actividad a editar: " + respuestaAct.getIdRespuestaActividad());
                System.out.println("codActividad a modificar: " + respuestaAct.getCodTipoActividad().getCodActividad().getIdActividad());
                respActFac.edit(this.respuestaAct);
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edicion Satisfactoriamente realizada.", "");
                fc.addMessage(null, message);
        } catch (Exception e) {
            System.out.println("Error al editar resp act: " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La edición de la respuesta de actividad no ha sido realizada.", "");
                fc.addMessage(null, message);
        }
        
    }
}
