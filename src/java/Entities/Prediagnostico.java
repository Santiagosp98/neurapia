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
@Table(name = "prediagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prediagnostico.findAll", query = "SELECT p FROM Prediagnostico p")
    , @NamedQuery(name = "Prediagnostico.findByIdPrediagnostico", query = "SELECT p FROM Prediagnostico p WHERE p.idPrediagnostico = :idPrediagnostico")
    , @NamedQuery(name = "Prediagnostico.findByFecha", query = "SELECT p FROM Prediagnostico p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Prediagnostico.findByHora", query = "SELECT p FROM Prediagnostico p WHERE p.hora = :hora")})
public class Prediagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrediagnostico")
    private Integer idPrediagnostico;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.DATE)
    private Date hora;
    @Lob
    @Size(max = 65535)
    @Column(name = "resultadoPrediagnostico")
    private String resultadoPrediagnostico;
    @JoinColumn(name = "codHistorialClinico", referencedColumnName = "idHistorialClinico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Historialclinico codHistorialClinico;
    @JoinColumn(name = "codCuestionario", referencedColumnName = "idCuestionario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cuestionarioprediagnostico codCuestionario;

    public Prediagnostico() {
    }

    public Prediagnostico(Integer idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public Integer getIdPrediagnostico() {
        return idPrediagnostico;
    }

    public void setIdPrediagnostico(Integer idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getResultadoPrediagnostico() {
        return resultadoPrediagnostico;
    }

    public void setResultadoPrediagnostico(String resultadoPrediagnostico) {
        this.resultadoPrediagnostico = resultadoPrediagnostico;
    }

    public Historialclinico getCodHistorialClinico() {
        return codHistorialClinico;
    }

    public void setCodHistorialClinico(Historialclinico codHistorialClinico) {
        this.codHistorialClinico = codHistorialClinico;
    }

    public Cuestionarioprediagnostico getCodCuestionario() {
        return codCuestionario;
    }

    public void setCodCuestionario(Cuestionarioprediagnostico codCuestionario) {
        this.codCuestionario = codCuestionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrediagnostico != null ? idPrediagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prediagnostico)) {
            return false;
        }
        Prediagnostico other = (Prediagnostico) object;
        if ((this.idPrediagnostico == null && other.idPrediagnostico != null) || (this.idPrediagnostico != null && !this.idPrediagnostico.equals(other.idPrediagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Prediagnostico[ idPrediagnostico=" + idPrediagnostico + " ]";
    }
    
}
