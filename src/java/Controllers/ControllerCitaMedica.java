/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citamedica;
import Entities.Fisioterapeuta;
import Entities.Usuario;
import Facade.CitamedicaFacade;
import Facade.FisioterapeutaFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author jair3
 */
@Named(value = "controllerCitaMedica")
@SessionScoped
public class ControllerCitaMedica implements Serializable {

    /**
     * Creates a new instance of ControllerCitaMedica
     */
    public ControllerCitaMedica() {
    }
    
    @EJB
    CitamedicaFacade citaMedicaFacade;
    Citamedica citaMedica;
    List<Citamedica> listaCitas;
    
    @EJB
    FisioterapeutaFacade fisioterapeutaFacade;
    Fisioterapeuta fisioterapeuta;
    List<Fisioterapeuta> listaFisioterapeutas;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private List<Usuario> listaUsuarios;
    
    @PostConstruct
    public void init(){
        this.setCitaMedica(new Citamedica());
        this.setListaCitas(getCitaMedicaFacade().findAll());
        
        this.setFisioterapeuta(new Fisioterapeuta());
        this.setListaFisioterapeutas(getFisioterapeutaFacade().findAll());
        
        this.setUsuario(new Usuario());
        this.setListaUsuarios(getUsuarioFacade().findAll());
    }
    
    public List<Citamedica> consultarCitaMedica(){
        this.listaCitas = citaMedicaFacade.findAll();
        return listaCitas;
        
    } 
    
    public String seleccionarCita(Citamedica citamedica){
        this.citaMedica = citamedica;
        return "ActualizarCitasMedicas";
    }
    
    public String actualizarCita(){
        citaMedicaFacade.edit(citaMedica);
        return "ConsultarCitasMedicas";
    }
    
    public String eliminarCita(Citamedica citamedica){
        this.citaMedicaFacade.remove(citamedica);
        return "ConsultarCitasMedicas";
    }
    
    public String crearCitaMedica(){
        init();
        String redirect;
        this.citaMedicaFacade.create(citaMedica);
        redirect = "ConsultarCitasMedicas";
        return redirect;
    }

    public CitamedicaFacade getCitaMedicaFacade() {
        return citaMedicaFacade;
    }

    public void setCitaMedicaFacade(CitamedicaFacade citaMedicaFacade) {
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

    public FisioterapeutaFacade getFisioterapeutaFacade() {
        return fisioterapeutaFacade;
    }

    public void setFisioterapeutaFacade(FisioterapeutaFacade fisioterapeutaFacade) {
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

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
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
