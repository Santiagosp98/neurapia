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
import Entities.Resultadoproceso;
import Facade.AnamnesisFacadeLocal;
import Facade.DolorFacadeLocal;
import Facade.HistorialclinicoFacadeLocal;
import Facade.ResultadoprocesoFacadeLocal;
import Facade.UsuarioFacadeLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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

    @EJB
    private HistorialclinicoFacadeLocal historialClinicoFacade;
    private Historialclinico historialClinico;
    private List<Historialclinico> listaHistorialClinico;
    private int edad;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    private Anamnesis anamnesis;
    private List<Anamnesis> listAnamnesis;

    @EJB
    private DolorFacadeLocal dolorFacade;
    private Dolor dolor;
    private List<Dolor> listaDolor;

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


    @PostConstruct
    public void init() {
        historialClinico = new Historialclinico();
        anamnesis = new Anamnesis();
        dolor = new Dolor();
        resultadoProceso = new Resultadoproceso();
        resultadosOBtenidos = new ArrayList();
        listaHistorialClinico = historialClinicoFacade.findAll();
        listAnamnesis = anamnesisFacade.findAll();
        listaDolor = dolorFacade.findAll();
        listaresultadoProceso = resultadoProcesoFacade.findAll();
        usuarios = usuarioFacade.findAll();
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
        this.historialClinico = historialClinico;
        for (Anamnesis anamnesis : listAnamnesis) {
            if (historialClinico.getIdHistorialClinico() == anamnesis.getCodHistorialClinico().getIdHistorialClinico()) {
                this.anamnesis = anamnesis;
                for (Dolor dolor : listaDolor) {
                    if (anamnesis.getCodDolor().getIdDolor() == dolor.getIdDolor()) {
                        System.out.println("Seleccionando dolor: " + dolor.getIdDolor());
                        this.dolor = dolor;
                        for (Resultadoproceso resultadoProceso : listaresultadoProceso) {
                            if (historialClinico.getIdHistorialClinico() == resultadoProceso.getCodHistorialClinico().getIdHistorialClinico()) {
                                resultadosOBtenidos.add(resultadoProceso);
                                System.out.println("Seleccionando resultados: " + resultadoProceso.getIdResultadoProceso());
                            }
                        }
                    }
                }
                System.out.println("dnmsjdkhbajkbdjbsajbdjbasjbdjbasjbjdbajksbdjkbjasks");
                inicializarListaOrdenada();
                System.out.println("Seleccionando anamnesis: " + anamnesis.getIdAnamnesis());
                return "ActualizarHistorialClinico?faces-redirect=true";
            }
        }
        System.out.println(historialClinico.getCodUsuario().getPrimerNombre());
        return "ActualizarHistorialClinico?faces-redirect=true";
    }

    public void editarHistorialClinico() {
        if (historialClinico != null || !historialClinico.equals("")) {
            System.out.println(historialClinico.getCodEps().getIdEps());
            this.historialClinicoFacade.edit(historialClinico);
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
        System.out.println("Estamos creando un hsitorial clinico");
        Calendar datosFecha = new GregorianCalendar();
        int anio = datosFecha.get(Calendar.YEAR);
        int mes = datosFecha.get(Calendar.MONTH);
        int dia = datosFecha.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = anio + "-" + mes + "-" + dia;
        Date fechaDate = null;
        fechaDate = formato.parse(strFecha);
//            this.historialClinico.setCodUsuario(usuarioFacade.find(usuario.getIdUsuario()));
        historialClinico.setFechaCreacion(fechaDate);
        historialClinico.setCodUsuario(controllerUsuario.getUsuario());
        this.historialClinicoFacade.create(historialClinico);
        return "ConsultarHistorialClinico?faces-redirect=true";
    }

    public void eliminarHistorialClinico(Historialclinico historialclinico) {
        historialClinicoFacade.remove(historialClinico);
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
            if (nomTemp.equals("") || !r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo().equals(nomTemp)) {
                nomTemp = r.getCodCaracteristicaMovilidad().getCodParteCuerpo().getNombreParteCuerpo();
                mapa.put(nomTemp, new ArrayList<Resultadoproceso>());
            }
            mapa.get(nomTemp).add(r);

        }
        System.out.println("#####################" + mapa);
    }

}
