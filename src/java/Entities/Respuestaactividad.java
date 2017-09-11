/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "respuestaactividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestaactividad.findAll", query = "SELECT r FROM Respuestaactividad r")
    , @NamedQuery(name = "Respuestaactividad.findByIdRespuestaActividad", query = "SELECT r FROM Respuestaactividad r WHERE r.idRespuestaActividad = :idRespuestaActividad")
    , @NamedQuery(name = "Respuestaactividad.respActObtenidas", query = "SELECT r FROM Respuestaactividad r WHERE r.idHistorialclinico.idHistorialClinico = :id")
    , @NamedQuery(name = "Respuestaactividad.findByFechaActividad", query = "SELECT r FROM Respuestaactividad r WHERE r.fechaActividad = :fechaActividad")})
public class Respuestaactividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRespuestaActividad")
    private Integer idRespuestaActividad;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcionRespuesta")
    private String descripcionRespuesta;
    @Column(name = "fechaActividad")
    @Temporal(TemporalType.DATE)
    private Date fechaActividad;
    @JoinColumn(name = "codRespuesta", referencedColumnName = "idRespuesta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Respuesta codRespuesta;
    @JoinColumn(name = "idHistorialclinico", referencedColumnName = "idHistorialClinico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Historialclinico idHistorialclinico;
    @JoinColumn(name = "codTipoActividad", referencedColumnName = "idTipoActividad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipoactividad codTipoActividad;

    public Respuestaactividad() {
    }

    public Respuestaactividad(Integer idRespuestaActividad) {
        this.idRespuestaActividad = idRespuestaActividad;
    }

    public Integer getIdRespuestaActividad() {
        return idRespuestaActividad;
    }

    public void setIdRespuestaActividad(Integer idRespuestaActividad) {
        this.idRespuestaActividad = idRespuestaActividad;
    }

    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public Respuesta getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(Respuesta codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public Historialclinico getIdHistorialclinico() {
        return idHistorialclinico;
    }

    public void setIdHistorialclinico(Historialclinico idHistorialclinico) {
        this.idHistorialclinico = idHistorialclinico;
    }

    public Tipoactividad getCodTipoActividad() {
        return codTipoActividad;
    }

    public void setCodTipoActividad(Tipoactividad codTipoActividad) {
        this.codTipoActividad = codTipoActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaActividad != null ? idRespuestaActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestaactividad)) {
            return false;
        }
        Respuestaactividad other = (Respuestaactividad) object;
        if ((this.idRespuestaActividad == null && other.idRespuestaActividad != null) || (this.idRespuestaActividad != null && !this.idRespuestaActividad.equals(other.idRespuestaActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Respuestaactividad[ idRespuestaActividad=" + idRespuestaActividad + " ]";
    }
    
}
