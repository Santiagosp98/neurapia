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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "cuestionarioprediagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuestionarioprediagnostico.findAll", query = "SELECT c FROM Cuestionarioprediagnostico c")
    , @NamedQuery(name = "Cuestionarioprediagnostico.findByIdCuestionario", query = "SELECT c FROM Cuestionarioprediagnostico c WHERE c.idCuestionario = :idCuestionario")})
public class Cuestionarioprediagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCuestionario")
    private Integer idCuestionario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcionPregunta")
    private String descripcionPregunta;
    @OneToMany(mappedBy = "codCuestionario", fetch = FetchType.LAZY)
    private List<Prediagnostico> prediagnosticoList;

    public Cuestionarioprediagnostico() {
    }

    public Cuestionarioprediagnostico(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Cuestionarioprediagnostico(Integer idCuestionario, String descripcionPregunta) {
        this.idCuestionario = idCuestionario;
        this.descripcionPregunta = descripcionPregunta;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    @XmlTransient
    public List<Prediagnostico> getPrediagnosticoList() {
        return prediagnosticoList;
    }

    public void setPrediagnosticoList(List<Prediagnostico> prediagnosticoList) {
        this.prediagnosticoList = prediagnosticoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuestionario != null ? idCuestionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuestionarioprediagnostico)) {
            return false;
        }
        Cuestionarioprediagnostico other = (Cuestionarioprediagnostico) object;
        if ((this.idCuestionario == null && other.idCuestionario != null) || (this.idCuestionario != null && !this.idCuestionario.equals(other.idCuestionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cuestionarioprediagnostico[ idCuestionario=" + idCuestionario + " ]";
    }
    
}
