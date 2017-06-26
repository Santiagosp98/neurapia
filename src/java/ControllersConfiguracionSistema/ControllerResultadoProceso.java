/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Resultadoproceso;
import Facade.ResultadoprocesoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerResultadoProceso")
@ConversationScoped
public class ControllerResultadoProceso implements Serializable{
    
    private List<Resultadoproceso> listaResultadoProceso;
    
    @EJB
    private ResultadoprocesoFacade resultadoProcesoFacade;
    private Resultadoproceso resultadoProceso;
    
    @Inject
    private Conversation conversacion;
    
    @PostConstruct
    public void init(){
        resultadoProceso = new Resultadoproceso();        
    }
    
    public void iniciarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.begin();
        }
    }
    
    private void finalizarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.end();
        }
    }
    
    public String cancelar(){
        finalizarConversacion();
        return "ConsultarResultadoProceso";
    }
    
    public List<Resultadoproceso> getListaResultadoProceso() {
        listaResultadoProceso = resultadoProcesoFacade.findAll();
        return listaResultadoProceso;
    }
    
    public String selccionarResultadoProceso(Resultadoproceso resultadoproceso){
        iniciarConversacion();
        resultadoProceso = resultadoproceso;
        return "ActualizarResultadoProceso";
    }
    
    public String ActualizarResultadoProceso(){
        resultadoProcesoFacade.edit(resultadoProceso);
        return cancelar();
    }
    
    public void seleccionarEliminar(ResultadoprocesoFacade resultadoprocesoFacade ){
        iniciarConversacion();
        this.resultadoProcesoFacade = resultadoprocesoFacade;
    }
    
    public String EliminarResultadoProceso(){
        resultadoProcesoFacade.remove(resultadoProceso);
        finalizarConversacion();
        return "ConsultarResultadoProceso";
        
    }
    
    public void setListaResultadoProceso(List<Resultadoproceso> listaResultadoProceso) {
        this.listaResultadoProceso = listaResultadoProceso;
    }

    public ResultadoprocesoFacade getResultadoProcesoFacade() {
        return resultadoProcesoFacade;
    }

    public void setResultadoProcesoFacade(ResultadoprocesoFacade resultadoProcesoFacade) {
        this.resultadoProcesoFacade = resultadoProcesoFacade;
    }
    /**
     * Creates a new instance of ControllerResultadoProceso
     */
    public ControllerResultadoProceso() {
    }
    
    
}
