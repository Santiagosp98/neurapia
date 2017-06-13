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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "faq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faq.findAll", query = "SELECT f FROM Faq f")
    , @NamedQuery(name = "Faq.findByIdFaq", query = "SELECT f FROM Faq f WHERE f.idFaq = :idFaq")})
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFaq")
    private Integer idFaq;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "detallePregunta")
    private String detallePregunta;

    public Faq() {
    }

    public Faq(Integer idFaq) {
        this.idFaq = idFaq;
    }

    public Faq(Integer idFaq, String pregunta, String detallePregunta) {
        this.idFaq = idFaq;
        this.pregunta = pregunta;
        this.detallePregunta = detallePregunta;
    }

    public Integer getIdFaq() {
        return idFaq;
    }

    public void setIdFaq(Integer idFaq) {
        this.idFaq = idFaq;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getDetallePregunta() {
        return detallePregunta;
    }

    public void setDetallePregunta(String detallePregunta) {
        this.detallePregunta = detallePregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaq != null ? idFaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faq)) {
            return false;
        }
        Faq other = (Faq) object;
        if ((this.idFaq == null && other.idFaq != null) || (this.idFaq != null && !this.idFaq.equals(other.idFaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Faq[ idFaq=" + idFaq + " ]";
    }
    
}
