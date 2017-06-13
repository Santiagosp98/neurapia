/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "partecuerpo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partecuerpo.findAll", query = "SELECT p FROM Partecuerpo p")
    , @NamedQuery(name = "Partecuerpo.findByIdParteCuerpo", query = "SELECT p FROM Partecuerpo p WHERE p.idParteCuerpo = :idParteCuerpo")
    , @NamedQuery(name = "Partecuerpo.findByNombreParteCuerpo", query = "SELECT p FROM Partecuerpo p WHERE p.nombreParteCuerpo = :nombreParteCuerpo")
    , @NamedQuery(name = "Partecuerpo.findByTipoMovilidad", query = "SELECT p FROM Partecuerpo p WHERE p.tipoMovilidad = :tipoMovilidad")})
public class Partecuerpo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParteCuerpo")
    private Integer idParteCuerpo;
    @Size(max = 45)
    @Column(name = "nombreParteCuerpo")
    private String nombreParteCuerpo;
    @Size(max = 45)
    @Column(name = "TipoMovilidad")
    private String tipoMovilidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codParteCuerpo", fetch = FetchType.LAZY)
    private List<Caracteristicamovilidad> caracteristicamovilidadList;

    public Partecuerpo() {
    }

    public Partecuerpo(Integer idParteCuerpo) {
        this.idParteCuerpo = idParteCuerpo;
    }

    public Integer getIdParteCuerpo() {
        return idParteCuerpo;
    }

    public void setIdParteCuerpo(Integer idParteCuerpo) {
        this.idParteCuerpo = idParteCuerpo;
    }

    public String getNombreParteCuerpo() {
        return nombreParteCuerpo;
    }

    public void setNombreParteCuerpo(String nombreParteCuerpo) {
        this.nombreParteCuerpo = nombreParteCuerpo;
    }

    public String getTipoMovilidad() {
        return tipoMovilidad;
    }

    public void setTipoMovilidad(String tipoMovilidad) {
        this.tipoMovilidad = tipoMovilidad;
    }

    @XmlTransient
    public List<Caracteristicamovilidad> getCaracteristicamovilidadList() {
        return caracteristicamovilidadList;
    }

    public void setCaracteristicamovilidadList(List<Caracteristicamovilidad> caracteristicamovilidadList) {
        this.caracteristicamovilidadList = caracteristicamovilidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParteCuerpo != null ? idParteCuerpo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partecuerpo)) {
            return false;
        }
        Partecuerpo other = (Partecuerpo) object;
        if ((this.idParteCuerpo == null && other.idParteCuerpo != null) || (this.idParteCuerpo != null && !this.idParteCuerpo.equals(other.idParteCuerpo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Partecuerpo[ idParteCuerpo=" + idParteCuerpo + " ]";
    }
    
}
