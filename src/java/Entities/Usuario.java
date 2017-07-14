/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

/**
 *
 * @author jair3
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByTipoDocumento", query = "SELECT u FROM Usuario u WHERE u.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Usuario.findByNumeroDocumento", query = "SELECT u FROM Usuario u WHERE u.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "Usuario.findByPrimerNombre", query = "SELECT u FROM Usuario u WHERE u.primerNombre = :primerNombre")
    , @NamedQuery(name = "Usuario.findBySegundoNombre", query = "SELECT u FROM Usuario u WHERE u.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Usuario.findByPrimerApellido", query = "SELECT u FROM Usuario u WHERE u.primerApellido = :primerApellido")
    , @NamedQuery(name = "Usuario.findBySegundoApellido", query = "SELECT u FROM Usuario u WHERE u.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Usuario.findByEstadoUsuario", query = "SELECT u FROM Usuario u WHERE u.estadoUsuario = :estadoUsuario")
    , @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Usuario.findByCorreoElectronico", query = "SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "Usuario.listadeusuarios", query = "SELECT u FROM Usuario u WHERE u.codRol = :rol")
    , @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.correoElectronico = :email AND u.claveUsuario = :claveUsuario")
    , @NamedQuery(name = "Usuario.findByClaveUsuario", query = "SELECT u FROM Usuario u WHERE u.claveUsuario = :claveUsuario")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario", fetch = FetchType.LAZY)
    private List<Prediagnostico> prediagnosticoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numeroDocumento")
    private String numeroDocumento;
    @Size(max = 45)
    @Column(name = "primerNombre")
    private String primerNombre;
    @Size(max = 45)
    @Column(name = "segundoNombre")
    private String segundoNombre;
    @Size(max = 45)
    @Column(name = "primerApellido")
    private String primerApellido;
    @Size(max = 45)
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "estadoUsuario")
    private String estadoUsuario;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Size(max = 45)
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Size(max = 45)
    @Column(name = "claveUsuario")
    private String claveUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario", fetch = FetchType.LAZY)
    private List<Historialclinico> historialclinicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cudUsuario", fetch = FetchType.LAZY)
    private List<Reportetratamiento> reportetratamientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario", fetch = FetchType.LAZY)
    private List<Citamedica> citamedicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario", fetch = FetchType.LAZY)
    private List<Fisioterapeuta> fisioterapeutaList;
    @JoinColumn(name = "codRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol codRol;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String tipoDocumento, String numeroDocumento, String estadoUsuario) {
        this.idUsuario = idUsuario;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    @XmlTransient
    public List<Historialclinico> getHistorialclinicoList() {
        return historialclinicoList;
    }

    public void setHistorialclinicoList(List<Historialclinico> historialclinicoList) {
        this.historialclinicoList = historialclinicoList;
    }

    @XmlTransient
    public List<Reportetratamiento> getReportetratamientoList() {
        return reportetratamientoList;
    }

    public void setReportetratamientoList(List<Reportetratamiento> reportetratamientoList) {
        this.reportetratamientoList = reportetratamientoList;
    }

    @XmlTransient
    public List<Citamedica> getCitamedicaList() {
        return citamedicaList;
    }

    public void setCitamedicaList(List<Citamedica> citamedicaList) {
        this.citamedicaList = citamedicaList;
    }

    @XmlTransient
    public List<Fisioterapeuta> getFisioterapeutaList() {
        return fisioterapeutaList;
    }

    public void setFisioterapeutaList(List<Fisioterapeuta> fisioterapeutaList) {
        this.fisioterapeutaList = fisioterapeutaList;
    }

    public Rol getCodRol() {
        return codRol;
    }

    public void setCodRol(Rol codRol) {
        this.codRol = codRol;
    }

    
    /*Edicion entidad Usuario*/
    public String getFullNameUsuario(){
        if (this.primerApellido == null || this.primerNombre == null) {
            return "";
        }
        String nombreCompleto = this.primerNombre + " " + this.primerApellido;
        return nombreCompleto;
    }
    
    public ArrayList getSeleccionEstados(){
        ArrayList lista = new ArrayList();
        lista.add("Activo");
        lista.add("Inactivo");
        return lista;
    }
    public ArrayList getSeleccionDocumento(){
        ArrayList lista = new ArrayList();
        lista.add("TI");
        lista.add("CC");
        lista.add("CE");
        return lista;
    }
    
    public ArrayList getSeleccionRol(){
        ArrayList listaRol = new ArrayList();
        listaRol.add("Super Administrador");
        listaRol.add("Administrador");
        listaRol.add("Fisioterapeuta");
        listaRol.add("Usuario");
        return listaRol;
    }
    /*Edicion entidad Usuario*/
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    @XmlTransient
    public List<Prediagnostico> getPrediagnosticoList() {
        return prediagnosticoList;
    }

    public void setPrediagnosticoList(List<Prediagnostico> prediagnosticoList) {
        this.prediagnosticoList = prediagnosticoList;
    }
    
}
