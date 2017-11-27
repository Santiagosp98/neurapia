/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Objetivotratamiento;
import Facade.ObjetivotratamientoFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerObjetivoTratamiento")
@ConversationScoped
public class ControllerObjetivoTratamiento extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerObjetivoTratamiento
     */
    @EJB
    ObjetivotratamientoFacadeLocal objetivoTratamientoFacade;
    Objetivotratamiento objetivoTratamiento;
    List<Objetivotratamiento> listaObjetivoTratamiento;
    private Objetivotratamiento objetivoTratamientoSeleccionado;

    public void ControllerObjetivotratamiento() {
    }

    @PostConstruct
    public void init() {
        objetivoTratamiento = new Objetivotratamiento();
        listaObjetivoTratamiento = objetivoTratamientoFacade.findAll();
    }

    public List<Objetivotratamiento> consultarObjetivoTratamiento() {
        this.listaObjetivoTratamiento = objetivoTratamientoFacade.findAll();
        return listaObjetivoTratamiento;
    }

    public String SeleccionarObjetivoTratamiento(Objetivotratamiento objetivoTratamiento) {
        this.objetivoTratamiento = objetivoTratamiento;
        return "";
    }

    public String crearobjetivoTratamiento() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (objetivoTratamiento.getNombreObjetivo() != null && !objetivoTratamiento.getNombreObjetivo().equals("")) {
            this.objetivoTratamientoFacade.create(objetivoTratamiento);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("treatment-objective-created"), "");
            context.addMessage(null, message);
            return "";
        } else {

        }
        return "";
    }

    public String prepararEditarobjetivoTratamiento(Objetivotratamiento objt) {
        iniciarConversacion();
        objetivoTratamientoSeleccionado = objt;
        return "editarobjetivo.xhtml?faces-redirect=true";
    }

    public String guardarCambios() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (objetivoTratamientoSeleccionado.getNombreObjetivo() != null && !objetivoTratamientoSeleccionado.getNombreObjetivo().equals("")) {
            objetivoTratamientoFacade.edit(objetivoTratamientoSeleccionado);
            finalizarConversacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("treatment-objective-edited"), "");
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null ,message);
            return "registrosenelsistema.xhtml?faces-redirect=true";
        }
        return "";
    }

    public String eliminarObjetivoTratamiento() {
        this.objetivoTratamientoFacade.remove(objetivoTratamiento);
        return "ConsultarObjetivoTratamiento";
    }

    public ObjetivotratamientoFacadeLocal getObjetivoTratamientoFacade() {
        return objetivoTratamientoFacade;
    }

    public void setObjetivoTratamientoFacadeLocal(ObjetivotratamientoFacadeLocal objetivoTratamientoFacade) {
        this.objetivoTratamientoFacade = objetivoTratamientoFacade;
    }

    public Objetivotratamiento getObjetivoTratamiento() {
        return objetivoTratamiento;
    }

    public void setObjetivoTratamiento(Objetivotratamiento objetivoTratamiento) {
        this.objetivoTratamiento = objetivoTratamiento;
    }

    public List<Objetivotratamiento> getListaObjetivoTratamiento() {
        return listaObjetivoTratamiento;
    }

    public void setListaObjetivoTratamiento(List<Objetivotratamiento> listaObjetivoTratamiento) {
        this.listaObjetivoTratamiento = listaObjetivoTratamiento;
    }

    public Objetivotratamiento getObjetivoTratamientoSeleccionado() {
        return objetivoTratamientoSeleccionado;
    }

    public void setObjetivoTratamientoSeleccionado(Objetivotratamiento objetivoTratamientoSeleccionado) {
        this.objetivoTratamientoSeleccionado = objetivoTratamientoSeleccionado;
    }

}
