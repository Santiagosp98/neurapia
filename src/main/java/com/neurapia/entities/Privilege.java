package com.neurapia.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Mapping for table 'privilege' from neurapia's database.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 0
 */
@Entity
@Table(name = "privilege")
@XmlRootElement
public class Privilege implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @Size(max = 150)
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "father_privilege_id", referencedColumnName = "id")
    private Privilege fatherPrivilege;

    @OneToMany(mappedBy = "fatherPrivilege", fetch = FetchType.LAZY)
    private List<Privilege> privileges;

    @JoinTable(name = "role_has_privilege",
            joinColumns = {
                    @JoinColumn(name = "privilege_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Privilege getFatherPrivilege() {
        return fatherPrivilege;
    }

    public void setFatherPrivilege(Privilege fatherPrivilege) {
        this.fatherPrivilege = fatherPrivilege;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
