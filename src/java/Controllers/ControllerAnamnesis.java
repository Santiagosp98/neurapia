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

/**
 *
 * @author jair3
 */
@Named(value = "controllerAnamnesis")
@ConversationScoped
public class ControllerAnamnesis extends ControllerApp{

    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    private Anamnesis anamnesis;
    private List<Anamnesis> listaAnamnesis;
    
    public ControllerAnamnesis() {
        
    }
    
    @PostConstruct
    public void init(){
        anamnesis = new Anamnesis();
        listaAnamnesis = anamnesisFacade.findAll();
    }
    
    public List consultarAnamnesis(){
        listaAnamnesis = anamnesisFacade.findAll();
        return listaAnamnesis;
    }
    
    public String editarAnamnesis(){
        System.out.println("Estamos actualizando la anamnesis");
        anamnesisFacade.edit(anamnesis);        
        return "Movilidad";
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
