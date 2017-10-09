/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jair3
 */
@Entity
@Table(name = "citamedica")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Citamedica.findAll", query = "SELECT c FROM Citamedica c")
        , @NamedQuery(name = "Citamedica.findByIdCitaMedica", query = "SELECT c FROM Citamedica c WHERE c.idCitaMedica = :idCitaMedica")
        , @NamedQuery(name = "Citamedica.findByHora", query = "SELECT c FROM Citamedica c WHERE c.hora = :hora")
        , @NamedQuery(name = "Citamedica.findByFecha", query = "SELECT c FROM Citamedica c WHERE c.fecha = :fecha")
        , @NamedQuery(name = "Citamedica.findByEstado", query = "SELECT c FROM Citamedica c WHERE c.estado = :estado")
        , @NamedQuery(name = "Citamedica.citasPorDobleEstado", query = "SELECT c FROM Citamedica c WHERE c.estado = :estado OR c.estado =:estado2")
        , @NamedQuery(name = "Citamedica.citasPorUsuario", query = "SELECT c FROM Citamedica c WHERE c.codUsuario = :codUsuario")
        , @NamedQuery(name = "Citamedica.citasEntreMeses", query = "SELECT c FROM Citamedica c WHERE c.fecha<:fecha AND c.fecha>:fecha2")
        , @NamedQuery(name = "Citamedica.citasPorUsuarioEstado", query = "SELECT c FROM Citamedica c WHERE c.codUsuario = :codUsuario AND c.estado = :estado")
        , @NamedQuery(name = "Citamedica.citasPorUsuarioDobleEstado", query = "SELECT c FROM Citamedica c WHERE c.codUsuario = :codUsuario AND c.estado = :estado OR c.estado = :estado2 AND c.codUsuario = :codUsuario")
        , @NamedQuery(name = "Citamedica.citasPorFisioterapeuta", query = "SELECT c FROM Citamedica c WHERE c.codFisioterapeuta = :codFisioterapeuta")
        , @NamedQuery(name = "Citamedica.citaPorFisioFechaHora", query = "SELECT c FROM Citamedica c WHERE c.codFisioterapeuta = :codFisioterapeuta AND c.fecha =:fecha AND c.hora =:hora")
        , @NamedQuery(name = "Citamedica.citasPorFisioterapeutaEstado", query = "SELECT c FROM Citamedica c WHERE c.codFisioterapeuta = :codFisioterapeuta AND c.estado = :estado")
        , @NamedQuery(name = "Citamedica.citasPorFisioterapeutaDobleEstado", query = "SELECT c FROM Citamedica c WHERE c.codFisioterapeuta = :codFisioterapeuta AND c.estado = :estado OR c.estado =:estado2 AND c.codFisioterapeuta = :codFisioterapeuta")
        , @NamedQuery(name = "Citamedica.findByNumeroConsultorio", query = "SELECT c FROM Citamedica c WHERE c.numeroConsultorio = :numeroConsultorio")
        , @NamedQuery(name = "Citamedica.countByEstado", query = "SELECT COUNT(c) FROM Citamedica c WHERE c.estado = :estado")
})
public class Citamedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCitaMedica")
    private Integer idCitaMedica;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(max = 5)
    @Column(name = "numeroConsultorio")
    private String numeroConsultorio;
    @JoinColumn(name = "codUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;
    @JoinColumn(name = "codFisioterapeuta", referencedColumnName = "idFisioterapeuta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fisioterapeuta codFisioterapeuta;

    public Citamedica() {
    }

    public Citamedica(Integer idCitaMedica) {
        this.idCitaMedica = idCitaMedica;
    }

    public Integer getIdCitaMedica() {
        return idCitaMedica;
    }

    public void setIdCitaMedica(Integer idCitaMedica) {
        this.idCitaMedica = idCitaMedica;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        if (fecha != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.format(fecha);
            return fecha;
        }
        return null;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(String numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Fisioterapeuta getCodFisioterapeuta() {
        return codFisioterapeuta;
    }

    public void setCodFisioterapeuta(Fisioterapeuta codFisioterapeuta) {
        this.codFisioterapeuta = codFisioterapeuta;
    }

    /*Edicion de entidad*/
    public String getFullNameFisioterapeuta() {
        String nombreCompleto = this.codFisioterapeuta.getCodUsuario().getPrimerNombre() + " " + this.codFisioterapeuta.getCodUsuario().getPrimerApellido();
        return nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList getSeleccionEstado() {
        ArrayList listaRol = new ArrayList();
        listaRol.add("Cancelada");
        listaRol.add("Pendiente");
        listaRol.add("Realizada");
        return listaRol;
    }

    public String getFullNameUsuario() {
        if (this.codUsuario != null) {
            String nombreCompleto = this.codUsuario.getPrimerNombre() + " " + this.codUsuario.getPrimerApellido();
            return nombreCompleto;
        }
        return "";
    }
    
    public ArrayList getSeleccionConsultorio() {
        ArrayList lista = new ArrayList();
        lista.add("123");
        lista.add("122");
        lista.add("121");
        return lista;
    }
    

    /*Fin Edicion de entidad*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitaMedica != null ? idCitaMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citamedica)) {
            return false;
        }
        Citamedica other = (Citamedica) object;
        if ((this.idCitaMedica == null && other.idCitaMedica != null) || (this.idCitaMedica != null && !this.idCitaMedica.equals(other.idCitaMedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Citamedica[ idCitaMedica=" + idCitaMedica + " ]";
    }

}
