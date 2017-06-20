/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author jair3
 */
@Named(value = "controllerUsuario")
@ConversationScoped
public class ControllerUsuario extends ControllerApp {

    /**
     * Creates a new instance of ControllerUsuario
     */
    public ControllerUsuario() {
    }
    @EJB
    private UsuarioFacadeLocal usuarioFacade; //Facade
    private Usuario usuario; //Entidades
    private List<Usuario> listaUsuarios;
    
 
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();        
    }
    
    public List<Usuario> consultarUsuarios(){
        this.listaUsuarios = usuarioFacade.findAll();
        return listaUsuarios;
    }
    
    public String eliminarUsuario(Usuario usuario){
        usuarioFacade.remove(usuario);
        return "ConsultarUsuarios";
    }
    
    public String seleccionarUsuario(Usuario usuario) throws IOException{
            iniciarConversacion();
            this.usuario = usuario;
            return "ActualizarUsuario";
    }
        
    public String editarUsuario(){
        FacesContext fc = FacesContext.getCurrentInstance();
        if (usuario != null) {
            this.usuarioFacade.edit(usuario);
            finalizarConversacion();
            return "ConsultarUsuarios";
        } else {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha podido actualizar el usuario", "Intentelo de nuevo");
            fc.addMessage(null, m);
        }
        return "";
    }
    
    public String seleccionarCrear(){
        iniciarConversacion();
        return "CrearUsuario";
    }
    
    public String crearUsuario() throws ParseException{
        System.out.println("Creando");
        if (usuario != null) {
            
            Calendar datosFecha = new GregorianCalendar();
            int anio = datosFecha.get(Calendar.YEAR);
            int mes = datosFecha.get(Calendar.MONTH);
            int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = anio + "-" + mes + "-" + dia;
            Date fechaDate = null;
            
            fechaDate = formato.parse(strFecha);
            usuario.setFechaRegistro(fechaDate);
            usuarioFacade.create(usuario);
            ControllerMensaje.enviarMensajeInformacion("formRegistrarse", "Registor satisfactorio", "El usuario se ha registrado correctamente.");
            return "ConsultarUsuarios";
        } else {
            ControllerMensaje.enviarMensajeError("formRegistrarse", "No se han diligenciado los campos", "");

        }
        return"";
    }
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacade;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
