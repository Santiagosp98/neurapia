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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeisson Diaz
 */
@Entity
@Table(name = "prediagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prediagnostico.findAll", query = "SELECT p FROM Prediagnostico p")
    , @NamedQuery(name = "Prediagnostico.findByIdPrediagnostico", query = "SELECT p FROM Prediagnostico p WHERE p.idPrediagnostico = :idPrediagnostico")
    , @NamedQuery(name = "Prediagnostico.findByFecha", query = "SELECT p FROM Prediagnostico p WHERE p.fecha = :fecha")})
public class Prediagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrediagnostico")
    private Integer idPrediagnostico;
    @Lob
    @Size(max = 65535)
    @Column(name = "resultadoPrediagnostico")
    private String resultadoPrediagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "codCuestionario", referencedColumnName = "idCuestionario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cuestionario codCuestionario;
    @JoinColumn(name = "codRespuestaPreg", referencedColumnName = "idRespuestaPreg")
    @ManyToOne(fetch = FetchType.LAZY)
    private Respuestapreg codRespuestaPreg;
    @JoinColumn(name = "codUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;

    public Prediagnostico() {
    }

    public Prediagnostico(Integer idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public Prediagnostico(Integer idPrediagnostico, Date fecha) {
        this.idPrediagnostico = idPrediagnostico;
        this.fecha = fecha;
    }

    public Integer getIdPrediagnostico() {
        return idPrediagnostico;
    }

    public void setIdPrediagnostico(Integer idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public String getResultadoPrediagnostico() {
        return resultadoPrediagnostico;
    }

    public void setResultadoPrediagnostico(String resultadoPrediagnostico) {
        this.resultadoPrediagnostico = resultadoPrediagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuestionario getCodCuestionario() {
        return codCuestionario;
    }

    public void setCodCuestionario(Cuestionario codCuestionario) {
        this.codCuestionario = codCuestionario;
    }

    public Respuestapreg getCodRespuestaPreg() {
        return codRespuestaPreg;
    }

    public void setCodRespuestaPreg(Respuestapreg codRespuestaPreg) {
        this.codRespuestaPreg = codRespuestaPreg;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
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
