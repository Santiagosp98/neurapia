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
@Table(name = "fisioterapeuta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fisioterapeuta.findAll", query = "SELECT f FROM Fisioterapeuta f")
    , @NamedQuery(name = "Fisioterapeuta.findByIdFisioterapeuta", query = "SELECT f FROM Fisioterapeuta f WHERE f.idFisioterapeuta = :idFisioterapeuta")
    , @NamedQuery(name = "Fisioterapeuta.findByTarjetaProfesional", query = "SELECT f FROM Fisioterapeuta f WHERE f.tarjetaProfesional = :tarjetaProfesional")
    , @NamedQuery(name = "Fisioterapeuta.findByEspecializacion", query = "SELECT f FROM Fisioterapeuta f WHERE f.especializacion = :especializacion")
    , @NamedQuery(name = "Fisioterapeuta.findByCorreoEmpresarial", query = "SELECT f FROM Fisioterapeuta f WHERE f.correoEmpresarial = :correoEmpresarial")
    , @NamedQuery(name = "Fisioterapeuta.findByTelefonoEmpresarial", query = "SELECT f FROM Fisioterapeuta f WHERE f.telefonoEmpresarial = :telefonoEmpresarial")})
public class Fisioterapeuta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFisioterapeuta")
    private Integer idFisioterapeuta;
    @Size(max = 30)
    @Column(name = "tarjetaProfesional")
    private String tarjetaProfesional;
    @Size(max = 30)
    @Column(name = "especializacion")
    private String especializacion;
    @Size(max = 45)
    @Column(name = "correoEmpresarial")
    private String correoEmpresarial;
    @Size(max = 45)
    @Column(name = "telefonoEmpresarial")
    private String telefonoEmpresarial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFisioterapeuta", fetch = FetchType.LAZY)
    private List<Citamedica> citamedicaList;
    @JoinColumn(name = "CodUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;

    public Fisioterapeuta() {
    }
    
    public String getFullnameFisioterapeuta(){
        String nombre = this.codUsuario.getFullNameUsuario();
        return "hola";
    }
    
    public Fisioterapeuta(Integer idFisioterapeuta) {
        this.idFisioterapeuta = idFisioterapeuta;
    }

    public Integer getIdFisioterapeuta() {
        return idFisioterapeuta;
    }

    public void setIdFisioterapeuta(Integer idFisioterapeuta) {
        this.idFisioterapeuta = idFisioterapeuta;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public String getCorreoEmpresarial() {
        return correoEmpresarial;
    }

    public void setCorreoEmpresarial(String correoEmpresarial) {
        this.correoEmpresarial = correoEmpresarial;
    }

    public String getTelefonoEmpresarial() {
        return telefonoEmpresarial;
    }

    public void setTelefonoEmpresarial(String telefonoEmpresarial) {
        this.telefonoEmpresarial = telefonoEmpresarial;
    }

    @XmlTransient
    public List<Citamedica> getCitamedicaList() {
        return citamedicaList;
    }

    public void setCitamedicaList(List<Citamedica> citamedicaList) {
        this.citamedicaList = citamedicaList;
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
        hash += (idFisioterapeuta != null ? idFisioterapeuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fisioterapeuta)) {
            return false;
        }
        Fisioterapeuta other = (Fisioterapeuta) object;
        if ((this.idFisioterapeuta == null && other.idFisioterapeuta != null) || (this.idFisioterapeuta != null && !this.idFisioterapeuta.equals(other.idFisioterapeuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Fisioterapeuta[ idFisioterapeuta=" + idFisioterapeuta + " ]";
    }
    
}
