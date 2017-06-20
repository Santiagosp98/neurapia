/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Resultadoproceso;
import Facade.ResultadoprocesoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author jair3
 */
@Named(value = "controllerResultadoProceso")
@ConversationScoped
public class ControllerResultadoProceso extends ControllerApp{
    
    
    
    @EJB
    private ResultadoprocesoFacadeLocal resultadoProcesoFacade;
    private Resultadoproceso resultadoProceso;
    private List<Resultadoproceso> listaResultadoProceso;
    
    @PostConstruct
    public void init(){
        resultadoProceso = new Resultadoproceso();        
    }
    
    public List<Resultadoproceso> ListaResultadoProceso() {
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
        finalizarConversacion();
        return "ConsultarDatosSistema";
    }
    
    public String seleccionarEliminar(Resultadoproceso resultadoproceso ){
        iniciarConversacion();
        this.resultadoProceso = resultadoproceso;
        return "ActualizarResultadoProceso";
    }
    
    public String EliminarResultadoProceso(Resultadoproceso resultadoProceso){
        resultadoProcesoFacade.remove(resultadoProceso);
        return "ConsultarDatosSistema";
        
    }
    
    public void setListaResultadoProceso(List<Resultadoproceso> listaResultadoProceso) {
        this.listaResultadoProceso = listaResultadoProceso;
    }

    public ResultadoprocesoFacadeLocal getResultadoProcesoFacadeLocal() {
        return resultadoProcesoFacade;
    }

    public void setResultadoProcesoFacadeLocal(ResultadoprocesoFacadeLocal resultadoProcesoFacade) {
        this.resultadoProcesoFacade = resultadoProcesoFacade;
    }
    /**
     * Creates a new instance of ControllerResultadoProceso
     */
    public ControllerResultadoProceso() {
    }
    
    
}
