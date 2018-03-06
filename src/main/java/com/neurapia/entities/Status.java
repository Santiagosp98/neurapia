package com.neurapia.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Mapping for table 'status' from neurapia's database.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 0
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Person> people;

    public Status() {
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
}
