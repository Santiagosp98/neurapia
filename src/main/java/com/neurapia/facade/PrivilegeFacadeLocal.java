package com.neurapia.facade;

        import com.neurapia.entities.Privilege;

        import java.util.List;
        import java.util.Map;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Privilege
 * @see AbstractFacade
 * @since 2018-08-03
 */
public interface PrivilegeFacadeLocal {
    Privilege create(Privilege Privilege);

    Privilege update(Privilege Privilege);

    Privilege delete(Privilege Privilege);

    Privilege find(Object id);

    List<Privilege> findAll();

    List<Privilege> findRange(int[] range);

    int count();

    List<Privilege> findWithNamedQuery(String namedQueryName);

    List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    List<Privilege> findWithQuery(String queryName);

    List<Privilege> findByNativeQuery(String sql);

    Privilege findSingleWithNamedQuery(String namedQueryName);

    Privilege findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters);
}