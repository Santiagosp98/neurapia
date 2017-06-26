/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersConfiguracionSistema;

import Entities.Proceso;
import Facade.ProcesoFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;

/**
 *
 * @author Jair3
 */
@Named(value = "controllerProceso")
@ConversationScoped
public class ControllerProceso extends  Controllers.ControllerApp {

    /**
     * Creates a new instance of ControllerProceso
     */
    public ControllerProceso() {
    }
    
    @EJB
    ProcesoFacadeLocal procesoFacade;
    Proceso proceso;
    List<Proceso> listaProceso;
    private Proceso procesoSeleccionado;

    @PostConstruct
    public void init(){
        proceso = new Proceso();
        listaProceso=procesoFacade.findAll();
    }
            
    
    public List<Proceso> consultarProceso(){
        this.listaProceso=procesoFacade.findAll();
        return listaProceso;
    }
    
     public String crearProceso() {
        if (proceso.getNombreProceso()!= null && !proceso.getNombreProceso().equals("")) {
            this.procesoFacade.create(proceso);
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {
        }
        return "";
    }

    public String prepararEditar(Proceso proc) {
        iniciarConversacion();
        procesoSeleccionado = proc;
        return "EditarProceso?faces-redirect=true";
    }

    public String guardarCambios() {
        if (procesoSeleccionado.getNombreProceso()!= null && !procesoSeleccionado.getNombreProceso().equals("")) {
            procesoFacade.edit(procesoSeleccionado);
            finalizarConversacion();
            return "ConsultarDatosSistema?faces-redirect=true";
        } else {

        }
        return "";
    }

    public ProcesoFacadeLocal getProcesoFacadeLocal() {
        return procesoFacade;
    }

    public void setProcesoFacadeLocal(ProcesoFacadeLocal procesoFacade) {
        this.procesoFacade = procesoFacade;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public List<Proceso> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<Proceso> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public Proceso getProcesoSeleccionado() {
        return procesoSeleccionado;
    }

    public void setProcesoSeleccionado(Proceso procesoSeleccionado) {
        this.procesoSeleccionado = procesoSeleccionado;
    }    
    
}
