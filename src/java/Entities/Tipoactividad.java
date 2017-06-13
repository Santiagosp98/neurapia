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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "tipoactividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoactividad.findAll", query = "SELECT t FROM Tipoactividad t")
    , @NamedQuery(name = "Tipoactividad.findByIdTipoActividad", query = "SELECT t FROM Tipoactividad t WHERE t.idTipoActividad = :idTipoActividad")
    , @NamedQuery(name = "Tipoactividad.findByDescripcionActividad", query = "SELECT t FROM Tipoactividad t WHERE t.descripcionActividad = :descripcionActividad")})
public class Tipoactividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoActividad")
    private Integer idTipoActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcionActividad")
    private String descripcionActividad;
    @JoinColumn(name = "codActividad", referencedColumnName = "idActividad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividad codActividad;

    public Tipoactividad() {
    }

    public Tipoactividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public Tipoactividad(Integer idTipoActividad, String descripcionActividad) {
        this.idTipoActividad = idTipoActividad;
        this.descripcionActividad = descripcionActividad;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Actividad getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Actividad codActividad) {
        this.codActividad = codActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoActividad != null ? idTipoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoactividad)) {
            return false;
        }
        Tipoactividad other = (Tipoactividad) object;
        if ((this.idTipoActividad == null && other.idTipoActividad != null) || (this.idTipoActividad != null && !this.idTipoActividad.equals(other.idTipoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tipoactividad[ idTipoActividad=" + idTipoActividad + " ]";
    }
    
}
