/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
 *
 * @author jair3
 */
@Entity
@Table(name = "historialclinico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialclinico.findAll", query = "SELECT h FROM Historialclinico h")
    , @NamedQuery(name = "Historialclinico.findByIdHistorialClinico", query = "SELECT h FROM Historialclinico h WHERE h.idHistorialClinico = :idHistorialClinico")
    , @NamedQuery(name = "Historialclinico.findByFechaCreacion", query = "SELECT h FROM Historialclinico h WHERE h.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Historialclinico.findByDireccion", query = "SELECT h FROM Historialclinico h WHERE h.direccion = :direccion")
    , @NamedQuery(name = "Historialclinico.findByFechaNacimiento", query = "SELECT h FROM Historialclinico h WHERE h.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Historialclinico.findBySexo", query = "SELECT h FROM Historialclinico h WHERE h.sexo = :sexo")
    , @NamedQuery(name = "Historialclinico.findByGrupoSanguineo", query = "SELECT h FROM Historialclinico h WHERE h.grupoSanguineo = :grupoSanguineo")
    , @NamedQuery(name = "Historialclinico.findByReligion", query = "SELECT h FROM Historialclinico h WHERE h.religion = :religion")
    , @NamedQuery(name = "Historialclinico.findByEstrato", query = "SELECT h FROM Historialclinico h WHERE h.estrato = :estrato")
    , @NamedQuery(name = "Historialclinico.findByZona", query = "SELECT h FROM Historialclinico h WHERE h.zona = :zona")
    , @NamedQuery(name = "Historialclinico.findByCiudadResidencia", query = "SELECT h FROM Historialclinico h WHERE h.ciudadResidencia = :ciudadResidencia")
    , @NamedQuery(name = "Historialclinico.findByEmpresa", query = "SELECT h FROM Historialclinico h WHERE h.empresa = :empresa")
    , @NamedQuery(name = "Historialclinico.findByPais", query = "SELECT h FROM Historialclinico h WHERE h.pais = :pais")
    , @NamedQuery(name = "Historialclinico.findByEstudio", query = "SELECT h FROM Historialclinico h WHERE h.estudio = :estudio")
    , @NamedQuery(name = "Historialclinico.findByOcupacion", query = "SELECT h FROM Historialclinico h WHERE h.ocupacion = :ocupacion")
    , @NamedQuery(name = "Historialclinico.findByTelefono", query = "SELECT h FROM Historialclinico h WHERE h.telefono = :telefono")})
public class Historialclinico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistorialClinico")
    private Integer idHistorialClinico;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "grupoSanguineo")
    private String grupoSanguineo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "religion")
    private String religion;
    @Column(name = "estrato")
    private Integer estrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Zona")
    private String zona;
    @Size(max = 45)
    @Column(name = "ciudadResidencia")
    private String ciudadResidencia;
    @Size(max = 45)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 45)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "estudio")
    private String estudio;
    @Size(max = 45)
    @Column(name = "ocupacion")
    private String ocupacion;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "codUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codUsuario;
    @JoinColumn(name = "codEps", referencedColumnName = "idEps")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eps codEps;
    @JoinColumn(name = "codLugarNacimiento", referencedColumnName = "idDepartamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento codLugarNacimiento;

    public Historialclinico() {
    }

    public Historialclinico(Integer idHistorialClinico) {
        this.idHistorialClinico = idHistorialClinico;
    }

    public Historialclinico(Integer idHistorialClinico, String sexo, String grupoSanguineo, String religion, String zona, String estudio) {
        this.idHistorialClinico = idHistorialClinico;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.religion = religion;
        this.zona = zona;
        this.estudio = estudio;
    }

    public Integer getIdHistorialClinico() {
        return idHistorialClinico;
    }

    public void setIdHistorialClinico(Integer idHistorialClinico) {
        this.idHistorialClinico = idHistorialClinico;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Eps getCodEps() {
        return codEps;
    }

    public void setCodEps(Eps codEps) {
        this.codEps = codEps;
    }

    public Departamento getCodLugarNacimiento() {
        return codLugarNacimiento;
    }

    public void setCodLugarNacimiento(Departamento codLugarNacimiento) {
        this.codLugarNacimiento = codLugarNacimiento;
    }
    
    public ArrayList getListaEstrato(){
        ArrayList lista = new ArrayList();
        lista.add("0");
        lista.add("1");
        lista.add("2");
        lista.add("3");
        lista.add("4");
        lista.add("5");
        lista.add("6");
        return lista;
    }
    
    public ArrayList getListaReligion(){
        ArrayList lista = new ArrayList();
        lista.add("Cristianismo");
        lista.add("Judaísmo");
        lista.add("Hinduismo");
        lista.add("Islamismo");
        lista.add("Budismo");
        lista.add("Otra");
        return lista;
    }
    
    public ArrayList getListaGrupoSanguineo(){
        ArrayList lista = new ArrayList();
        lista.add("AB+");
        lista.add("AB-");
        lista.add("A+");
        lista.add("A-");
        lista.add("B+");
        lista.add("B-");
        lista.add("O+");
        lista.add("O-");
        return lista;
    }
    
    public ArrayList getListaSexo(){
        ArrayList lista = new ArrayList();
        lista.add("M");
        lista.add("F");
        lista.add("Otro");
        return lista;
    }
    
    public ArrayList getListaZona(){
        ArrayList lista = new ArrayList();
        lista.add("Rural");
        lista.add("Urbana");
        return lista;
    }
    
    public ArrayList getListaEstudios(){
        ArrayList lista = new ArrayList();
        lista.add("No Tiene");
        lista.add("Primaria");
        lista.add("Secundaria");
        lista.add("Bachiller");
        lista.add("Técnico");
        lista.add("Tecnólogo");
        lista.add("Profesional");
        lista.add("Posgrado");
        lista.add("Maestria");
        lista.add("Doctorado");
        lista.add("Otro");
        return lista;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialClinico != null ? idHistorialClinico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialclinico)) {
            return false;
        }
        Historialclinico other = (Historialclinico) object;
        if ((this.idHistorialClinico == null && other.idHistorialClinico != null) || (this.idHistorialClinico != null && !this.idHistorialClinico.equals(other.idHistorialClinico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Historialclinico[ idHistorialClinico=" + idHistorialClinico + " ]";
    }
    
}
