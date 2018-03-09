package com.neurapia.facade;

import com.neurapia.entities.Genre;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Genre
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface GenreFacadeLocal {
    Genre create(Genre Genre);

    Genre update(Genre Genre);

    Genre delete(Genre Genre);

    Genre find(Object id);

    List<Genre> findAll();

    List<Genre> findRange(int[] range);

    int count();

    List<Genre> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Genre> findWithQuery(String queryName);

    List<Genre> findByNativeQuery(String sql);

    Genre findSingleWithNamedQuery(String namedQueryName);

    Genre findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}