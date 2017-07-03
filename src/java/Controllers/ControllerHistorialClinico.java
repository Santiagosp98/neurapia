/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Anamnesis;
import Entities.Historialclinico;
import Entities.Usuario;
import Entities.Dolor;
import Facade.AnamnesisFacadeLocal;
import Facade.DolorFacadeLocal;
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
    private HistorialclinicoFacadeLocal historialClinicoFacade;
    private Historialclinico historialClinico;
    private List<Historialclinico> listaHistorialClinico;
    private int edad;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    
    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    private Anamnesis anamnesis;
    private List<Anamnesis> listAnamnesis;
    
    @EJB
    private DolorFacadeLocal dolorFacade;
    private Dolor dolor;
    private List<Dolor> listaDolor;

    public HistorialclinicoFacadeLocal getHistorialClinicoFacade() {
        return historialClinicoFacade;
    }

    public void setHistorialClinicoFacade(HistorialclinicoFacadeLocal historialClinicoFacade) {
        this.historialClinicoFacade = historialClinicoFacade;
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

    public AnamnesisFacadeLocal getAnamnesisFacade() {
        return anamnesisFacade;
    }

    public void setAnamnesisFacade(AnamnesisFacadeLocal anamnesisFacade) {
        this.anamnesisFacade = anamnesisFacade;
    }

    public Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }

    public List<Anamnesis> getListAnamnesis() {
        return listAnamnesis;
    }

    public void setListAnamnesis(List<Anamnesis> listAnamnesis) {
        this.listAnamnesis = listAnamnesis;
    }

    public DolorFacadeLocal getDolorFacade() {
        return dolorFacade;
    }

    public void setDolorFacade(DolorFacadeLocal dolorFacade) {
        this.dolorFacade = dolorFacade;
    }

    public Dolor getDolor() {
        return dolor;
    }

    public void setDolor(Dolor dolor) {
        this.dolor = dolor;
    }

    public List<Dolor> getListaDolor() {
        return listaDolor;
    }

    public void setListaDolor(List<Dolor> listaDolor) {
        this.listaDolor = listaDolor;
    }
    

    @PostConstruct
    public void init(){
        historialClinico = new Historialclinico(); 
        anamnesis = new Anamnesis(); 
        dolor = new Dolor();
        listaHistorialClinico = historialClinicoFacade.findAll();
        listAnamnesis = anamnesisFacade.findAll();
        listaDolor = dolorFacade.findAll();
    }
    
    public List<Historialclinico> consultarHistorialClinico(){
        this.listaHistorialClinico = historialClinicoFacade.findAll();
        return listaHistorialClinico;
    }
    
    public String seleccionarHistorialclinico(Historialclinico historialClinico){
        iniciarConversacion();
        this.historialClinico = historialClinico;
        for (Anamnesis  anamnesis : listAnamnesis) {
            if (historialClinico.getIdHistorialClinico() == anamnesis.getCodHistorialClinico().getIdHistorialClinico()) {
                this.anamnesis = anamnesis;
                    for (Dolor  dolor : listaDolor) {
                        if (anamnesis.getCodDolor().getIdDolor() == dolor.getIdDolor()) {
                            System.out.println("Estoy seleccionando el dolor: " + dolor.getIdDolor());
                            this.dolor = dolor;
                        }
                    }
                System.out.println("estoy seleccionando anamnesis: " + anamnesis.getIdAnamnesis());
                return "ActualizarHistorialClinico?faces-redirect=true";
            }
        }
        System.out.println(historialClinico.getCodUsuario().getPrimerNombre());
        return "ActualizarHistorialClinico?faces-redirect=true";
    }
    
    public void editarHistorialClinico(){
        if (historialClinico != null || !historialClinico.equals("")){
            System.out.println(historialClinico.getCodEps().getIdEps());
            this.historialClinicoFacade.edit(historialClinico);
        }
    }
    
    public void editarAnamnesis(){
        System.out.println("Estamos actualizando la anamnesis");
        this.anamnesisFacade.edit(anamnesis);
        editarDolor();
    }
    
    public void editarDolor(){
            System.out.println("Estamos editando Dolor");
            this.dolorFacade.edit(dolor);
    }
    
    public String selectAnamnesis(){
        return "Anamnesis";
    }
    public String selectMovilidad(){
        return "Movilidad";
    }
    
    public String selectInformacionBasica(){
        return "ActualizarHistorialClinico";
    }   

    public String selectObjetivoTratamiento(){
        return "ObjetivoTratamiento";
    } 
    
    public String selectReporteTratamiento(){
        return "ReporteTratamiento";
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
