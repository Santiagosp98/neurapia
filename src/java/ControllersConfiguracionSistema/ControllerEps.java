/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Eps;
import Facade.EpsFacadeLocal;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Jair3
 */
@Named(value = "controllerEps")
@ConversationScoped
public class ControllerEps extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerEps
     */
    public ControllerEps() {
    }

    @EJB
    EpsFacadeLocal epsFacade;
    Eps eps;
    List<Eps> listaEps;

    private Eps epsSeleccionada;

    @PostConstruct
    public void init() {
        eps = new Eps();
        listaEps = epsFacade.findAll();
    }

    public List<Eps> consultarEps() {
        this.listaEps = epsFacade.findAll();
        return listaEps;
    }

    public String crearEps() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (eps.getNombreEps() != null && !eps.getNombreEps().equals("")) {
            this.epsFacade.create(eps);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("eps-created"), "");
            context.addMessage(null, message);
            return "";
        } else {

        }
        return "";
    }

    public String prepararEditar(Eps ep) {
        iniciarConversacion();
        epsSeleccionada = ep;
        return "editareps.xhtml?faces-redirect=true";
    }

    public String guardarCambios() {
        FacesContext fc1 = FacesContext.getCurrentInstance();
        if (epsSeleccionada.getNombreEps() != null || !epsSeleccionada.getNombreEps().equals("")) {
            epsFacade.edit(epsSeleccionada);
            finalizarConversacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("eps-edited"), null);
            fc1.getExternalContext().getFlash().setKeepMessages(true);
            fc1.addMessage("", message);
            return "registrosenelsistema.xhtml?faces-redirect=true";
        } else {
            FacesMessage m1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "campo no debe ser vacio", null);
            fc1.addMessage("prueba", m1);
            System.out.println("si");
            return "";
        }
    }

    public EpsFacadeLocal getEpsFacade() {
        return epsFacade;
    }

    public void setEpsFacadeLocal(EpsFacadeLocal epsFacade) {
        this.epsFacade = epsFacade;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public List<Eps> getListaEps() {
        return listaEps;
    }

    public void setListaEps(List<Eps> listaEps) {
        this.listaEps = listaEps;
    }

    public Eps getEpsSeleccionada() {
        return epsSeleccionada;
    }

    public void setEpsSeleccionada(Eps epsSeleccionada) {
        this.epsSeleccionada = epsSeleccionada;
    }

}

