/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Historialclinico;
import Entities.Usuario;
import Facade.HistorialclinicoFacadeLocal;
import Facade.UsuarioFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author jair3
 */
@Named(value = "controllerHistorialClinico")
@ConversationScoped
public class ControllerHistorialClinico extends ControllerApp {

    /**
     * Creates a new instance of ControllerHistorialClinico
     */
    public ControllerHistorialClinico() {
    }
    
    @EJB
    HistorialclinicoFacadeLocal historialClinicoFacade;
    Historialclinico historialClinico;
    List<Historialclinico> listaHistorialClinico;
    
    @EJB
    UsuarioFacadeLocal usuarioFacade;
    Usuario usuario;
    
    @PostConstruct
    public void init(){
        historialClinico = new Historialclinico(); 
        listaHistorialClinico = historialClinicoFacade.findAll();
    }
    
    public List<Historialclinico> consultarHistorialClinico(){
        this.listaHistorialClinico = historialClinicoFacade.findAll();
        return listaHistorialClinico;
    }
    
    public String seleccionarHistorialclinico(Historialclinico historialClinico){
        iniciarConversacion();
        this.historialClinico = historialClinico;
        System.out.println(historialClinico.getCodUsuario().getPrimerNombre());
        return "ActualizarHistorialClinico?faces-redirect=true";
    }
    
    public void editarHistorialClinico(){
        if (historialClinico != null || !historialClinico.equals("")) {
            System.out.println(historialClinico.getCodEps().getIdEps());
            this.historialClinicoFacade.edit(historialClinico);
            siguienteHistorialClinico();
        }
    }
    
    public String siguienteHistorialClinico(){
     ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println(ec.getRequestContextPath());
        String page = "";        
        if (page == "ActualizarHistorialClinico") {
            return "Anamnesis?faces-redirect=true";
        }else if (page == "Anamnesis") {
            return "Movilidad?faces-redirect=true";
        }else if (page == "Movilidad" ) {
            return "ObjetivosTratamiento?faces-redirect=true";
        }else if (page == "ObjetivosTratamiento"){
            finalizarConversacion();
            return "ConsultarHistorialclinico?faces-redirect=true";
             
        }return "";
    }
    
    public String atrasHistorialClinico(){
        String page = "";
        if (page == "ObjetivosTratamiento" ) {
            return "ObjetivosTratamiento?faces-redirect=true";
        }else if (page == "ObjetivosTratamiento") {
            return "Movilidad?faces-redirect=true";
        }else if (page == "Movilidad") {
            return "Anamnesis?faces-redirect=true";
        }else if (page == "anamnesis") {
            finalizarConversacion();
            return "ConsultarHistorialclinico?faces-redirect=true";
        }return "";
    }
     
    public String crearHistorialClinico() throws ParseException{
        System.out.println("Estamos creando un hsitorial clinico");
        Calendar datosFecha = new GregorianCalendar();
            int anio = datosFecha.get(Calendar.YEAR);
            int mes = datosFecha.get(Calendar.MONTH);
            int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = anio + "-" + mes + "-" + dia;
            Date fechaDate = null;
            fechaDate = formato.parse(strFecha);
//            this.historialClinico.setCodUsuario(usuarioFacade.find(usuario.getIdUsuario()));
            historialClinico.setFechaCreacion(fechaDate);
        this.historialClinicoFacade.create(historialClinico);
        return "ConsultarHistorialClinico?faces-redirect=true";
    }
    
    public void eliminarHistorialClinico(Historialclinico historialclinico){
        historialClinicoFacade.remove(historialClinico);
        this.consultarHistorialClinico();
    }

    public HistorialclinicoFacadeLocal getHistorialClinicoFacadeLocal() {
        return historialClinicoFacade;
    }

    public void setHistorialClinicoFacadeLocal(HistorialclinicoFacadeLocal historialClinicoFacade) {
        this.historialClinicoFacade = historialClinicoFacade;
    }

    public Historialclinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(Historialclinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public List<Historialclinico> getListaHistorialClinico() {
        return listaHistorialClinico;
    }

    public void setListaHistorialClinico(List<Historialclinico> listaHistorialClinico) {
        this.listaHistorialClinico = listaHistorialClinico;
    }
    
}
