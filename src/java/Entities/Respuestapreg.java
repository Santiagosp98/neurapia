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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jeisson Diaz
 */
@Entity
@Table(name = "respuestapreg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestapreg.findAll", query = "SELECT r FROM Respuestapreg r")
    , @NamedQuery(name = "Respuestapreg.findByIdRespuestaPreg", query = "SELECT r FROM Respuestapreg r WHERE r.idRespuestaPreg = :idRespuestaPreg")})
public class Respuestapreg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRespuestaPreg")
    private Integer idRespuestaPreg;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp1")
    private String resp1;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp2")
    private String resp2;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp3")
    private String resp3;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp4")
    private String resp4;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp5")
    private String resp5;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp6")
    private String resp6;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp7")
    private String resp7;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp8")
    private String resp8;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp9")
    private String resp9;
    @Lob
    @Size(max = 65535)
    @Column(name = "resp10")
    private String resp10;
    @OneToMany(mappedBy = "codRespuestaPreg", fetch = FetchType.LAZY)
    private List<Prediagnostico> prediagnosticoList;

    public Respuestapreg() {
    }

    public Respuestapreg(Integer idRespuestaPreg) {
        this.idRespuestaPreg = idRespuestaPreg;
    }

    public Integer getIdRespuestaPreg() {
        return idRespuestaPreg;
    }

    public void setIdRespuestaPreg(Integer idRespuestaPreg) {
        this.idRespuestaPreg = idRespuestaPreg;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public String getResp4() {
        return resp4;
    }

    public void setResp4(String resp4) {
        this.resp4 = resp4;
    }

    public String getResp5() {
        return resp5;
    }

    public void setResp5(String resp5) {
        this.resp5 = resp5;
    }

    public String getResp6() {
        return resp6;
    }

    public void setResp6(String resp6) {
        this.resp6 = resp6;
    }

    public String getResp7() {
        return resp7;
    }

    public void setResp7(String resp7) {
        this.resp7 = resp7;
    }

    public String getResp8() {
        return resp8;
    }

    public void setResp8(String resp8) {
        this.resp8 = resp8;
    }

    public String getResp9() {
        return resp9;
    }

    public void setResp9(String resp9) {
        this.resp9 = resp9;
    }

    public String getResp10() {
        return resp10;
    }

    public void setResp10(String resp10) {
        this.resp10 = resp10;
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
        hash += (idRespuestaPreg != null ? idRespuestaPreg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestapreg)) {
            return false;
        }
        Respuestapreg other = (Respuestapreg) object;
        if ((this.idRespuestaPreg == null && other.idRespuestaPreg != null) || (this.idRespuestaPreg != null && !this.idRespuestaPreg.equals(other.idRespuestaPreg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Respuestapreg[ idRespuestaPreg=" + idRespuestaPreg + " ]";
    }
    
}
