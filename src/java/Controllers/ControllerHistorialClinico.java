
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Anamnesis;
import Entities.Historialclinico;
import Entities.Usuario;
import Entities.Dolor;
import Entities.Respuestaactividad;
import Entities.Resultadoproceso;
import Facade.AnamnesisFacadeLocal;
import Facade.DolorFacadeLocal;
import Facade.HistorialclinicoFacadeLocal;
import Facade.RespuestaactividadFacadeLocal;
import Facade.ResultadoprocesoFacadeLocal;
import Facade.UsuarioFacadeLocal;

import java.text.ParseException;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 * @author jair3
 */
@Named(value = "controllerHistorialClinico")
@ConversationScoped
public class ControllerHistorialClinico extends ControllerApp {

    /**
     * Creates a new instance of ControllerHistorialClinico
     */
    public ControllerHistorialClinico() {
    }

    @Inject
    ControllerUsuario controllerUsuario;

    public ControllerUsuario getControllerUsuario() {
        return controllerUsuario;
    }

    public void setControllerUsuario(ControllerUsuario controllerUsuario) {
        this.controllerUsuario = controllerUsuario;
    }

    @EJB
    private HistorialclinicoFacadeLocal historialClinicoFacade;
    private Historialclinico historialClinico;
    private List<Historialclinico> listaHistorialClinico;
    private int edad;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    private List<Usuario> listaUsuarios;

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    private Anamnesis anamnesis;
    private List<Anamnesis> listAnamnesis;
    private List<Anamnesis> listAnamnesisObtenidos;

    @EJB
    private DolorFacadeLocal dolorFacade;
    private Dolor dolor;
    private List<Dolor> listaDolor;
    private List<Dolor> listaDolorObtenidos;
    
    @EJB
    private RespuestaactividadFacadeLocal resActFacade;
    private Respuestaactividad respuestaAct;
    private List<Respuestaactividad> listRespAct;
    private List<Respuestaactividad> listRespActobtenidos;

    @EJB
    private ResultadoprocesoFacadeLocal resultadoProcesoFacade;
    private Resultadoproceso resultadoProceso;
    private List<Resultadoproceso> listaresultadoProceso;
    private List<Resultadoproceso> resultadosOBtenidos;
    private Map<String, List<Resultadoproceso>> mapa;

    public HistorialclinicoFacadeLocal getHistorialClinicoFacade() {
        return historialClinicoFacade;
    }

    public void setHistorialClinicoFacade(HistorialclinicoFacadeLocal historialClinicoFacade) {
        this.historialClinicoFacade = historialClinicoFacade;
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

    public List<Anamnesis> getListAnamnesis() {
        return listAnamnesis;
    }

    public void setListAnamnesis(List<Anamnesis> listAnamnesis) {
        this.listAnamnesis = listAnamnesis;
    }

    public DolorFacadeLocal getDolorFacade() {
        return dolorFacade;
    }

    public void setDolorFacade(DolorFacadeLocal dolorFacade) {
        this.dolorFacade = dolorFacade;
    }

    public Dolor getDolor() {
        return dolor;
    }

    public void setDolor(Dolor dolor) {
        this.dolor = dolor;
    }

    public List<Dolor> getListaDolor() {
        return listaDolor;
    }

    public void setListaDolor(List<Dolor> listaDolor) {
        this.listaDolor = listaDolor;
    }

    public ResultadoprocesoFacadeLocal getResultadoProcesoFacade() {
        return resultadoProcesoFacade;
    }

    public void setResultadoProcesoFacade(ResultadoprocesoFacadeLocal resultadoProcesoFacade) {
        this.resultadoProcesoFacade = resultadoProcesoFacade;
    }

    public Resultadoproceso getResultadoProceso() {
        return resultadoProceso;
    }

    public void setResultadoProceso(Resultadoproceso resultadoProceso) {
        this.resultadoProceso = resultadoProceso;
    }

    public List<Resultadoproceso> getListaresultadoProceso() {
        return listaresultadoProceso;
    }

    public void setListaresultadoProceso(List<Resultadoproceso> listaresultadoProceso) {
        this.listaresultadoProceso = listaresultadoProceso;
    }

    public List<Resultadoproceso> getResultadosOBtenidos() {
        return resultadosOBtenidos;
    }

    public void setResultadosOBtenidos(List<Resultadoproceso> resultadosOBtenidos) {
        this.resultadosOBtenidos = resultadosOBtenidos;
    }

    public RespuestaactividadFacadeLocal getResTraFacade() {
        return resActFacade;
    }

    public void setResTraFacade(RespuestaactividadFacadeLocal resActFacade) {
        this.resActFacade = resActFacade;
    }

    public Respuestaactividad getRespuestaAct() {
        return respuestaAct;
    }

    public void setRespuestaAct(Respuestaactividad respuestaAct) {
        this.respuestaAct = respuestaAct;
    }

    public List<Respuestaactividad> getListRespAct() {
        return listRespAct;
    }

    public void setListRespAct(List<Respuestaactividad> listRespAct) {
        this.listRespAct = listRespAct;
    }

    public RespuestaactividadFacadeLocal getResActFacade() {
        return resActFacade;
    }

    public void setResActFacade(RespuestaactividadFacadeLocal resActFacade) {
        this.resActFacade = resActFacade;
    }

    public List<Respuestaactividad> getListRespActobtenidos() {
        return listRespActobtenidos;
    }

    public void setListRespActobtenidos(List<Respuestaactividad> listRespActobtenidos) {
        this.listRespActobtenidos = listRespActobtenidos;
    }

    public List<Anamnesis> getListAnamnesisObtenidos() {
        return listAnamnesisObtenidos;
    }

    public void setListAnamnesisObtenidos(List<Anamnesis> listAnamnesisObtenidos) {
        this.listAnamnesisObtenidos = listAnamnesisObtenidos;
    }

    public List<Dolor> getListaDolorObtenidos() {
        return listaDolorObtenidos;
    }

    public void setListaDolorObtenidos(List<Dolor> listaDolorObtenidos) {
        this.listaDolorObtenidos = listaDolorObtenidos;
    }
    
    
    
    @PostConstruct
    public void init() {
        historialClinico = new Historialclinico();
        listaHistorialClinico = historialClinicoFacade.findAll();
        usuario = new Usuario();
        
        dolor = new Dolor();
        
        resultadoProceso = new Resultadoproceso();
        resultadosOBtenidos = new ArrayList();
        
        anamnesis = new Anamnesis();
        listAnamnesis = anamnesisFacade.findAll();
        listAnamnesisObtenidos = new ArrayList();
        
        listaDolor = dolorFacade.findAll();
        listaresultadoProceso = resultadoProcesoFacade.findAll();
        listaUsuarios = usuarioFacade.findAll();
        
        respuestaAct = new Respuestaactividad();
        listRespActobtenidos = new ArrayList();
    }

    public List<Historialclinico> consultarHistorialClinico() {
        this.listaHistorialClinico = historialClinicoFacade.findAll();
        return listaHistorialClinico;
    }

    public Map<String, List<Resultadoproceso>> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, List<Resultadoproceso>> mapa) {
        this.mapa = mapa;
    }

    public String seleccionarHistorialclinico(Historialclinico historialClinico) {
        iniciarConversacion();
        listRespAct = resActFacade.findAll();
        try {
            this.historialClinico = historialClinico;
            int id = historialClinico.getIdHistorialClinico();
            for (Anamnesis anamnesis : listAnamnesis) {
                if (id == anamnesis.getCodHistorialClinico().getIdHistorialClinico()) {
                    listAnamnesisObtenidos.add(anamnesis);
                    this.anamnesis = anamnesis;
                    System.out.println("Seleccionando anamnesis: " + anamnesis.getIdAnamnesis());
                    for (Dolor dolor : listaDolor) {
                        if (anamnesis.getCodDolor().getIdDolor() == dolor.getIdDolor()) {
                            System.out.println("Seleccionando dolor: " + dolor.getIdDolor());
                            this.dolor = dolor;
                        }
                    }                    
                }
            }
            
            for (Resultadoproceso resultadoProceso : listaresultadoProceso) {
                if (id == resultadoProceso.getCodHistorialClinico().getIdHistorialClinico()) {
                    resultadosOBtenidos.add(resultadoProceso);
                    System.out.println("Seleccionando resultados: " + resultadoProceso.getIdResultadoProceso());
                }
            }
            for (Respuestaactividad respuestaactividad : listRespAct) {
                if (id == respuestaactividad.getIdHistorialclinico().getIdHistorialClinico()) {                    
                    listRespActobtenidos.add(respuestaactividad);
                    System.out.println("Seleccionando resultados Actividad: " + respuestaactividad.getIdRespuestaActividad());
                }
            }            
        } catch (Exception e) {
            System.out.println("Excepcion: " + e);
        }
        inicializarListaOrdenada();
        System.out.println("Nombre paciente:" + historialClinico.getCodUsuario().getPrimerNombre());
        System.out.println("Seleccionando anamnesis: " + listAnamnesisObtenidos.size());
        System.out.println("Seleccionando dolor: " + dolor.getIdDolor());
        return "ActualizarHistorialClinico?faces-redirect=true";
    }
    
    public void actualizarHistorialClinico(Integer idHistorialClinico){
        this.historialClinico = historialClinicoFacade.find(idHistorialClinico);
        seleccionarHistorialclinico(this.historialClinico);
    }

    public void editarHistorialClinico() {
        try {
            if (historialClinico != null || !historialClinico.equals("")) {
                System.out.println(historialClinico.getCodEps().getIdEps());
                this.historialClinicoFacade.edit(historialClinico);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void editarAnamnesis() {
        System.out.println("Estamos actualizando la anamnesis");
        this.anamnesisFacade.edit(anamnesis);
        editarDolor();
    }

    public void editarDolor() {
        System.out.println("Estamos editando Dolor");
        this.dolorFacade.edit(dolor);
    }

    public String selectAnamnesis() {
        return "Anamnesis";
    }

    public String selectMovilidad() {
        return "Movilidad";
    }

    public String selectInformacionBasica() {
        return "ActualizarHistorialClinico";
    }

    public String selectObjetivoTratamiento() {
        return "Objetivos";
    }

    public String selectReporteTratamiento() {
        return "ReporteTratamiento";
    }

    public String crearHistorialClinico() throws ParseException {
        iniciarConversacion();
        if (controllerUsuario.getUsuario() != null) {
            historialClinico.setCodUsuario(controllerUsuario.getUsuario());
        }
        if (this.usuario != null) {
            System.out.println("Estamos creando un hsitorial clinico");           
            historialClinico.setFechaCreacion(new Date());
            this.historialClinicoFacade.create(historialClinico);
            iniciarConversacion();
            return "Anamnesis?faces-redirect=true";
        }
        System.out.println("no se pudo crear");
        return "ConsultarUsuarios?faces-redirect=true";
    }

    public void eliminarHistorialClinico(Historialclinico historialclinico) {
        this.historialClinico = historialclinico;
        historialClinicoFacade.remove(historialclinico);
        this.consultarHistorialClinico();
    }

    public HistorialclinicoFacadeLocal getHistorialClinicoFacadeLocal() {
        return historialClinicoFacade;
    }

    public void setHistorialClinicoFacadeLocal(HistorialclinicoFacadeLocal historialClinicoFacade) {
        this.historialClinicoFacade = historialClinicoFacade;
    }

    public Historialclinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(Historialclinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public List<Historialclinico> getListaHistorialClinico() {
        return listaHistorialClinico;
    }

    public void setListaHistorialClinico(List<Historialclinico> listaHistorialClinico) {
        this.listaHistorialClinico = listaHistorialClinico;
    }

    public void inicializarListaOrdenada() {
        mapa = new HashMap<>();
        String nomTemp = "";
        for (Resultadoproceso r : resultadosOBtenidos) {
            System.out.println("Probando lista ordenada");
            if (nomTemp.equals("") || !r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo().equals(nomTemp)) {
                nomTemp = r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo();
                if(!mapa.containsKey(nomTemp)) {
                    mapa.put(nomTemp, new ArrayList<Resultadoproceso>());
                }
            }
            mapa.get(nomTemp).add(r);
        }
        System.out.println("Map list: " + mapa);
    }
}
