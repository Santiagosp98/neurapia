/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citamedica;
import Entities.Fisioterapeuta;
import Entities.Usuario;
import Facade.CitamedicaFacadeLocal;
import Facade.FisioterapeutaFacadeLocal;
import Facade.UsuarioFacadeLocal;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerCitaMedica")
@ConversationScoped
public class ControllerCitaMedica extends ControllerApp{

    /**
     * Creates a new instance of ControllerCitaMedica
     */
    public ControllerCitaMedica() {
    }
    
    @Inject
    private ControllerSession cs;
    
    @EJB
    CitamedicaFacadeLocal citaMedicaFacade;
    Citamedica citaMedica;
    List<Citamedica> listaCitas;
    List<Citamedica> listaCitas1;
    
    @EJB
    FisioterapeutaFacadeLocal fisioterapeutaFacade;
    Fisioterapeuta fisioterapeuta;
    List<Fisioterapeuta> listaFisioterapeutas;
    
    @EJB
    UsuarioFacadeLocal usuarioFacade;       
    Usuario usuario;
    List<Usuario> listaUsuarios;
  
    @PostConstruct
    public void init(){
       this.citaMedica = new Citamedica();
       this.fisioterapeuta = new Fisioterapeuta();
       this.usuario = new Usuario();
       this.listaCitas = citaMedicaFacade.findAll();
       this.listaFisioterapeutas = fisioterapeutaFacade.findAll();
    }
    
    public List<Citamedica> consultarCitaMedica(){
        this.listaCitas = citaMedicaFacade.findAll();
        return listaCitas;
        
    } 
    
    public List<Citamedica> listarCitasporEstado(){
        this.listaCitas = citaMedicaFacade.citasPendientes("Pendiente");
        return listaCitas;        
    } 
    //Consultas de el usuario desde rol usuario
    public List<Citamedica> listarCitasporUsuario(){
         System.out.println(listaCitas.size());
         listaCitas = citaMedicaFacade.citasPorUsuario(cs.getUsuario());
         System.out.println(listaCitas.size());
        return listaCitas;        
    } 
    //Consulta de las citas por fisioterapeuta
    public List<Citamedica> listarCitasporFisioterapeuta(){   
        try {
            fisioterapeuta.setCodUsuario(cs.getUsuario());
            for (Fisioterapeuta ft : listaFisioterapeutas) {
                System.out.println(ft.getCodUsuario());
                if (fisioterapeuta.getCodUsuario().equals(ft.getCodUsuario())) {
                    System.out.println("Estoy listando por fisoterapeuta");
                    fisioterapeuta.setIdFisioterapeuta(ft.getIdFisioterapeuta());
                    System.out.println(fisioterapeuta.getCodUsuario());
                    listaCitas = citaMedicaFacade.citasPorFisioterapeuta(fisioterapeuta);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("No estoy en la lista de fisioterapeutas correcto");
        return listaCitas;
    }
    
    
    public String seleccionarCita(Citamedica citamedica){
        iniciarConversacion();
        this.citaMedica = citamedica;
        return "ActualizarCitasMedicas?faces-redirect=true";
    }
    
    public String actualizarCita(){        
        try {
            System.out.println("Estoy actualizando la cita"); 
            citaMedicaFacade.edit(citaMedica);
            System.out.println(citaMedica.getCodFisioterapeuta().getIdFisioterapeuta());
            finalizarConversacion();
            return "ConsultarCitasMedicas?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(citaMedica.getCodFisioterapeuta().getIdFisioterapeuta());
        return "";
        
    }
    
    public String eliminarCita(Citamedica citamedica){
        this.citaMedicaFacade.remove(citamedica);
        return "ConsultarCitasMedicas?faces-redirect=true";
    }
    
    public String crearCitaMedica(){
        try {
//          this.citaMedica.setCodFisioterapeuta(fisioterapeutaFacade.find(fisioterapeuta.getIdFisioterapeuta()));
//          this.citaMedica.setCodUsuario(usuarioFacade.find(usuario.getIdUsuario()));
            this.citaMedica.setEstado(String.valueOf(citaMedica.getSeleccionEstado().get(1).toString()));
            citaMedicaFacade.create(citaMedica);
            return "ConsultarCitasMedicas?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
    
    public CitamedicaFacadeLocal getCitaMedicaFacade() {
        return citaMedicaFacade;
    }

    public void setCitaMedicaFacadeLocal(CitamedicaFacadeLocal citaMedicaFacade) {
        this.citaMedicaFacade = citaMedicaFacade;
    }

    public Citamedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(Citamedica citaMedica) {
        this.citaMedica = citaMedica;
    }

    public List<Citamedica> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Citamedica> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public FisioterapeutaFacadeLocal getFisioterapeutaFacade() {
        return fisioterapeutaFacade;
    }

    public void setFisioterapeutaFacade(FisioterapeutaFacadeLocal fisioterapeutaFacade) {
        this.fisioterapeutaFacade = fisioterapeutaFacade;
    }

    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public List<Fisioterapeuta> getListaFisioterapeutas() {
        return listaFisioterapeutas;
    }

    public void setListaFisioterapeutas(List<Fisioterapeuta> listaFisioterapeutas) {
        this.listaFisioterapeutas = listaFisioterapeutas;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    
}
