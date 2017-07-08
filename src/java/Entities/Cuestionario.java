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
 * @author Jeisson Diaz
 */
@Entity
@Table(name = "cuestionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuestionario.findAll", query = "SELECT c FROM Cuestionario c")
    , @NamedQuery(name = "Cuestionario.findByIdCuestionario", query = "SELECT c FROM Cuestionario c WHERE c.idCuestionario = :idCuestionario")})
public class Cuestionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCuestionario")
    private Integer idCuestionario;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg1")
    private String preg1;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg2")
    private String preg2;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg3")
    private String preg3;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg4")
    private String preg4;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg5")
    private String preg5;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg6")
    private String preg6;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg7")
    private String preg7;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg8")
    private String preg8;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg9")
    private String preg9;
    @Lob
    @Size(max = 65535)
    @Column(name = "preg10")
    private String preg10;
    @OneToMany(mappedBy = "codCuestionario", fetch = FetchType.LAZY)
    private List<Prediagnostico> prediagnosticoList;

    public Cuestionario() {
    }

    public Cuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getPreg1() {
        return preg1;
    }

    public void setPreg1(String preg1) {
        this.preg1 = preg1;
    }

    public String getPreg2() {
        return preg2;
    }

    public void setPreg2(String preg2) {
        this.preg2 = preg2;
    }

    public String getPreg3() {
        return preg3;
    }

    public void setPreg3(String preg3) {
        this.preg3 = preg3;
    }

    public String getPreg4() {
        return preg4;
    }

    public void setPreg4(String preg4) {
        this.preg4 = preg4;
    }

    public String getPreg5() {
        return preg5;
    }

    public void setPreg5(String preg5) {
        this.preg5 = preg5;
    }

    public String getPreg6() {
        return preg6;
    }

    public void setPreg6(String preg6) {
        this.preg6 = preg6;
    }

    public String getPreg7() {
        return preg7;
    }

    public void setPreg7(String preg7) {
        this.preg7 = preg7;
    }

    public String getPreg8() {
        return preg8;
    }

    public void setPreg8(String preg8) {
        this.preg8 = preg8;
    }

    public String getPreg9() {
        return preg9;
    }

    public void setPreg9(String preg9) {
        this.preg9 = preg9;
    }

    public String getPreg10() {
        return preg10;
    }

    public void setPreg10(String preg10) {
        this.preg10 = preg10;
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
        if (!(object instanceof Cuestionario)) {
            return false;
        }
        Cuestionario other = (Cuestionario) object;
        if ((this.idCuestionario == null && other.idCuestionario != null) || (this.idCuestionario != null && !this.idCuestionario.equals(other.idCuestionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cuestionario[ idCuestionario=" + idCuestionario + " ]";
    }
    
}
