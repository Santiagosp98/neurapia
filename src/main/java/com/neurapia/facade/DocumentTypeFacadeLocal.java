package com.neurapia.facade;

import com.neurapia.entities.DocumentType;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see DocumentType
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface DocumentTypeFacadeLocal {
    DocumentType create(DocumentType DocumentType);

    DocumentType update(DocumentType DocumentType);

    DocumentType delete(DocumentType DocumentType);

    DocumentType find(Object id);

    List<DocumentType> findAll();

    List<DocumentType> findRange(int[] range);

    int count();

    List<DocumentType> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<DocumentType> findWithQuery(String queryName);

    List<DocumentType> findByNativeQuery(String sql);

    DocumentType findSingleWithNamedQuery(String namedQueryName);

    DocumentType findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}