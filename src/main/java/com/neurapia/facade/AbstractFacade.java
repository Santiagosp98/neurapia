package com.neurapia.facade;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
}
