/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jair3
 */
public class ControllerMensaje {
    
    public static void enviarMensajeError(String idClient, String summary, String detail){
        enviarMensajeComun(FacesMessage.SEVERITY_ERROR,idClient, summary, detail);
    }
    public static void enviarMensajeErrorGlobal(String summary, String detail){
        enviarMensajeComun(FacesMessage.SEVERITY_ERROR,null, summary, detail);
    }
    
    public static void enviarMensajeInformacion(String idClient, String summary, String detail){
        enviarMensajeComun(FacesMessage.SEVERITY_INFO,idClient, summary, detail);
    }
    public static void enviarMensajeInformacionGlobal(String summary, String detail){
        enviarMensajeComun(FacesMessage.SEVERITY_INFO, null, summary, detail);
    }
    
    private static void enviarMensajeComun(FacesMessage.Severity severity, String idClient, String summary, String detail){
        FacesMessage fm = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(idClient, fm);
    }
}
