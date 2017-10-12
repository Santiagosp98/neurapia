package Entities;

import java.util.Date;

public class ReportePrediagnostico {

private Integer idPrediagnostico;
private String numeroDocumento;
private String primerNombre;
private String primerApellido;
private String correoElectronico;
private String fecha;

    public Integer getIdPrediagnostico() {
        return idPrediagnostico;
    }

    public void setIdPrediagnostico(Integer idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
