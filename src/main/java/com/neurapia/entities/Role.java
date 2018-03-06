package com.neurapia.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Mapping for table 'role' from neurapia's database.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 0
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
    private List<Person> people;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Privilege> privileges;

    public Role() {
    }

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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
