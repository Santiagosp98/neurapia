package com.neurapia.facade;

import com.neurapia.entities.Person;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Person
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface PersonFacadeLocal {
    Person create(Person person);

    Person update(Person person);

    Person delete(Person person);

    Person find(Object id);

    List<Person> findAll();

    List<Person> findRange(int[] range);

    int count();

    List<Person> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Person> findWithQuery(String queryName);

    List<Person> findByNativeQuery(String sql);

    Person findSingleWithNamedQuery(String namedQueryName);

    Person findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}