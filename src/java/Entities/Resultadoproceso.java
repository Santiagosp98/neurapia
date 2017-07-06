/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "resultadoproceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadoproceso.findAll", query = "SELECT r FROM Resultadoproceso r")
    , @NamedQuery(name = "Resultadoproceso.findByIdResultadoProceso", query = "SELECT r FROM Resultadoproceso r WHERE r.idResultadoProceso = :idResultadoProceso")
    , @NamedQuery(name = "Resultadoproceso.findByResultadoProceso", query = "SELECT r FROM Resultadoproceso r WHERE r.resultadoProceso = :resultadoProceso")})
public class    Resultadoproceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResultadoProceso")
    private Integer idResultadoProceso;
    @Column(name = "resultadoProceso")
    private Integer resultadoProceso;
    @JoinColumn(name = "codCaracteristicaMovilidad", referencedColumnName = "idCaracteristicaMovilidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Caracteristicamovilidad codCaracteristicaMovilidad;
    @JoinColumn(name = "codProceso", referencedColumnName = "idProceso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proceso codProceso;
    @JoinColumn(name = "codHistorialClinico", referencedColumnName = "idHistorialClinico")
    @ManyToOne(fetch = FetchType.LAZY)
    private Historialclinico codHistorialClinico;

    public Resultadoproceso() {
    }

    public Resultadoproceso(Integer idResultadoProceso) {
        this.idResultadoProceso = idResultadoProceso;
    }

    public Integer getIdResultadoProceso() {
        return idResultadoProceso;
    }

    public void setIdResultadoProceso(Integer idResultadoProceso) {
        this.idResultadoProceso = idResultadoProceso;
    }

    public Integer getResultadoProceso() {
        return resultadoProceso;
    }

    public void setResultadoProceso(Integer resultadoProceso) {
        this.resultadoProceso = resultadoProceso;
    }

    public Caracteristicamovilidad getCodCaracteristicaMovilidad() {
        return codCaracteristicaMovilidad;
    }

    public void setCodCaracteristicaMovilidad(Caracteristicamovilidad codCaracteristicaMovilidad) {
        this.codCaracteristicaMovilidad = codCaracteristicaMovilidad;
    }

    public Proceso getCodProceso() {
        return codProceso;
    }

    public void setCodProceso(Proceso codProceso) {
        this.codProceso = codProceso;
    }

    public Historialclinico getCodHistorialClinico() {
        return codHistorialClinico;
    }

    public void setCodHistorialClinico(Historialclinico codHistorialClinico) {
        this.codHistorialClinico = codHistorialClinico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoProceso != null ? idResultadoProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoproceso)) {
            return false;
        }
        Resultadoproceso other = (Resultadoproceso) object;
        if ((this.idResultadoProceso == null && other.idResultadoProceso != null) || (this.idResultadoProceso != null && !this.idResultadoProceso.equals(other.idResultadoProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Resultadoproceso[ idResultadoProceso=" + idResultadoProceso + " ]";
    }
    
}
