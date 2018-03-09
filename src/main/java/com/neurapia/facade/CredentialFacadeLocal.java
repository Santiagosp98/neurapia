package com.neurapia.facade;

import com.neurapia.entities.Credential;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Credential
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface CredentialFacadeLocal {
    Credential create(Credential Credential);

    Credential update(Credential Credential);

    Credential delete(Credential Credential);

    Credential find(Object id);

    List<Credential> findAll();

    List<Credential> findRange(int[] range);

    int count();

    List<Credential> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Credential> findWithQuery(String queryName);

    List<Credential> findByNativeQuery(String sql);

    Credential findSingleWithNamedQuery(String namedQueryName);

    Credential findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}