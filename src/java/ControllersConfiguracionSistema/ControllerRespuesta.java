/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Respuesta;
import Facade.RespuestaFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

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
        if (respuesta.getNombreRespuesta() != null && !respuesta.getNombreRespuesta().equals("")) {
            this.respuestaFacade.create(respuesta);
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {
        }
        return "";
    }

    public String prepararEditar(Respuesta resp) {
        iniciarConversacion();
        respuestaSeleccionada = resp;
        return "EditarRespuesta?faces-redirect=true";
    }

    public String guardarCambios() {
        if (respuestaSeleccionada.getNombreRespuesta()!= null && !respuestaSeleccionada.getNombreRespuesta().equals("")) {
            respuestaFacade.edit(respuestaSeleccionada);
            finalizarConversacion();
            return "ConsultarDatosSistema?faces-redirect=true";
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
    
}
