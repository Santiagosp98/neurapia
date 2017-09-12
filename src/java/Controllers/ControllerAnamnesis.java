/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Anamnesis;
import Facade.AnamnesisFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerAnamnesis")
@ConversationScoped
public class ControllerAnamnesis extends ControllerApp{

    @Inject
    Controllers.ControllerHistorialClinico hc;
    
    @Inject
    ControllerDolor dc;
    
    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    private Anamnesis anamnesis;
    private List<Anamnesis> listaAnamnesis;
    private List<Anamnesis> listaAnamnesisObtenidas;
    
    public ControllerAnamnesis() {
        
    }
    
    @PostConstruct
    public void init(){
        this.anamnesis = new Anamnesis();
        listaAnamnesis = anamnesisFacade.findAll();
    }
    public String redireccionarCrearAnamnesis(){
        iniciarConversacion();
        this.anamnesis = new Anamnesis();
        return "crearanamnesis.xhtml?faces-redirect=true";
    }
    public void crearAnamnesis(){
        try {
            if (this.anamnesis != null) {                
                System.out.println("Estamos creando nueva anamnesis");                
                anamnesis.setCodDolor(dc.crearDolor());                
                anamnesis.setCodHistorialClinico(hc.getHistorialClinico());              
                anamnesisFacade.create(anamnesis);
                selectAnamnesis();
            } else {
                System.out.println("Error al crear una nueva anamnesis");
            }
        } catch (Exception e) {
            System.out.println("Error al crear nueva anamnesis: " + e.getMessage());
        }
    }
    
    public List<Anamnesis> listaAnamnesisObtenidas(){
        listaAnamnesisObtenidas = anamnesisFacade.seleccionarPorHistorialClinico(hc.getHistorialClinico().getIdHistorialClinico());
        for (Anamnesis a : listaAnamnesisObtenidas) {
            anamnesis = a;
            System.out.println("anamnesis Obtenidas: " + anamnesis.getIdAnamnesis());
        }
        return listaAnamnesisObtenidas;        
    }
    
    public void editarAnamnesis() {
        System.out.println("Estamos actualizando la anamnesis");
        this.anamnesisFacade.edit(anamnesis);
        dc.editarDolor();
    }
    
    public List<Anamnesis> getListaAnamnesisObtenidas() {
        return listaAnamnesisObtenidas;
    }

    public void setListaAnamnesisObtenidas(List<Anamnesis> listaAnamnesisObtenidas) {
        this.listaAnamnesisObtenidas = listaAnamnesisObtenidas;
    }
    
    public List<Anamnesis> consultarAnamnesis(){
        listaAnamnesis = anamnesisFacade.findAll();
        return listaAnamnesis;
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

    public List<Anamnesis> getListaAnamnesis() {
        return listaAnamnesis;
    }

    public void setListaAnamnesis(List<Anamnesis> listaAnamnesis) {
        this.listaAnamnesis = listaAnamnesis;
    }
    
    
}
