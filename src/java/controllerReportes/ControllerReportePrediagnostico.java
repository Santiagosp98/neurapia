package controllerReportes;

import Controllers.ControllerSession;
import Entities.ReportePrediagnostico;
import Facade.PrediagnosticoFacadeLocal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named(value = "controllerReportePrediagnostico")
@RequestScoped

public class ControllerReportePrediagnostico   {

    public ControllerReportePrediagnostico() {

    }

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    private JasperPrint jP;

    @Inject
    private ControllerSession cs;

    @EJB
    private PrediagnosticoFacadeLocal pfl;
    private List<ReportePrediagnostico> listaPrediagnosticoResultado;
    private JasperPrint jp;


    @PostConstruct
    public void init() {
        listaPrediagnosticoResultado = pfl.listaReporte("Pendiente");

    }

    protected EntityManager getEntityManager() {
        return em;
    }


    private void prepararExport() throws JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("Nombre", cs.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaPrediagnosticoResultado);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/reportes/prediagnostico/ReportePrediagnostico.jasper";
        jp = JasperFillManager.fillReport(reportPath, params, bcds);
    }

    public void exportarPDF() throws IOException, JRException {

        prepararExport();
        ServletOutputStream out = null;
        String contentType = "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"ReportePrediagnosticoPendiente.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, out);
        fc.responseComplete();
    }

    private void prepareGraphics() throws JRException, ClassNotFoundException, SQLException, IOException{
        Connection conn;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaneurapia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","jair","3208743082");
        Map<String, Object> params = new HashMap<>();
//        params.put("NombreSesssion", cS.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(listaPrediagnosticoResultado);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+ "/WEB-INF/reportes/prediagnostico/graficos.jasper";
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
        res.addHeader("Content-disposition", "attachment; filename=\"GraficoEstadoPrediagnostico.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jP, out);
        fc.responseComplete();
    }
}
