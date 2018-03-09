package com.neurapia.facade;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class to handle basic CRUD operations.
 * Includes utility methods to count, return a single entityClass,
 * return a list of multiple entities and returning entities between a range.
 * Also, you can find single or multiple results using Named or Native queries.
 *
 * @author Santiago Samper
 * @version %I%, %G%
 * @since 2018-07-03
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Inserts a row in the database.
     * Makes the entity persistent then writes the change in database.
     *
     * @param entity The object entity.
     * @return The entity.
     */
    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity;
    }

    /**
     * Updates a row in the database.
     * Updates a persistence entity instance with new field values from
     * a detached entity instance. Finally, writes the changes in database.
     *
     * @param entity The object entity.
     * @return The entity.
     */
    public T update(T entity) {
        getEntityManager().merge((entity));
        getEntityManager().flush();
        return entity;
    }

    /**
     * Deletes a row in the database.
     * Executes the remove operation based on the loaded entity.
     *
     * @param entity The object entity.
     * @return The entity.
     */
    public T delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
        return entity;
    }

    /**
     * Find an entity by it's primary key then, return it.
     *
     * @param id Entity's primary key.
     * @return Fetched entity.
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Fetch all entities from a database table.
     *
     * @return A list of fetched entites.
     */
    public List<T> findAll() {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     * Fetch a result list of entities between a range.
     *
     * @param range An array of integers where the range is assigned.
     * @return A result list of fetched entities.
     */
    public List<T> findRange(int[] range) {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        Query query = getEntityManager().createQuery(criteriaQuery);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }

    /**
     * Fetch the number of rows in database from a given entity.
     *
     * @return Total quantity of database rows from an entity.
     */
    public int count() {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    }

    /**
     * Fetch a list of entities based on a NamedQuery.
     *
     * @return Result of entities that matches the query.
     */
    public List<T> findWithNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Fetch a list of entities based on a NamedQuery with parameters.
     *
     * @param namedQueryName The NamedQuery name.
     * @param parameters     The NamedQuery parameters.
     * @return Result of entities that matches the NamedQuery.
     */
    public List findwithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    /**
     * Fetch a list of entities using JPQL query.
     *
     * @param queryName The name of the query.
     * @return Result of entities that matches the query.
     */
    public List<T> findWithQuery(String queryName) {
        return getEntityManager().createQuery(queryName).getResultList();
    }

    /**
     * Fetch a list of entities using native SQL code.
     *
     * @return Result of entities that matches the native query.
     */
    public List<T> findByNativeQuery(String sql) {
        return getEntityManager().createNativeQuery(sql, entityClass).getResultList();
    }

    /**
     * Return a single entity using a NamedQuery.
     *
     * @param namedQueryName The NamedQuery name.
     * @return An entity that matches the query's 'requirements'.
     * @throws NoResultException Throw it if there's no results.
     */
    public T findSingleWithNamedQuery(String namedQueryName) {
        T result = null;
        try {
            result = (T) getEntityManager().createNamedQuery(namedQueryName).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Return a single entity using a NamedQuery with parameters.
     *
     * @param namedQueryName The NamedQuery name.
     * @param parameters     The NamedQuery parameters.
     * @return An entity that matches the query's 'requirements'.
     * @throws NoResultException Throw it if there's no results.
     */
    public T findSingleWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        T result = null;
        try {
            result = (T) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return result;
    }
}
