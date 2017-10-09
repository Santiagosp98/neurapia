/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerReportes;

import Entities.Citamedica;
import Facade.CitamedicaFacadeLocal;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import net.sf.jasperreports.engine.JasperPrint;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author BRYAN BUITRAGO
 */
@Named(value = "controllerReporteCita")
@RequestScoped
public class ControllerReporteCita {

    @Inject
    private Controllers.ControllerSession cs;

    @EJB
    private CitamedicaFacadeLocal cfl;
    private List<Citamedica> citasMedicas;
    private JasperPrint jp;
    private String anio;
    private String mes;
    private String dia;

    @PostConstruct
    public void init() {
        citasMedicas = cfl.findAll();
    }

    public CitamedicaFacadeLocal getCfl() {
        return cfl;
    }

    public void setCfl(CitamedicaFacadeLocal cfl) {
        this.cfl = cfl;
    }

    public List<Citamedica> getCitasMedicas() {
        return citasMedicas;
    }

    public void setCitasMedicas(List<Citamedica> citasMedicas) {
        this.citasMedicas = citasMedicas;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Creates a new instance of ControllerReporteCita
     */
    public ControllerReporteCita() {
    }

    private void prepareExport(String dateString) throws JRException, ParseException {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha1 = formato.parse(dateString);
        citasMedicas = cfl.citasPorDosMeses(fecha.getTime(), fecha1);
        Map<String, Object> params = new HashMap<>();
        params.put("Nombre", cs.getUsuario().getFullNameUsuario());
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(citasMedicas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/WEB-INF/reportes/citas/neruapiaReporteCitasFehca.jasper";
        jp = JasperFillManager.fillReport(reportPath, params, bcds);

    }

    public void exportPDF() throws IOException, JRException, ParseException {
        FacesContext fc = FacesContext.getCurrentInstance();
        String fechaIngresada = "";
        Calendar fecha = Calendar.getInstance();
        fechaIngresada = anio + "-" + mes + "-" + dia;
        if (anio != null && mes != null && dia != null) {
            if (Integer.parseInt(anio) <= fecha.get(Calendar.YEAR) && Integer.parseInt(anio) > 2000) {
                if (Integer.parseInt(mes) <= 12 && Integer.parseInt(mes) > 0) {
                    if (Integer.parseInt(anio) == fecha.get(Calendar.YEAR)) {
                        if (Integer.parseInt(mes) < fecha.get(Calendar.MONTH) + 1) {
                            if ((Integer.parseInt(mes) % 2 == 1 && Integer.parseInt(mes) <= 7) || (Integer.parseInt(mes) % 2 == 0 && Integer.parseInt(mes) > 7)) {
                                if (Integer.parseInt(dia) <= 31) {
                                    System.out.println(fechaIngresada + " Fecha");
                                    prepareExport(fechaIngresada);
                                    ServletOutputStream out = null;
                                    String contentType = "application/pdf";
                                    ExternalContext ec = fc.getExternalContext();
                                    HttpServletResponse res = (HttpServletResponse) ec.getResponse();
                                    res.setContentType(contentType);
                                    res.addHeader("Content-disposition", "attachment; filename=\"ReporteCitaMedicaFecha.pdf\"");
                                    out = res.getOutputStream();

                                    JasperExportManager.exportReportToPdfStream(jp, out);
                                    fc.responseComplete();
                                } else {
                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                                            "El dia tiene que ser menor o igual a 31");
                                    fc.addMessage(null, m);
                                }
                            } else {
                                if (Integer.parseInt(dia) <= 30) {
                                    System.out.println(fechaIngresada + " Fecha");
                                    prepareExport(fechaIngresada);
                                    ServletOutputStream out = null;
                                    String contentType = "application/pdf";
                                    ExternalContext ec = fc.getExternalContext();
                                    HttpServletResponse res = (HttpServletResponse) ec.getResponse();
                                    res.setContentType(contentType);
                                    res.addHeader("Content-disposition", "attachment; filename=\"ReporteCitaMedicaFecha.pdf\"");
                                    out = res.getOutputStream();

                                    JasperExportManager.exportReportToPdfStream(jp, out);
                                    fc.responseComplete();
                                } else {
                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                                            "El dia tiene que ser menor o igual a 30");
                                    fc.addMessage(null, m);
                                }
                            }
                        } else {
                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                                    "El mes tiene que ser menor al mes en el que estamos");
                            fc.addMessage(null, m);
                        }
                    } else {
                        if ((Integer.parseInt(mes) % 2 == 1 && Integer.parseInt(mes) <= 7) || (Integer.parseInt(mes) % 2 == 0 && Integer.parseInt(mes) > 7)) {
                            if (Integer.parseInt(dia) <= 31) {
                                System.out.println(fechaIngresada + " Fecha");
                                prepareExport(fechaIngresada);
                                ServletOutputStream out = null;
                                String contentType = "application/pdf";
                                ExternalContext ec = fc.getExternalContext();
                                HttpServletResponse res = (HttpServletResponse) ec.getResponse();
                                res.setContentType(contentType);
                                res.addHeader("Content-disposition", "attachment; filename=\"ReporteCitaMedicaFecha.pdf\"");
                                out = res.getOutputStream();

                                JasperExportManager.exportReportToPdfStream(jp, out);
                                fc.responseComplete();
                            } else {
                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                                        "El dia tiene que ser menor o igual a 31");
                                fc.addMessage(null, m);
                            }
                        } else {
                            if (Integer.parseInt(dia) <= 30) {
                                System.out.println(fechaIngresada + " Fecha");
                                prepareExport(fechaIngresada);
                                ServletOutputStream out = null;
                                String contentType = "application/pdf";
                                ExternalContext ec = fc.getExternalContext();
                                HttpServletResponse res = (HttpServletResponse) ec.getResponse();
                                res.setContentType(contentType);
                                res.addHeader("Content-disposition", "attachment; filename=\"ReporteCitaMedicaFecha.pdf\"");
                                out = res.getOutputStream();

                                JasperExportManager.exportReportToPdfStream(jp, out);
                                fc.responseComplete();
                            } else {
                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                                        "El dia tiene que ser menor o igual a 30");
                                fc.addMessage(null, m);
                            }
                        }
                    }
                } else {
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                            "El mes tiene que ser congruente a los datos de un mes");
                    fc.addMessage(null, m);
                }
            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                        "El año tiene que ser menor al actual y mayor a 2000");
                fc.addMessage(null, m);
            }

        } else {
            System.out.println("No entro" + anio + mes + dia);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar reporte",
                        "Digite datos para el reporte");
            fc.addMessage(null, m);
        }

    }

}
