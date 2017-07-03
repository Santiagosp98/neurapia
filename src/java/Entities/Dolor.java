/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "evaluacionInicial")
    private String evaluacionInicial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "frecuencia")
    private String frecuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "caracteristica")
    private String caracteristica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "dolorPalmacion")
    private String dolorPalmacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "espasmos")
    private String espasmos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "presenciaCefalea")
    private String presenciaCefalea;
    @Column(name = "escalaNumerica")
    private Integer escalaNumerica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "vertigo")
    private String vertigo;

    public Dolor() {
    }

    public Dolor(Integer idDolor) {
        this.idDolor = idDolor;
    }

    public Dolor(Integer idDolor, String localizacion, String evaluacionInicial, String frecuencia, String caracteristica, String dolorPalmacion, String espasmos, String presenciaCefalea, String vertigo) {
        this.idDolor = idDolor;
        this.localizacion = localizacion;
        this.evaluacionInicial = evaluacionInicial;
        this.frecuencia = frecuencia;
        this.caracteristica = caracteristica;
        this.dolorPalmacion = dolorPalmacion;
        this.espasmos = espasmos;
        this.presenciaCefalea = presenciaCefalea;
        this.vertigo = vertigo;
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

    public String getEvaluacionInicial() {
        return evaluacionInicial;
    }

    public void setEvaluacionInicial(String evaluacionInicial) {
        this.evaluacionInicial = evaluacionInicial;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getDolorPalmacion() {
        return dolorPalmacion;
    }

    public void setDolorPalmacion(String dolorPalmacion) {
        this.dolorPalmacion = dolorPalmacion;
    }

    public String getEspasmos() {
        return espasmos;
    }

    public void setEspasmos(String espasmos) {
        this.espasmos = espasmos;
    }

    public String getPresenciaCefalea() {
        return presenciaCefalea;
    }

    public void setPresenciaCefalea(String presenciaCefalea) {
        this.presenciaCefalea = presenciaCefalea;
    }

    public Integer getEscalaNumerica() {
        return escalaNumerica;
    }

    public void setEscalaNumerica(Integer escalaNumerica) {
        this.escalaNumerica = escalaNumerica;
    }

    public String getVertigo() {
        return vertigo;
    }

    public void setVertigo(String vertigo) {
        this.vertigo = vertigo;
    }
    
    public ArrayList getSeleccionSiNo(){
        ArrayList lista = new ArrayList();
        lista.add("No");
        lista.add("Si");
        return lista;
    }
        
    public ArrayList getSeleccionEscalaNumerica(){
        ArrayList lista = new ArrayList();
        lista.add("1");
        lista.add("2");
        lista.add("3");
        lista.add("4");
        lista.add("5");
        lista.add("6");
        lista.add("7");
        lista.add("8");
        lista.add("9");
        lista.add("10");
        return lista;
    }
    
    public ArrayList getSeleccionFrecuencia(){
        ArrayList lista = new ArrayList();
        lista.add("Ocacional");
        lista.add("Intermedio");
        lista.add("Continuo");
        return lista;
    }
    
    public ArrayList getSeleccionEvaluacionInicial(){
        ArrayList lista = new ArrayList();
        lista.add("Agudo");
        lista.add("Subagudo");
        lista.add("Crónico");
        lista.add("Diurno");
        lista.add("Nocturno");
        return lista;
    }
    
    public ArrayList getSeleccionCaracteristica(){
        ArrayList lista = new ArrayList();
        lista.add("Ardor");
        lista.add("Adormecimiento");
        lista.add("Calambre");
        lista.add("Difuso");
        lista.add("Cansancio");
        lista.add("Tirones");
        return lista;
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
