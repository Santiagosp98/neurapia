/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerReportes;

import Entities.ReporteHistorialClinico;
import Entities.Historialclinico;
import Entities.ReporteHistorialClinico;
import Facade.HistorialclinicoFacadeLocal;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *
 * @author BRYAN BUITRAGO
 */
@Named(value = "controllerReporteHc")
@RequestScoped
public class ControllerReporteHistorialClinico {

    /**
     * Creates a new instance of ControllerReporteUsuario
     */
    public ControllerReporteHistorialClinico() {
    }
    
    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;
    
    private JasperPrint jP;
    
    @Inject
    private Controllers.ControllerSession cS;
    
    @EJB
    private HistorialclinicoFacadeLocal hcfl;
    private Historialclinico historialClinico;
    
    private List<ReporteHistorialClinico> listaReporte;
    
    
    @PostConstruct
    public void init(){        
        listaReporte = hcfl.generarListaResultados();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private void prepareExport() throws JRException{
        
        Map<String, Object> params = new HashMap<>();
        params.put("NombreSesssion", cS.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaReporte);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+ "/WEB-INF/reportes/historialclinico/ReporteHistorialClinico.jasper";
        jP = JasperFillManager.fillReport(reportPath, params, bcds);
    }
    public void exportPDF() throws IOException, JRException{
//        System.out.println(listaReporte.toString());
        prepareExport();
        ServletOutputStream out = null;
        String contentType =  "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"ReporteHistorialClinico.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jP, out);
        fc.responseComplete();
    }
}