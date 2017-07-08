/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Jeisson Diaz
 */
@Entity
@Table(name = "usuario_seguridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioSeguridad.findAll", query = "SELECT u FROM UsuarioSeguridad u")
    , @NamedQuery(name = "UsuarioSeguridad.findById", query = "SELECT u FROM UsuarioSeguridad u WHERE u.id = :id")
    , @NamedQuery(name = "UsuarioSeguridad.findByIdUsuario", query = "SELECT u FROM UsuarioSeguridad u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "UsuarioSeguridad.findByTipoDocumneto", query = "SELECT u FROM UsuarioSeguridad u WHERE u.tipoDocumneto = :tipoDocumneto")
    , @NamedQuery(name = "UsuarioSeguridad.findByNumeroDocumento", query = "SELECT u FROM UsuarioSeguridad u WHERE u.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "UsuarioSeguridad.findByPrimerNombre", query = "SELECT u FROM UsuarioSeguridad u WHERE u.primerNombre = :primerNombre")
    , @NamedQuery(name = "UsuarioSeguridad.findBySegundoNombre", query = "SELECT u FROM UsuarioSeguridad u WHERE u.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "UsuarioSeguridad.findByPrimerApellido", query = "SELECT u FROM UsuarioSeguridad u WHERE u.primerApellido = :primerApellido")
    , @NamedQuery(name = "UsuarioSeguridad.findBySegundoApellido", query = "SELECT u FROM UsuarioSeguridad u WHERE u.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "UsuarioSeguridad.findByEstadoUsuario", query = "SELECT u FROM UsuarioSeguridad u WHERE u.estadoUsuario = :estadoUsuario")
    , @NamedQuery(name = "UsuarioSeguridad.findByFechaRegistro", query = "SELECT u FROM UsuarioSeguridad u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "UsuarioSeguridad.findByCorreoElectronico", query = "SELECT u FROM UsuarioSeguridad u WHERE u.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "UsuarioSeguridad.findByClaveUsuario", query = "SELECT u FROM UsuarioSeguridad u WHERE u.claveUsuario = :claveUsuario")
    , @NamedQuery(name = "UsuarioSeguridad.findByTipoMoficacion", query = "SELECT u FROM UsuarioSeguridad u WHERE u.tipoMoficacion = :tipoMoficacion")
    , @NamedQuery(name = "UsuarioSeguridad.findByFechaModificacion", query = "SELECT u FROM UsuarioSeguridad u WHERE u.fechaModificacion = :fechaModificacion")})
public class UsuarioSeguridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 3)
    @Column(name = "tipoDocumneto")
    private String tipoDocumneto;
    @Size(max = 20)
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
    @Size(max = 8)
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
    @Size(max = 45)
    @Column(name = "TipoMoficacion")
    private String tipoMoficacion;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public UsuarioSeguridad() {
    }

    public UsuarioSeguridad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDocumneto() {
        return tipoDocumneto;
    }

    public void setTipoDocumneto(String tipoDocumneto) {
        this.tipoDocumneto = tipoDocumneto;
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

    public String getTipoMoficacion() {
        return tipoMoficacion;
    }

    public void setTipoMoficacion(String tipoMoficacion) {
        this.tipoMoficacion = tipoMoficacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSeguridad)) {
            return false;
        }
        UsuarioSeguridad other = (UsuarioSeguridad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.UsuarioSeguridad[ id=" + id + " ]";
    }
    
}
