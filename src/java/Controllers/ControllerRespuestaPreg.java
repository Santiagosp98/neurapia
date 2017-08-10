/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Prediagnostico;
import Entities.Respuestapreg;
import Facade.CuestionarioFacadeLocal;
import Facade.PrediagnosticoFacadeLocal;
import Facade.RespuestapregFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerRespuestaPreg")
@ConversationScoped
public class ControllerRespuestaPreg extends ControllerApp {

    @Inject
    private ControllerSession cs;
    
    @EJB 
    private PrediagnosticoFacadeLocal pfl;

    @EJB
    private CuestionarioFacadeLocal cfl;
    
    @EJB
    private RespuestapregFacadeLocal respuestapregFacade;
    private Respuestapreg respuestapreg;
    private List<Respuestapreg> listaRespuestapreg;
    private Respuestapreg respuestapregSeleccionado;
    
    /**
     * Creates a new instance of ControllerRespuestaPreg
     */
    public ControllerRespuestaPreg() {
    }

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
        Prediagnostico pre = new Prediagnostico();
        pre.setCodUsuario(cs.getUsuario());
        pre.setCodRespuestaPreg(respuestapreg);
        pre.setFecha(new Date());
        pre.setResultadoPrediagnostico("Pendiente");
        pre.setCodCuestionario(cfl.find(1));
        pfl.create(pre);
        return "ConsultarPrediagnostico?faces-redirect=true";
    }
    
    public String aceptar(){
        
        return "ConsultarPrediagnostico?faces-redirect=true";
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
