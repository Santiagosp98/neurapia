/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Resultadoproceso;
import Facade.ResultadoprocesoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jair3
 */
@Named(value = "controllerResultadoProceso")
@ConversationScoped
public class ControllerResultadoProceso extends Controllers.ControllerApp{
    
    private List<Resultadoproceso> listaResultadoProceso;
    private List listaIdCaracteristicas;
    private List listaProcesos;
    private List resultadosObtenidos;
    
    private List<List<String>> datos;
    
    @Inject 
    Controllers.ControllerHistorialClinico hc;
    
    @EJB
    private ResultadoprocesoFacadeLocal resultadoProcesoFacade;
    private Resultadoproceso resultadoProceso;
   
    @PostConstruct
    public void init(){
        resultadoProceso = new Resultadoproceso();

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
    
    public void crearResultadoProceso(){
        System.out.println("Hc número: " + hc.getHistorialClinico().getIdHistorialClinico());
        resultadoProceso.setCodHistorialClinico(hc.getHistorialClinico());
        resultadoProcesoFacade.create(resultadoProceso);
    }
    
    public String ActualizarResultadoProceso(){
        resultadoProcesoFacade.edit(resultadoProceso);
        return cancelar();
    }
    
    public void seleccionarEliminarLocal(ResultadoprocesoFacadeLocal resultadoprocesoFacade ){
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

    public ResultadoprocesoFacadeLocal getResultadoProcesoFacade() {
        return resultadoProcesoFacade;
    }

    public void setResultadoProcesoFacade(ResultadoprocesoFacadeLocal resultadoProcesoFacade) {
        this.resultadoProcesoFacade = resultadoProcesoFacade;
    }
    /**
     * Creates a new instance of ControllerResultadoProceso
     */
    public ControllerResultadoProceso() {
    }

    public Resultadoproceso getResultadoProceso() {
        return resultadoProceso;
    }

    public void setResultadoProceso(Resultadoproceso resultadoProceso) {
        this.resultadoProceso = resultadoProceso;
    }
}   
