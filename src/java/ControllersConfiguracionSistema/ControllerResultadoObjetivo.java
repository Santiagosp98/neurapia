/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerHistorialClinico;
import Entities.Resultadoobjetivo;
import Facade.ResultadoobjetivoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerResultadoObjetivo")
@ConversationScoped
public class ControllerResultadoObjetivo extends Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerResultadoObjetivo
     */
    @EJB
    private ResultadoobjetivoFacadeLocal rofl;
    private Resultadoobjetivo resObjetivo;
    private List<Resultadoobjetivo> listaResObj;
    private List<Resultadoobjetivo> listaSeleccionados;
    
    private Map<String, List<Resultadoobjetivo>> mapa;
    
    @Inject ControllerHistorialClinico hc;
    
    public ControllerResultadoObjetivo() {
    }
    
    @PostConstruct
    public void init(){
         resObjetivo = new Resultadoobjetivo();
         listaResObj = rofl.findAll();  
         listaSeleccionados = new ArrayList();
    }
    
    public void crearResultadoObjetivo(){
        resObjetivo.setCodHistorialClinico(hc.getHistorialClinico());
        rofl.create(resObjetivo);
    }
    
    public void agregarlistaOrdenada(){
        listaSeleccionados = hc.getListobjetivosbtenidos();
        mapa = new HashMap<>();
        String nomTemp = ""; 
        try {
            for (Resultadoobjetivo r : listaSeleccionados) {
                if (nomTemp.equals("") || !r.getCodObjetivoTratamiento().getNombreObjetivo().equals(nomTemp)) {
                    nomTemp = r.getCodObjetivoTratamiento().getNombreObjetivo();
                    if (!mapa.containsKey(nomTemp)) {                        
                        mapa.put(nomTemp, new ArrayList<>());
                    }                    
                }
                System.out.println("Agregando al mapa: " + r.getIdResultadoObjetivo() + " key: " + nomTemp);
                mapa.get(nomTemp).add(r);
            }
        } catch (Exception e) {
            System.out.println("El error es: " + e);
        }
    }

    public ResultadoobjetivoFacadeLocal getRofl() {
        return rofl;
    }

    public void setRofl(ResultadoobjetivoFacadeLocal rofl) {
        this.rofl = rofl;
    }

    public Resultadoobjetivo getResObjetivo() {
        return resObjetivo;
    }

    public void setResObjetivo(Resultadoobjetivo resObjetivo) {
        this.resObjetivo = resObjetivo;
    }

    public List<Resultadoobjetivo> getListaResObj() {
        return listaResObj;
    }

    public void setListaResObj(List<Resultadoobjetivo> listaResObj) {
        this.listaResObj = listaResObj;
    }

    public List<Resultadoobjetivo> getListaSeleccionados() {
        return listaSeleccionados;
    }

    public void setListaSeleccionados(List<Resultadoobjetivo> listaSeleccionados) {
        this.listaSeleccionados = listaSeleccionados;
    }

    public Map<String, List<Resultadoobjetivo>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, List<Resultadoobjetivo>> mapa) {
        this.mapa = mapa;
    }
   
}
