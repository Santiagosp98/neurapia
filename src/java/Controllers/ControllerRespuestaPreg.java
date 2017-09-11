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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
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
        FacesContext facesContext = FacesContext.getCurrentInstance();

        Pattern regexp;
        regexp = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚ]+$");
        int invalid = 0;

        if (!regexp.matcher(respuestapreg.getResp1()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta1", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp2()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta2", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp3()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta3", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp4()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta4", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp5()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta5", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp6()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta6", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp7()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta7", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp8()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta8", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp9()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta9", m);
            invalid++;
        }

        if (!regexp.matcher(respuestapreg.getResp10()).matches()) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permiten carácteres que no pertenezcan al alfabeto.", "");
            facesContext.addMessage("pregunta10", m);
            invalid++;
        }

        if (invalid > 0) {
            return "";
        }

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

    public String aceptar() {

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
