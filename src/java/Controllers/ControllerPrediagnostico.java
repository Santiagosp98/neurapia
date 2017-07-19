/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cuestionario;
import Entities.Prediagnostico;
import Entities.Respuestapreg;
import Entities.Usuario;
import Facade.CuestionarioFacadeLocal;
import Facade.PrediagnosticoFacadeLocal;
import Facade.RespuestapregFacadeLocal;
import Facade.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Jeisson Diaz
 */
@Named(value = "controllerPrediagnostico")
@ConversationScoped
public class ControllerPrediagnostico extends ControllerApp {

    /**
     * Creates a new instance of ControllerPrediagnostico
     */
    public ControllerPrediagnostico() {
    }

    @Inject
    ControllerUsuario controllerUsuario;
    ControllerCuestionario controllerCuestionario;
    ControllerRespuestaPreg controllerRespuestaPreg;

    @EJB
    PrediagnosticoFacadeLocal prediagnosticoFacade;
    Prediagnostico prediagnostico;
    List<Prediagnostico> listaPrediagnistico;
    private Prediagnostico prediagnosticoSeleccionado;
    
    @EJB
    CuestionarioFacadeLocal cuestionarioFacade;
    Cuestionario cuestionario;
    List<Cuestionario> listaCuestionario;

    @EJB
    RespuestapregFacadeLocal respuestapregFacade;
    Respuestapreg respuestapreg;
    List<Respuestapreg> listaRespuestapreg;

    @EJB
    UsuarioFacadeLocal usuarioFacade; //Facade
    Usuario usuario; //Entidades
    List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        this.prediagnostico = new Prediagnostico();
        this.cuestionario = new Cuestionario();
        this.respuestapreg = new Respuestapreg();
        this.usuario = new Usuario();
        this.listaPrediagnistico = prediagnosticoFacade.findAll();
    }

    public List<Prediagnostico> consultarPrediagnostico() {
        this.listaPrediagnistico = prediagnosticoFacade.findAll();
        return listaPrediagnistico;

    }

    public String seleccionarPrediagnostico(Prediagnostico prediagnostico) {
        iniciarConversacion();
        this.prediagnostico = prediagnostico;
        return "ActualizarPrediagnostico?faces-redirect=true";
    }

    public String guardarCambios() {
        prediagnosticoFacade.edit(prediagnostico);
        finalizarConversacion();
        return "ConsultarPrediagnostico?faces-redirect=true";
    }

    public String seleccionarCrear() {
        System.out.println("HOLA");
        iniciarConversacion();
        return "";
    }

    public String crearPrediagnostico() throws ParseException {
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
        prediagnostico.setFecha(fechaDate);
        prediagnostico.setCodUsuario(controllerUsuario.getUsuario());
        prediagnostico.setCodCuestionario(controllerCuestionario.getListaCuestionario().get(1));
        prediagnostico.setCodRespuestaPreg(controllerRespuestaPreg.getRespuestapreg());
        this.prediagnosticoFacade.create(prediagnostico);
        return "ConsultarPrediagnostico?faces-redirect=true";
    }  
    
    public String cancelarActualizar(){
        finalizarConversacion();
        return "ConsultarPrediagnostico?faces-redirect=true";
    }

    public PrediagnosticoFacadeLocal getPrediagnosticoFacade() {
        return prediagnosticoFacade;
    }

    public void setPrediagnosticoFacade(PrediagnosticoFacadeLocal prediagnosticoFacade) {
        this.prediagnosticoFacade = prediagnosticoFacade;
    }

    public Prediagnostico getPrediagnostico() {
        return prediagnostico;
    }

    public void setPrediagnostico(Prediagnostico prediagnostico) {
        this.prediagnostico = prediagnostico;
    }

    public List<Prediagnostico> getListaPrediagnistico() {
        return listaPrediagnistico;
    }

    public void setListaPrediagnistico(List<Prediagnostico> listaPrediagnistico) {
        this.listaPrediagnistico = listaPrediagnistico;
    }

    public CuestionarioFacadeLocal getCuestionarioFacade() {
        return cuestionarioFacade;
    }

    public void setCuestionarioFacade(CuestionarioFacadeLocal cuestionarioFacade) {
        this.cuestionarioFacade = cuestionarioFacade;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public List<Cuestionario> getListaCuestionario() {
        return listaCuestionario;
    }

    public void setListaCuestionario(List<Cuestionario> listaCuestionario) {
        this.listaCuestionario = listaCuestionario;
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

    public List<Respuestapreg> getListaRespuestapreg() {
        return listaRespuestapreg;
    }

    public void setListaRespuestapreg(List<Respuestapreg> listaRespuestapreg) {
        this.listaRespuestapreg = listaRespuestapreg;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Prediagnostico getPrediagnosticoSeleccionado() {
        return prediagnosticoSeleccionado;
    }

    public void setPrediagnosticoSeleccionado(Prediagnostico prediagnosticoSeleccionado) {
        this.prediagnosticoSeleccionado = prediagnosticoSeleccionado;
    }

    
}
