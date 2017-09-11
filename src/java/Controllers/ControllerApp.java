/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jair3
 */
@Named(value = "controllerVarios")
@ConversationScoped
public class ControllerApp implements Serializable {

    public ControllerApp() {
        
    }
    
    @Inject
    private Conversation conversacion;
    
    public void iniciarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.begin();
        }
    }
    
    public void finalizarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.end();
        }
    }
    
    public String cancelar(){
        finalizarConversacion();
        return "";
    }
    
    public String cancelarConfiguracionSistema(){
        finalizarConversacion();
        return "ConsultarDatosSistema.xhtml";
    }

    public Conversation getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversation conversacion) {
        this.conversacion = conversacion;
    }
    
    public void redireccionar(String url) throws IOException {
        
        url = this.hostName()+ url + ".xhtml";
        
        if(!"".equals(url)) {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            
        }
         
    }
    
/*    public String hostName() {
        
        String serverName = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        
        String name = "http://";
        
        if("localhost".equals(serverName)) {
            
            name += serverName  + ":8080/Proyecto/";
        
        } else {
            
            name += serverName;
           
        }
        return name; 
    }*/

        //generar una url completa para redireccionar y validar si es localhost o el dominio del sitio
    public String hostName() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        int port = fc.getExternalContext().getRequestServerPort();
        String name =  fc.getExternalContext().getRequestScheme() + "://" + fc.getExternalContext().getRequestServerName() + ":" + port + fc.getExternalContext().getRequestContextPath() + "/";
        return name;
        
    }

    public void recargar() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(((HttpServletRequest) externalContext.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redirecMovilidad(){
        return "Movilidad?faces-redirect=true";
    }
    
    public String selectAnamnesis() {
        return "Anamnesis?faces-redirect=true";
    }

    public String selectMovilidad() {
        return "Movilidad?faces-redirect=true";
    }

    public String selectInformacionBasica() {
        return "ActualizarHistorialClinico?faces-redirect=true";
    }

    public String selectObjetivoTratamiento() {
        return "Objetivos?faces-redirect=true";
    }

    public String selectReporteTratamiento() {
        return "ReporteTratamiento?faces-redirect=true";
    }
    
}
