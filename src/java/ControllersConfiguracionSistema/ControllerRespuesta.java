/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerHistorialClinico;
import Entities.Respuesta;
import Facade.RespuestaFacadeLocal;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerRespuesta")
@ConversationScoped
public class ControllerRespuesta extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerRespuesta
     */
    public ControllerRespuesta() {
    }
    
    @Inject ControllerHistorialClinico hc;
    

    @EJB
    RespuestaFacadeLocal respuestaFacade;
    Respuesta respuesta;
    List<Respuesta> listaRespuesta;
    private Respuesta respuestaSeleccionada;
    
    @PostConstruct
    public void init(){
        respuesta = new Respuesta();
        listaRespuesta=respuestaFacade.findAll();        
    }
    
     public List<Respuesta> consultarRespuesta(){
        this.listaRespuesta=respuestaFacade.findAll();
        return listaRespuesta;
    }
    
    public String crearRespuesta() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (respuesta.getNombreRespuesta() != null && !respuesta.getNombreRespuesta().equals("")) {
            this.respuestaFacade.create(respuesta);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("response-created"), "");
            context.addMessage(null, message);
            return "";
        } else {
        }
        return "";
    }

    public String prepararEditar(Respuesta resp) {
        iniciarConversacion();
        respuestaSeleccionada = resp;
        return "editarrespuesta.xhtml?faces-redirect=true";
    }

    public String guardarCambios() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (respuestaSeleccionada.getNombreRespuesta()!= null && !respuestaSeleccionada.getNombreRespuesta().equals("")) {
            respuestaFacade.edit(respuestaSeleccionada);
            finalizarConversacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("response-edited"), "");
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, message);
            return "registrosenelsistema.xhtml?faces-redirect=true";
        } else {

        }
        return "";
    }

    public RespuestaFacadeLocal getRespuestaFacade() {
        return respuestaFacade;
    }

    public void setRespuestaFacadeLocal(RespuestaFacadeLocal respuestaFacade) {
        this.respuestaFacade = respuestaFacade;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public List<Respuesta> getListaRespuesta() {
        return listaRespuesta;
    }

    public void setListaRespuesta(List<Respuesta> listaRespuesta) {
        this.listaRespuesta = listaRespuesta;
    }

    public Respuesta getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(Respuesta respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }           
    
    public List seleccionRespuestas(){
        ArrayList lista = new ArrayList();
        lista.add("Dolor escala analoga visual");
        lista.add("Espasmos");
        lista.add("Fuerza");
        lista.add("Movilidad");      
        return lista;
    }

}
