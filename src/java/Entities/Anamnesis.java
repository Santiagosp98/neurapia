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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "anamnesis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anamnesis.findAll", query = "SELECT a FROM Anamnesis a")
    , @NamedQuery(name = "Anamnesis.findByIdAnamnesis", query = "SELECT a FROM Anamnesis a WHERE a.idAnamnesis = :idAnamnesis")
    , @NamedQuery(name = "Anamnesis.findByDiagnostico", query = "SELECT a FROM Anamnesis a WHERE a.diagnostico = :diagnostico")
    , @NamedQuery(name = "Anamnesis.findByMotivoConsulta", query = "SELECT a FROM Anamnesis a WHERE a.motivoConsulta = :motivoConsulta")})
public class Anamnesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnamnesis")
    private Integer idAnamnesis;
    @Lob
    @Size(max = 65535)
    @Column(name = "anamnesis")
    private String anamnesis;
    @Size(max = 70)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Size(max = 70)
    @Column(name = "motivoConsulta")
    private String motivoConsulta;
    @JoinColumn(name = "codHistorialClinico", referencedColumnName = "idHistorialClinico")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Historialclinico codHistorialClinico;
    @JoinColumn(name = "codActividad", referencedColumnName = "idActividad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividad codActividad;
    @JoinColumn(name = "codDolor", referencedColumnName = "idDolor")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dolor codDolor;

    public Anamnesis() {
    }

    public Anamnesis(Integer idAnamnesis) {
        this.idAnamnesis = idAnamnesis;
    }

    public Integer getIdAnamnesis() {
        return idAnamnesis;
    }

    public void setIdAnamnesis(Integer idAnamnesis) {
        this.idAnamnesis = idAnamnesis;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Historialclinico getCodHistorialClinico() {
        return codHistorialClinico;
    }

    public void setCodHistorialClinico(Historialclinico codHistorialClinico) {
        this.codHistorialClinico = codHistorialClinico;
    }

    public Actividad getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Actividad codActividad) {
        this.codActividad = codActividad;
    }

    public Dolor getCodDolor() {
        return codDolor;
    }

    public void setCodDolor(Dolor codDolor) {
        this.codDolor = codDolor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnamnesis != null ? idAnamnesis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anamnesis)) {
            return false;
        }
        Anamnesis other = (Anamnesis) object;
        if ((this.idAnamnesis == null && other.idAnamnesis != null) || (this.idAnamnesis != null && !this.idAnamnesis.equals(other.idAnamnesis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Anamnesis[ idAnamnesis=" + idAnamnesis + " ]";
    }
    
}
