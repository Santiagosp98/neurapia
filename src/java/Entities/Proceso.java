/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
    , @NamedQuery(name = "Proceso.findByIdProceso", query = "SELECT p FROM Proceso p WHERE p.idProceso = :idProceso")
    , @NamedQuery(name = "Proceso.findByNombreProceso", query = "SELECT p FROM Proceso p WHERE p.nombreProceso = :nombreProceso")})
public class Proceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProceso")
    private Integer idProceso;
    @Size(max = 45)
    @Column(name = "nombreProceso")
    private String nombreProceso;
    @OneToMany(mappedBy = "codProceso", fetch = FetchType.LAZY)
    private List<Resultadoproceso> resultadoprocesoList;
    @OneToMany(mappedBy = "codProceso", fetch = FetchType.LAZY)
    private List<Resultadoobjetivo> resultadoobjetivoList;

    public Proceso() {
    }

    public Proceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    @XmlTransient
    public List<Resultadoproceso> getResultadoprocesoList() {
        return resultadoprocesoList;
    }

    public void setResultadoprocesoList(List<Resultadoproceso> resultadoprocesoList) {
        this.resultadoprocesoList = resultadoprocesoList;
    }

    @XmlTransient
    public List<Resultadoobjetivo> getResultadoobjetivoList() {
        return resultadoobjetivoList;
    }

    public void setResultadoobjetivoList(List<Resultadoobjetivo> resultadoobjetivoList) {
        this.resultadoobjetivoList = resultadoobjetivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Proceso[ idProceso=" + idProceso + " ]";
    }
    
}
