/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerReportes;

import Entities.Rol;
import Entities.Usuario;
import Facade.RolFacadeLocal;
import Facade.UsuarioFacadeLocal;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jboss.weld.bean.builtin.ee.HttpServletRequestBean;
import org.jfree.util.HashNMap;

/**
 *
 * @author BRYAN BUITRAGO
 */
@Named(value = "controllerReporteUsuario")
@RequestScoped
public class ControllerReporteUsuario {

    /**
     * Creates a new instance of ControllerReporteUsuario
     */
    public ControllerReporteUsuario() {
    }
   
    @Inject
    private Controllers.ControllerSession cS;
   
    @EJB
    private UsuarioFacadeLocal ufl;
    private List<Usuario> usuarioLista;
    private JasperPrint jP;
   
    @EJB
    private RolFacadeLocal rolFacade;
    private List<Rol> listaRol;
    private Rol rol;
   
    @PostConstruct
    public void init(){
        usuarioLista = ufl.findAll();
        rol = new Rol();
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public List<Usuario> getUsuarioLista() {
        return usuarioLista;
    }

    public void setUsuarioLista(List<Usuario> usuarioLista) {
        this.usuarioLista = usuarioLista;
    }
   
    private void prepareExport() throws JRException{
        rol.setIdRol(4);
        usuarioLista = ufl.listaUsuariosPorRol(rol);
        Map<String, Object> params = new HashMap<>();
        params.put("NombreSesssion", cS.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(usuarioLista);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+ "/WEB-INF/reportes/usuarios/neurapia1.jasper";
        jP = JasperFillManager.fillReport(reportPath, params, bcds);
    }
    public void exportPDF() throws IOException, JRException{
        prepareExport();
        ServletOutputStream out = null;
        String contentType =  "application/pdf";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        res.setContentType(contentType);
        res.addHeader("Content-disposition", "attachment; filename=\"ReporteUsuarios.pdf\"");
        out = res.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jP, out);
        fc.responseComplete();
    }
}