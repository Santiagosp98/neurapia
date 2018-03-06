package com.neurapia.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Mapping for table 'country' from neurapia's database.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 0
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Person> people;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
