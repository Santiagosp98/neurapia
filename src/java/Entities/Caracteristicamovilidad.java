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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "caracteristicamovilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracteristicamovilidad.findAll", query = "SELECT c FROM Caracteristicamovilidad c")
    , @NamedQuery(name = "Caracteristicamovilidad.findByIdCaracteristicaMovilidad", query = "SELECT c FROM Caracteristicamovilidad c WHERE c.idCaracteristicaMovilidad = :idCaracteristicaMovilidad")
    , @NamedQuery(name = "Caracteristicamovilidad.findByTipoCaracteristica", query = "SELECT c FROM Caracteristicamovilidad c WHERE c.tipoCaracteristica = :tipoCaracteristica")})
public class Caracteristicamovilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaracteristicaMovilidad")
    private Integer idCaracteristicaMovilidad;
    @Size(max = 45)
    @Column(name = "tipoCaracteristica")
    private String tipoCaracteristica;
    @OneToMany(mappedBy = "codCaracteristicaMovilidad", fetch = FetchType.LAZY)
    private List<Resultadoproceso> resultadoprocesoList;
    @JoinColumn(name = "codParteCuerpo", referencedColumnName = "idParteCuerpo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partecuerpo codParteCuerpo;

    public Caracteristicamovilidad() {
    }

    public Caracteristicamovilidad(Integer idCaracteristicaMovilidad) {
        this.idCaracteristicaMovilidad = idCaracteristicaMovilidad;
    }

    public Integer getIdCaracteristicaMovilidad() {
        return idCaracteristicaMovilidad;
    }

    public void setIdCaracteristicaMovilidad(Integer idCaracteristicaMovilidad) {
        this.idCaracteristicaMovilidad = idCaracteristicaMovilidad;
    }

    public String getTipoCaracteristica() {
        return tipoCaracteristica;
    }

    public void setTipoCaracteristica(String tipoCaracteristica) {
        this.tipoCaracteristica = tipoCaracteristica;
    }

    @XmlTransient
    public List<Resultadoproceso> getResultadoprocesoList() {
        return resultadoprocesoList;
    }

    public void setResultadoprocesoList(List<Resultadoproceso> resultadoprocesoList) {
        this.resultadoprocesoList = resultadoprocesoList;
    }

    public Partecuerpo getCodParteCuerpo() {
        return codParteCuerpo;
    }

    public void setCodParteCuerpo(Partecuerpo codParteCuerpo) {
        this.codParteCuerpo = codParteCuerpo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaracteristicaMovilidad != null ? idCaracteristicaMovilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristicamovilidad)) {
            return false;
        }
        Caracteristicamovilidad other = (Caracteristicamovilidad) object;
        if ((this.idCaracteristicaMovilidad == null && other.idCaracteristicaMovilidad != null) || (this.idCaracteristicaMovilidad != null && !this.idCaracteristicaMovilidad.equals(other.idCaracteristicaMovilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Caracteristicamovilidad[ idCaracteristicaMovilidad=" + idCaracteristicaMovilidad + " ]";
    }
    
}
