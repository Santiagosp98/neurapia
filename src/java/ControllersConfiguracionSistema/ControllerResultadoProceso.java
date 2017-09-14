/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Resultadoproceso;
import Facade.ResultadoprocesoFacadeLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    
    private List<List<String>> datos;
    
    @Inject 
    Controllers.ControllerHistorialClinico hc;
    
    @EJB
    private ResultadoprocesoFacadeLocal resultadoProcesoFacade;
    private Resultadoproceso resultadoProceso;
    private List<Resultadoproceso> resultadosObtenidos;
    
    private Map<String, List<Resultadoproceso>> mapa;
   
    @PostConstruct
    public void init(){
        resultadoProceso = new Resultadoproceso();
        resultadosObtenidos = new ArrayList();
    }
    
    public void inicializarListaOrdenada() {
        mapa = new HashMap<>();
        String nomTemp = "";        
        String proceso = "";
        try {   
            for (Resultadoproceso r : resultadosObtenidos) {
                int cont = 0;
                if (nomTemp.equals("") || !r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo().equals(nomTemp) || mapa.containsKey(nomTemp)) {                    
                    nomTemp = r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo();                    
                    if (!mapa.containsKey(nomTemp)) {
                        mapa.put(nomTemp, new ArrayList<Resultadoproceso>());
                    }                    
                }
                proceso = r.getCodProceso().getNombreProceso();  
                System.out.println("Add al mapa: " + r.getIdResultadoProceso() + " Key: " + nomTemp + " Proceso: " + proceso + " res: " + r.getResultadoProceso());
                if (mapa.get(nomTemp).isEmpty() || !mapa.get(nomTemp).get(cont).getCodProceso().getNombreProceso().equals(proceso)) {
                    System.out.println("mapa vacio");
                     mapa.get(nomTemp).add(r); 
                }else if (mapa.get(nomTemp).get(cont).getCodProceso().getNombreProceso().equals(proceso)) {
                    System.out.println("nuevo mapa:");
                    mapa.put(nomTemp, new ArrayList<Resultadoproceso>());
                    mapa.get(nomTemp).add(r);
                }                               
                cont ++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Resultadoproceso> listaResultadosProcesosObtenidos(){
        int id = hc.getHistorialClinico().getIdHistorialClinico(); //debemos modificarlo por codAnamnesis
        resultadosObtenidos = resultadoProcesoFacade.listarResultadosProcesosObtenidos(id);
        for (Resultadoproceso r : resultadosObtenidos) {
            System.out.println("Resultados proceso Obtenido: " + r.getIdResultadoProceso());
        }
        return resultadosObtenidos;
    }
    
    public String selccionarResultadoProceso(Resultadoproceso resultadoproceso){
        iniciarConversacion();
        resultadoProceso = resultadoproceso;
        return "ActualizarResultadoProceso";
    }
    
    public String crearResultadoProceso(){
        try {
            if (resultadoProceso != null) {
                resultadoProceso.setCodHistorialClinico(hc.getHistorialClinico()); //debemos modificarlo por anamnesis.getAnamnesis();
                resultadoProcesoFacade.create(resultadoProceso);      
                return "movilidad.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("Error crear Resultado Proceso: " + e.getMessage());
        }        
        return "movilidad.xhtml?faces-redirect=true";
    }   
        
    public String ActualizarResultadoProceso(){
        try {
            if (resultadoProceso != null) {
                resultadoProcesoFacade.edit(resultadoProceso);
                return "movilidad.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar Resultado Proceso: " + e.getMessage());
        }
        return  "";
    }
    
    public void seleccionarEliminarLocal(ResultadoprocesoFacadeLocal resultadoprocesoFacade ){
        iniciarConversacion();
        this.resultadoProcesoFacade = resultadoprocesoFacade;
    }
    
    public String EliminarResultadoProceso(){
        try {
            resultadoProcesoFacade.remove(resultadoProceso);
            finalizarConversacion();
            return "ConsultarResultadoProceso";            
        } catch (Exception e) {
            System.out.println("Error al Eliminar el Resultado Proceso: " + e.getMessage());
        }
        return "";
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
        
    public Map<String, List<Resultadoproceso>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, List<Resultadoproceso>> mapa) {
        this.mapa = mapa;
    }

    public List<Resultadoproceso> getResultadosObtenidos() {
        return resultadosObtenidos;
    }

    public void setResultadosObtenidos(List<Resultadoproceso> resultadosObtenidos) {
        this.resultadosObtenidos = resultadosObtenidos;
    }

    public List<Resultadoproceso> getListaResultadoProceso() {
        listaResultadoProceso = resultadoProcesoFacade.findAll();
        return listaResultadoProceso;
    }
}   
