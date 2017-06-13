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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "dolor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dolor.findAll", query = "SELECT d FROM Dolor d")
    , @NamedQuery(name = "Dolor.findByIdDolor", query = "SELECT d FROM Dolor d WHERE d.idDolor = :idDolor")
    , @NamedQuery(name = "Dolor.findByLocalizacion", query = "SELECT d FROM Dolor d WHERE d.localizacion = :localizacion")
    , @NamedQuery(name = "Dolor.findByEvaluacionInicial", query = "SELECT d FROM Dolor d WHERE d.evaluacionInicial = :evaluacionInicial")
    , @NamedQuery(name = "Dolor.findByFrecuencia", query = "SELECT d FROM Dolor d WHERE d.frecuencia = :frecuencia")
    , @NamedQuery(name = "Dolor.findByCaracteristica", query = "SELECT d FROM Dolor d WHERE d.caracteristica = :caracteristica")
    , @NamedQuery(name = "Dolor.findByDolorPalmacion", query = "SELECT d FROM Dolor d WHERE d.dolorPalmacion = :dolorPalmacion")
    , @NamedQuery(name = "Dolor.findByEspasmos", query = "SELECT d FROM Dolor d WHERE d.espasmos = :espasmos")
    , @NamedQuery(name = "Dolor.findByPresenciaCefalea", query = "SELECT d FROM Dolor d WHERE d.presenciaCefalea = :presenciaCefalea")
    , @NamedQuery(name = "Dolor.findByEscalaNumerica", query = "SELECT d FROM Dolor d WHERE d.escalaNumerica = :escalaNumerica")
    , @NamedQuery(name = "Dolor.findByVertigo", query = "SELECT d FROM Dolor d WHERE d.vertigo = :vertigo")})
public class Dolor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDolor")
    private Integer idDolor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "localizacion")
    private String localizacion;
    @Column(name = "evaluacionInicial")
    private Integer evaluacionInicial;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Column(name = "caracteristica")
    private Integer caracteristica;
    @Column(name = "dolorPalmacion")
    private Short dolorPalmacion;
    @Column(name = "espasmos")
    private Short espasmos;
    @Column(name = "presenciaCefalea")
    private Short presenciaCefalea;
    @Column(name = "escalaNumerica")
    private Integer escalaNumerica;
    @Column(name = "vertigo")
    private Short vertigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDolor", fetch = FetchType.LAZY)
    private List<Anamnesis> anamnesisList;

    public Dolor() {
    }

    public Dolor(Integer idDolor) {
        this.idDolor = idDolor;
    }

    public Dolor(Integer idDolor, String localizacion) {
        this.idDolor = idDolor;
        this.localizacion = localizacion;
    }

    public Integer getIdDolor() {
        return idDolor;
    }

    public void setIdDolor(Integer idDolor) {
        this.idDolor = idDolor;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Integer getEvaluacionInicial() {
        return evaluacionInicial;
    }

    public void setEvaluacionInicial(Integer evaluacionInicial) {
        this.evaluacionInicial = evaluacionInicial;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Integer caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Short getDolorPalmacion() {
        return dolorPalmacion;
    }

    public void setDolorPalmacion(Short dolorPalmacion) {
        this.dolorPalmacion = dolorPalmacion;
    }

    public Short getEspasmos() {
        return espasmos;
    }

    public void setEspasmos(Short espasmos) {
        this.espasmos = espasmos;
    }

    public Short getPresenciaCefalea() {
        return presenciaCefalea;
    }

    public void setPresenciaCefalea(Short presenciaCefalea) {
        this.presenciaCefalea = presenciaCefalea;
    }

    public Integer getEscalaNumerica() {
        return escalaNumerica;
    }

    public void setEscalaNumerica(Integer escalaNumerica) {
        this.escalaNumerica = escalaNumerica;
    }

    public Short getVertigo() {
        return vertigo;
    }

    public void setVertigo(Short vertigo) {
        this.vertigo = vertigo;
    }

    @XmlTransient
    public List<Anamnesis> getAnamnesisList() {
        return anamnesisList;
    }

    public void setAnamnesisList(List<Anamnesis> anamnesisList) {
        this.anamnesisList = anamnesisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDolor != null ? idDolor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dolor)) {
            return false;
        }
        Dolor other = (Dolor) object;
        if ((this.idDolor == null && other.idDolor != null) || (this.idDolor != null && !this.idDolor.equals(other.idDolor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Dolor[ idDolor=" + idDolor + " ]";
    }
    
}
