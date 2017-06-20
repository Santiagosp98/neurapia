/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Historialclinico;
import Facade.HistorialclinicoFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerHistorialClinico")
@SessionScoped
public class ControllerHistorialClinico implements Serializable {

    /**
     * Creates a new instance of ControllerHistorialClinico
     */
    public ControllerHistorialClinico() {
    }
    
    @EJB
    HistorialclinicoFacadeLocal historialClinicoFacade;
    Historialclinico historialClinico;
    List<Historialclinico> listaHistorialClinico;
    
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
        this.historialClinico = historialClinico;
        return "CrearHistorialClinico";
    }
    
    public String editarHistorialClinico(Historialclinico historialClinico){
        String pag;
        if (historialClinico != null) {
            this.historialClinicoFacade.edit(historialClinico);
            pag = "ConsultarHistorialclinico";
        }else{
            pag = "";
        }
        return pag;
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
