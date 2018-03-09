package com.neurapia.facade;

import com.neurapia.entities.Country;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Country
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface CountryFacadeLocal {
    Country create(Country Country);

    Country update(Country Country);

    Country delete(Country Country);

    Country find(Object id);

    List<Country> findAll();

    List<Country> findRange(int[] range);

    int count();

    List<Country> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Country> findWithQuery(String queryName);

    List<Country> findByNativeQuery(String sql);

    Country findSingleWithNamedQuery(String namedQueryName);

    Country findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}