/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Controllers.ControllerApp;
import Entities.Partecuerpo;
import Facade.PartecuerpoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author jair3
 */
@Named(value = "controllerParteCuerpo")
@ConversationScoped
public class ControllerParteCuerpo extends ControllerApp {

    /**
     * Creates a new instance of ControllerParteCuerpo
     */
    public ControllerParteCuerpo() {
    }
    
    @EJB
    PartecuerpoFacadeLocal pcfl;
    Partecuerpo parteCuerpo;
    List<Partecuerpo> listaPartesCuerpo;
    
    @PostConstruct
    public void init(){
        parteCuerpo = new Partecuerpo();
        listaPartesCuerpo = pcfl.findAll();
    }
    
    public List<Partecuerpo> obtenerPartesCuerpo(){
        listaPartesCuerpo = pcfl.findAll();
        System.out.println("cantidad partes cuerpo: " + listaPartesCuerpo.size());
        return listaPartesCuerpo;
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

    public List<Partecuerpo> getListaPartesCuerpo() {
        return listaPartesCuerpo;
    }

    public void setListaPartesCuerpo(List<Partecuerpo> listaPartesCuerpo) {
        this.listaPartesCuerpo = listaPartesCuerpo;
    }
    
    
}
