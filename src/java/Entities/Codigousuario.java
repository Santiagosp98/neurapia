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
@Table(name = "codigousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codigousuario.findAll", query = "SELECT c FROM Codigousuario c")
    , @NamedQuery(name = "Codigousuario.findByIdcodigoUsuario", query = "SELECT c FROM Codigousuario c WHERE c.idcodigoUsuario = :idcodigoUsuario")})
public class Codigousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcodigoUsuario")
    private Integer idcodigoUsuario;
    @JoinColumn(name = "codRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roles codRol;
    @JoinColumn(name = "codUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;

    public Codigousuario() {
    }

    public Codigousuario(Integer idcodigoUsuario) {
        this.idcodigoUsuario = idcodigoUsuario;
    }

    public Integer getIdcodigoUsuario() {
        return idcodigoUsuario;
    }

    public void setIdcodigoUsuario(Integer idcodigoUsuario) {
        this.idcodigoUsuario = idcodigoUsuario;
    }

    public Roles getCodRol() {
        return codRol;
    }

    public void setCodRol(Roles codRol) {
        this.codRol = codRol;
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
        hash += (idcodigoUsuario != null ? idcodigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codigousuario)) {
            return false;
        }
        Codigousuario other = (Codigousuario) object;
        if ((this.idcodigoUsuario == null && other.idcodigoUsuario != null) || (this.idcodigoUsuario != null && !this.idcodigoUsuario.equals(other.idcodigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Codigousuario[ idcodigoUsuario=" + idcodigoUsuario + " ]";
    }
    
}
