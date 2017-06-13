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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "reportetratamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportetratamiento.findAll", query = "SELECT r FROM Reportetratamiento r")
    , @NamedQuery(name = "Reportetratamiento.findByIdReportetratamiento", query = "SELECT r FROM Reportetratamiento r WHERE r.idReportetratamiento = :idReportetratamiento")
    , @NamedQuery(name = "Reportetratamiento.findByFecha", query = "SELECT r FROM Reportetratamiento r WHERE r.fecha = :fecha")})
public class Reportetratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReportetratamiento")
    private Integer idReportetratamiento;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "cudUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario cudUsuario;

    public Reportetratamiento() {
    }

    public Reportetratamiento(Integer idReportetratamiento) {
        this.idReportetratamiento = idReportetratamiento;
    }

    public Integer getIdReportetratamiento() {
        return idReportetratamiento;
    }

    public void setIdReportetratamiento(Integer idReportetratamiento) {
        this.idReportetratamiento = idReportetratamiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getCudUsuario() {
        return cudUsuario;
    }

    public void setCudUsuario(Usuario cudUsuario) {
        this.cudUsuario = cudUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReportetratamiento != null ? idReportetratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportetratamiento)) {
            return false;
        }
        Reportetratamiento other = (Reportetratamiento) object;
        if ((this.idReportetratamiento == null && other.idReportetratamiento != null) || (this.idReportetratamiento != null && !this.idReportetratamiento.equals(other.idReportetratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Reportetratamiento[ idReportetratamiento=" + idReportetratamiento + " ]";
    }
    
}
