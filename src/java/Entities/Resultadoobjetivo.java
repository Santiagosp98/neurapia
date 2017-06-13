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
@Table(name = "resultadoobjetivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadoobjetivo.findAll", query = "SELECT r FROM Resultadoobjetivo r")
    , @NamedQuery(name = "Resultadoobjetivo.findByIdResultadoObjetivo", query = "SELECT r FROM Resultadoobjetivo r WHERE r.idResultadoObjetivo = :idResultadoObjetivo")
    , @NamedQuery(name = "Resultadoobjetivo.findByValor", query = "SELECT r FROM Resultadoobjetivo r WHERE r.valor = :valor")
    , @NamedQuery(name = "Resultadoobjetivo.findByObservacion", query = "SELECT r FROM Resultadoobjetivo r WHERE r.observacion = :observacion")})
public class Resultadoobjetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResultadoObjetivo")
    private Integer idResultadoObjetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "Observacion")
    private String observacion;
    @JoinColumn(name = "codObjetivoTratamiento", referencedColumnName = "idObjetivoTratamiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Objetivotratamiento codObjetivoTratamiento;
    @JoinColumn(name = "codProceso", referencedColumnName = "idProceso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proceso codProceso;
    @JoinColumn(name = "codHistorialClinico", referencedColumnName = "idHistorialClinico")
    @ManyToOne(fetch = FetchType.LAZY)
    private Historialclinico codHistorialClinico;

    public Resultadoobjetivo() {
    }

    public Resultadoobjetivo(Integer idResultadoObjetivo) {
        this.idResultadoObjetivo = idResultadoObjetivo;
    }

    public Resultadoobjetivo(Integer idResultadoObjetivo, int valor, String observacion) {
        this.idResultadoObjetivo = idResultadoObjetivo;
        this.valor = valor;
        this.observacion = observacion;
    }

    public Integer getIdResultadoObjetivo() {
        return idResultadoObjetivo;
    }

    public void setIdResultadoObjetivo(Integer idResultadoObjetivo) {
        this.idResultadoObjetivo = idResultadoObjetivo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Objetivotratamiento getCodObjetivoTratamiento() {
        return codObjetivoTratamiento;
    }

    public void setCodObjetivoTratamiento(Objetivotratamiento codObjetivoTratamiento) {
        this.codObjetivoTratamiento = codObjetivoTratamiento;
    }

    public Proceso getCodProceso() {
        return codProceso;
    }

    public void setCodProceso(Proceso codProceso) {
        this.codProceso = codProceso;
    }

    public Historialclinico getCodHistorialClinico() {
        return codHistorialClinico;
    }

    public void setCodHistorialClinico(Historialclinico codHistorialClinico) {
        this.codHistorialClinico = codHistorialClinico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoObjetivo != null ? idResultadoObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoobjetivo)) {
            return false;
        }
        Resultadoobjetivo other = (Resultadoobjetivo) object;
        if ((this.idResultadoObjetivo == null && other.idResultadoObjetivo != null) || (this.idResultadoObjetivo != null && !this.idResultadoObjetivo.equals(other.idResultadoObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Resultadoobjetivo[ idResultadoObjetivo=" + idResultadoObjetivo + " ]";
    }
    
}
