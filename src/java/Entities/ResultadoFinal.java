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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "resultadofinal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoFinal.findAll", query = "SELECT r FROM ResultadoFinal r")
    , @NamedQuery(name = "ResultadoFinal.findById", query = "SELECT r FROM ResultadoFinal r WHERE r.id = :id")
    , @NamedQuery(name = "ResultadoFinal.findByResultadoActividad", query = "SELECT r FROM ResultadoFinal r WHERE r.resultadoActividad = :resultadoActividad")
    , @NamedQuery(name = "ResultadoFinal.findByObservacion", query = "SELECT r FROM ResultadoFinal r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "ResultadoFinal.findByFecha", query = "SELECT r FROM ResultadoFinal r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "ResultadoFinal.findByAnamnesis", query = "SELECT r FROM ResultadoFinal r WHERE r.codAnamnesis.idAnamnesis = :codAnamnesis")})
public class ResultadoFinal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "resultadoActividad")
    private String resultadoActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "codAnamnesis", referencedColumnName = "idAnamnesis")
    @ManyToOne(optional = false)
    private Anamnesis codAnamnesis;
    @JoinColumn(name = "codRespuestaActividad", referencedColumnName = "idRespuestaActividad")
    @ManyToOne(optional = false)
    private Respuestaactividad codRespuestaActividad;

    public ResultadoFinal() {
    }

    public ResultadoFinal(Integer id) {
        this.id = id;
    }

    public ResultadoFinal(Integer id, String resultadoActividad, String observacion, Date fecha) {
        this.id = id;
        this.resultadoActividad = resultadoActividad;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResultadoActividad() {
        return resultadoActividad;
    }

    public void setResultadoActividad(String resultadoActividad) {
        this.resultadoActividad = resultadoActividad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Anamnesis getCodAnamnesis() {
        return codAnamnesis;
    }

    public void setCodAnamnesis(Anamnesis codAnamnesis) {
        this.codAnamnesis = codAnamnesis;
    }

    public Respuestaactividad getCodRespuestaActividad() {
        return codRespuestaActividad;
    }

    public void setCodRespuestaActividad(Respuestaactividad codRespuestaActividad) {
        this.codRespuestaActividad = codRespuestaActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoFinal)) {
            return false;
        }
        ResultadoFinal other = (ResultadoFinal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ResultadoFinal[ id=" + id + " ]";
    }
    
}
