package com.neurapia.facade;

import com.neurapia.entities.Status;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Status
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface StatusFacadeLocal {
    Status create(Status status);

    Status update(Status status);

    Status delete(Status status);

    Status find(Object id);

    List<Status> findAll();

    List<Status> findRange(int[] range);

    int count();

    List<Status> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Status> findWithQuery(String queryName);

    List<Status> findByNativeQuery(String sql);

    Status findSingleWithNamedQuery(String namedQueryName);

    Status findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}