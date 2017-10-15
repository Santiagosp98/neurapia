/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerReportes;

import Entities.Dolor;
import Entities.ReporteHistorialClinico;
import Entities.Historialclinico;
import Entities.ReporteHistorialClinico;
import Facade.DolorFacadeLocal;
import Facade.HistorialclinicoFacadeLocal;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jfree.chart.plot.PlotOrientation;
        
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
    
    @EJB
    private DolorFacadeLocal dfl;
    private Dolor dolor;
    private List<Dolor> listaDolor;
    
    
    @PostConstruct
    public void init(){        
        listaReporte = hcfl.generarListaResultados();
        listaDolor = dfl.reporteDolor();
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
    
    private void prepareGraphics() throws JRException, ClassNotFoundException, SQLException, IOException{
        Connection conn;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaneurapia?zeroDateTimeBehavior=convertToNull","jair","3208743082");
        Map<String, Object> params = new HashMap<>();
//        params.put("NombreSesssion", cS.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaDolor);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+ "/WEB-INF/reportes/historialclinico/Grafico.jasper";
        jP = JasperFillManager.fillReport(reportPath, params, conn);
        
    }
    public void exportGraphicsPDF() throws IOException, JRException, ClassNotFoundException, SQLException{
//        System.out.println(listaReporte.toString());
        prepareGraphics();
        ServletOutputStream out = null;
        String contentType =  "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"GraficoPromedioDolor.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jP, out);
        fc.responseComplete();
    }
}