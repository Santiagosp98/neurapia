/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.ResultadoFinal;
import Facade.ResultadoFinalFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerResultadoFinal")
@ConversationScoped
public class ControllerResultadoFinal extends ControllerApp{

    /**
     * Creates a new instance of ControllerResultadoFinal
     */
    public ControllerResultadoFinal() {
    }
    
    @EJB
    private ResultadoFinalFacadeLocal rffl;
    private ResultadoFinal resFinal;
    private List<ResultadoFinal> listaResFinal;
    private List<ResultadoFinal> lista = null;
    
    @Inject
    ControllerAnamnesis cA;
    
    @Inject
    ControllerRespuestaActividad cResAct;
    
    @PostConstruct
    public void init(){
        this.listaResFinal = rffl.findAll(); //Consulta todos los valores de la BD
        this.resFinal = new ResultadoFinal();
    }

    public ResultadoFinal getResFinal() {
        return resFinal;
    }

    public void setResFinal(ResultadoFinal resFinal) {
        this.resFinal = resFinal;
    }

    public List<ResultadoFinal> getListaResFinal() {
        return listaResFinal;
    }

    public void setListaResFinal(List<ResultadoFinal> listaResFinal) {
        this.listaResFinal = listaResFinal;
    }  

    public List<ResultadoFinal> getLista() {
        return lista;
    }

    public void setLista(List<ResultadoFinal> lista) {
        this.lista = lista;
    }
    
    public String  crearRespuestaFinal(){
        iniciarConversacion();
        if(resFinal != null && cA.getAnamnesis() != null && cResAct.getRespuestaAct() != null){
            System.out.println("Estamos creando el resultado final");
            System.out.println("Anamnesis n° " + cA.getAnamnesis());
            System.out.println("respeusta act n° " + resFinal.getCodRespuestaActividad());
            resFinal.setCodAnamnesis(cA.getAnamnesis());
            resFinal.setFecha(new Date());
            rffl.create(resFinal);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Resultado Final ha sido creado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj); 
            System.out.println("Respuesta Final creada Exitosamente");
            return "objetivos.xhtml?faces-redirect=true";
        }else{
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Resultado Final no ha sido creado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj); 
            System.out.println("El resultado final no ha sido creado");
        }
        return "";
    }
    
    public void SeleccionarResultadoFinal(ResultadoFinal resultFinal){
        iniciarConversacion();
        if(resultFinal != null){
           this.resFinal = resultFinal; 
        }     
        
    }
    
    public void eliminarResultadoFinal(ResultadoFinal resfFinal){
        try {
            rffl.remove(resfFinal);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Resultado Final ha sido eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);            
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Resultado Final no ha sido eliminado exitosamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj); 
            System.out.println(e.getMessage());
        }        
        
    }
    
    public List<ResultadoFinal> consultarResultadosFinales(){                
        int id = cA.getAnamnesis().getIdAnamnesis();
        lista = rffl.consultarResultadosPorAnamnesis(id);   
        return lista;
    }

    
    
    
    
}
