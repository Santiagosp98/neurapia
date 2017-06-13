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
@Table(name = "objetivotratamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivotratamiento.findAll", query = "SELECT o FROM Objetivotratamiento o")
    , @NamedQuery(name = "Objetivotratamiento.findByIdObjetivoTratamiento", query = "SELECT o FROM Objetivotratamiento o WHERE o.idObjetivoTratamiento = :idObjetivoTratamiento")
    , @NamedQuery(name = "Objetivotratamiento.findByNombreObjetivo", query = "SELECT o FROM Objetivotratamiento o WHERE o.nombreObjetivo = :nombreObjetivo")})
public class Objetivotratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObjetivoTratamiento")
    private Integer idObjetivoTratamiento;
    @Size(max = 45)
    @Column(name = "nombreObjetivo")
    private String nombreObjetivo;
    @OneToMany(mappedBy = "codObjetivoTratamiento", fetch = FetchType.LAZY)
    private List<Resultadoobjetivo> resultadoobjetivoList;

    public Objetivotratamiento() {
    }

    public Objetivotratamiento(Integer idObjetivoTratamiento) {
        this.idObjetivoTratamiento = idObjetivoTratamiento;
    }

    public Integer getIdObjetivoTratamiento() {
        return idObjetivoTratamiento;
    }

    public void setIdObjetivoTratamiento(Integer idObjetivoTratamiento) {
        this.idObjetivoTratamiento = idObjetivoTratamiento;
    }

    public String getNombreObjetivo() {
        return nombreObjetivo;
    }

    public void setNombreObjetivo(String nombreObjetivo) {
        this.nombreObjetivo = nombreObjetivo;
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
        hash += (idObjetivoTratamiento != null ? idObjetivoTratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivotratamiento)) {
            return false;
        }
        Objetivotratamiento other = (Objetivotratamiento) object;
        if ((this.idObjetivoTratamiento == null && other.idObjetivoTratamiento != null) || (this.idObjetivoTratamiento != null && !this.idObjetivoTratamiento.equals(other.idObjetivoTratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Objetivotratamiento[ idObjetivoTratamiento=" + idObjetivoTratamiento + " ]";
    }
    
}
