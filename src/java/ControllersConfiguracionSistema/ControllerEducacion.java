/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Educacion;
import Facade.EducacionFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jair3
 */
@Named(value = "controllerEducacion")
@ConversationScoped
public class ControllerEducacion extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerEducacion
     */
    public ControllerEducacion() {
    }

    @EJB
    EducacionFacadeLocal educacionFacade;
    Educacion educacion;
    List<Educacion> listaEducacion;
    private Educacion educacionSeleccionada;

    @PostConstruct
    public void init() {
        educacion = new Educacion();
        listaEducacion = educacionFacade.findAll();
    }

    public List<Educacion> consultarEducacion() {
        this.listaEducacion = educacionFacade.findAll();
        return listaEducacion;
    }

    public String eliminarEducacion(Educacion eduacion) {
        this.educacionFacade.remove(eduacion);
        return "RegistroInformacion";
    }

    public String crearEducacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (educacion.getTipoEducacion() != null && !educacion.getTipoEducacion().equals("")) {
            this.educacionFacade.create(educacion);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("degree-created"), "");
            context.addMessage(null, message);
            return "";
        } else {

        }
        return "";
    }

    public String prepararEditar(Educacion edu) {
        iniciarConversacion();
        educacionSeleccionada = edu;
        return "editareducacion.xhtml?faces-redirect=true";
    }

    public String guardarCambios() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (educacionSeleccionada.getTipoEducacion() != null || !educacionSeleccionada.getTipoEducacion().equals("")) {
            educacionFacade.edit(educacionSeleccionada);
            finalizarConversacion();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getString("degree-edited"), "");
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, message);
            return "registrosenelsistema.xhtml?faces-redirect=true";
        }
        return "";
    }

    public EducacionFacadeLocal getEducacionFacade() {
        return educacionFacade;
    }

    public void setEducacionFacadeLocal(EducacionFacadeLocal educacionFacade) {
        this.educacionFacade = educacionFacade;
    }

    public Educacion getEducacion() {
        return educacion;
    }

    public void setEducacion(Educacion educacion) {
        this.educacion = educacion;
    }

    public List<Educacion> getListaEducacion() {
        return listaEducacion;
    }

    public void setListaEducacion(List<Educacion> listaEducacion) {
        this.listaEducacion = listaEducacion;
    }

    public Educacion getEducacionSeleccionada() {
        return educacionSeleccionada;
    }

    public void setEducacionSeleccionada(Educacion educacionSeleccionada) {
        this.educacionSeleccionada = educacionSeleccionada;
    }

}

