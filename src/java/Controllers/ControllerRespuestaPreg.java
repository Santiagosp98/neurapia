/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Respuestapreg;
import Facade.RespuestapregFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerRespuestaPreg")
@ConversationScoped
public class ControllerRespuestaPreg extends ControllerApp {

    /**
     * Creates a new instance of ControllerRespuestaPreg
     */
    public ControllerRespuestaPreg() {
    }

    @EJB
    RespuestapregFacadeLocal respuestapregFacade;
    Respuestapreg respuestapreg;
    List<Respuestapreg> listaRespuestapreg;
    private Respuestapreg respuestapregSeleccionado;

    @PostConstruct
    public void init() {
        respuestapreg = new Respuestapreg();
        listaRespuestapreg = respuestapregFacade.findAll();
    }

    public List<Respuestapreg> consultarRespuestapreg() {
        this.listaRespuestapreg = respuestapregFacade.findAll();
        return listaRespuestapreg;
    }

    public String crearRespuestapreg() {
        System.out.println("iniciando crear respuestas");
        this.respuestapregFacade.create(respuestapreg);        
        return "";
    }

    public RespuestapregFacadeLocal getRespuestapregFacade() {
        return respuestapregFacade;
    }

    public void setRespuestapregFacade(RespuestapregFacadeLocal respuestapregFacade) {
        this.respuestapregFacade = respuestapregFacade;
    }

    public Respuestapreg getRespuestapreg() {
        return respuestapreg;
    }

    public void setRespuestapreg(Respuestapreg respuestapreg) {
        this.respuestapreg = respuestapreg;
    }

    public Respuestapreg getRespuestapregSeleccionado() {
        return respuestapregSeleccionado;
    }

    public void setRespuestapregSeleccionado(Respuestapreg respuestapregSeleccionado) {
        this.respuestapregSeleccionado = respuestapregSeleccionado;
    }

}
