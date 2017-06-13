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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "educacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Educacion.findAll", query = "SELECT e FROM Educacion e")
    , @NamedQuery(name = "Educacion.findByIdEducacion", query = "SELECT e FROM Educacion e WHERE e.idEducacion = :idEducacion")
    , @NamedQuery(name = "Educacion.findByTipoEducacion", query = "SELECT e FROM Educacion e WHERE e.tipoEducacion = :tipoEducacion")})
public class Educacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEducacion")
    private Integer idEducacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoEducacion")
    private String tipoEducacion;

    public Educacion() {
    }

    public Educacion(Integer idEducacion) {
        this.idEducacion = idEducacion;
    }

    public Educacion(Integer idEducacion, String tipoEducacion) {
        this.idEducacion = idEducacion;
        this.tipoEducacion = tipoEducacion;
    }

    public Integer getIdEducacion() {
        return idEducacion;
    }

    public void setIdEducacion(Integer idEducacion) {
        this.idEducacion = idEducacion;
    }

    public String getTipoEducacion() {
        return tipoEducacion;
    }

    public void setTipoEducacion(String tipoEducacion) {
        this.tipoEducacion = tipoEducacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEducacion != null ? idEducacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Educacion)) {
            return false;
        }
        Educacion other = (Educacion) object;
        if ((this.idEducacion == null && other.idEducacion != null) || (this.idEducacion != null && !this.idEducacion.equals(other.idEducacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Educacion[ idEducacion=" + idEducacion + " ]";
    }
    
}
