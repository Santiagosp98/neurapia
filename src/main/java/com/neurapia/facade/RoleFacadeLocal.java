package com.neurapia.facade;

import com.neurapia.entities.Role;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Role
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface RoleFacadeLocal {
    Role create(Role Role);

    Role update(Role Role);

    Role delete(Role Role);

    Role find(Object id);

    List<Role> findAll();

    List<Role> findRange(int[] range);

    int count();

    List<Role> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Role> findWithQuery(String queryName);

    List<Role> findByNativeQuery(String sql);

    Role findSingleWithNamedQuery(String namedQueryName);

    Role findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}