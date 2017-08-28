/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
import Entities.Caracteristicamovilidad;
import Entities.Partecuerpo;
import Facade.CaracteristicamovilidadFacadeLocal;
import Facade.PartecuerpoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author jair3
 */
@Named(value = "controllerCaracteristicaMovilidad")
@ConversationScoped
public class ControllerCaracteristicaMovilidad extends ControllerApp {

    /**
     * Creates a new instance of ControllerCaracteristicaMovilidad
     */
    public ControllerCaracteristicaMovilidad() {
    }
    
    @EJB
    private CaracteristicamovilidadFacadeLocal cmfl;
    private Caracteristicamovilidad caractMovilidad;
    private List<Caracteristicamovilidad> listCarmov;
    private List<Caracteristicamovilidad> lista;
    
    @EJB
    private PartecuerpoFacadeLocal pcfl;
    private Partecuerpo parteCuerpo;
    private List<Partecuerpo> listaparteCuerpo;
    
    @PostConstruct
    public void init(){
        caractMovilidad = new Caracteristicamovilidad();
        listCarmov = cmfl.findAll();
        listaparteCuerpo = pcfl.findAll();
        lista = new ArrayList();
    }
    
    public List<Caracteristicamovilidad> listarPorParteCuerpo(){
        try {         
            if (caractMovilidad.getCodParteCuerpo().getIdParteCuerpo() != null) {
                System.out.println("id parte del cuerpo seleccionada: " + caractMovilidad.getCodParteCuerpo().getIdParteCuerpo());
                lista = cmfl.listarPorParteCuerpo(caractMovilidad.getCodParteCuerpo().getIdParteCuerpo());
                for (Caracteristicamovilidad caracteristicamovilidad : lista) {
                    System.out.println("caracteristicas Seleccionada: " + caracteristicamovilidad.getTipoCaracteristica());
                }
                return lista;                
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public List<Partecuerpo> obtenerPartesCuerpo(){
        listaparteCuerpo = pcfl.findAll();
        return listaparteCuerpo;
    }
    

    public CaracteristicamovilidadFacadeLocal getCmfl() {
        return cmfl;
    }

    public void setCmfl(CaracteristicamovilidadFacadeLocal cmfl) {
        this.cmfl = cmfl;
    }

    public Caracteristicamovilidad getCaractMovilidad() {
        return caractMovilidad;
    }

    public void setCaractMovilidad(Caracteristicamovilidad caractMovilidad) {
        this.caractMovilidad = caractMovilidad;
    }

    public List<Caracteristicamovilidad> getListCarmov() {
        return listCarmov;
    }

    public void setListCarmov(List<Caracteristicamovilidad> listCarmov) {
        this.listCarmov = listCarmov;
    }

    public PartecuerpoFacadeLocal getPcfl() {
        return pcfl;
    }

    public void setPcfl(PartecuerpoFacadeLocal pcfl) {
        this.pcfl = pcfl;
    }

    public Partecuerpo getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(Partecuerpo parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public List<Partecuerpo> getListaparteCuerpo() {
        return listaparteCuerpo;
    }

    public void setListaparteCuerpo(List<Partecuerpo> listaparteCuerpo) {
        this.listaparteCuerpo = listaparteCuerpo;
    }

    public List<Caracteristicamovilidad> getLista() {
        return lista;
    }

    public void setLista(List<Caracteristicamovilidad> lista) {
        this.lista = lista;
    }
    
    
    
}
