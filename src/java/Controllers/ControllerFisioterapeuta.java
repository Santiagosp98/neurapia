/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Fisioterapeuta;
import Facade.FisioterapeutaFacadeLocal;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
/**
 *
 * @author jair3
 */
@Named(value = "controllerFisioterapeuta")
@ConversationScoped
public class ControllerFisioterapeuta extends ControllerApp {

    /**
     * Creates a new instance of ControllerFisioterapeutas
     */
    public ControllerFisioterapeuta() {
    }
    
    @EJB
    private FisioterapeutaFacadeLocal fisioterapeutaFacade;
    private Fisioterapeuta fisioterapeuta;
    private List<Fisioterapeuta> listaFisioterapeuta;
    
    @PostConstruct
    public void init(){
        fisioterapeuta = new Fisioterapeuta();
        listaFisioterapeuta = fisioterapeutaFacade.findAll();
    }
    
    public  List<Fisioterapeuta> consultarFisioterapeutas(){
        this.listaFisioterapeuta = fisioterapeutaFacade.findAll();
        return listaFisioterapeuta;
    }
    
    public String eliminarFisioterapeuta(){
        this.fisioterapeutaFacade.remove(fisioterapeuta);
        return "ConsultarFisioterapeutas";
    }
    
    public String seleccionarFisioterapeuta(Fisioterapeuta fisioterapeuta){
        iniciarConversacion();
        this.fisioterapeuta = fisioterapeuta;
        return "ActualizarFisioterapeuta";
    }
    
    public String editarFisioterapeuta(){
        this.fisioterapeutaFacade.edit(fisioterapeuta);
        finalizarConversacion();
        return "ConsultarFisioterapeutas";
    }

    public FisioterapeutaFacadeLocal getFisioterapeutaFacade() {
        return fisioterapeutaFacade;
    }

    public void setFisioterapeutaFacadeLocal(FisioterapeutaFacadeLocal fisioterapeutaFacade) {
        this.fisioterapeutaFacade = fisioterapeutaFacade;
    }

    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public List<Fisioterapeuta> getListaFisioterapeuta() {
        return listaFisioterapeuta;
    }

    public void setListaFisioterapeuta(List<Fisioterapeuta> listaFisioterapeuta) {
        this.listaFisioterapeuta = listaFisioterapeuta;
    }
    
    
    
}
