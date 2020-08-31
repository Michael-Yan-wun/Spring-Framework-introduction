package com.example.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.entity.BaseEntity;
import com.example.entity.CommonEntityDao;



public abstract class EntityService<T extends BaseEntity, ID extends Serializable> {

    protected Class entityBeanType;

    protected EntityManager em;

    public EntityService() {
        this.entityBeanType = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public List<T> findAll() {
        return getCommonEntityDao().findAll();
    }

    public Page<T> findAll(Pageable pageable) {

        return getCommonEntityDao().findAll(pageable);
    }

    public List<T> findAll(Sort sort) {
        return getCommonEntityDao().findAll(sort);
    }

    /**
     * Returns all instances of the type with the given IDs.
     * 
     * @param ids
     * @return
     */
    public List<T> findAllById(Iterable<ID> ids) {
        return getCommonEntityDao().findAllById(ids);
    }

    /**
     * Flushes all pending changes to the database.
     */
    public void flush() {
        getCommonEntityDao().flush();
    }

    /**
     * Saves an entity and flushes changes instantly.
     * 
     * @param entity
     * @return the saved entity
     */
    public <S extends T> S saveAndFlush(S entity) {
        return getCommonEntityDao().saveAndFlush(entity);
    }

    /**
     * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
     * the {@link javax.persistence.EntityManager} after the call.
     * 
     * @param entities
     */
    public void deleteInBatch(Iterable<T> entities) {
        getCommonEntityDao().deleteInBatch(entities);
    }

    /**
     * Deletes all entities in a batch call.
     */
    public void deleteAllInBatch() {
        getCommonEntityDao().deleteAllInBatch();
    }

    /**
     * Returns a reference to the entity with the given identifier.
     * 
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object)
     */
    public T getOne(ID id) {
        return getCommonEntityDao().getOne(id);
    }

    /**
     * Returns a single entity matching the given {@link Specification}.
     * 
     * @param spec
     * @return
     */
    public Optional<T> findOne(Specification<T> spec) {
        return getCommonEntityDao().findOne(spec);
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     * 
     * @param entity
     * @return the saved entity
     */
    public <S extends T> S save(S entity) {
        return getCommonEntityDao().save(entity);
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return getCommonEntityDao().saveAll(entities);
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public Optional<T> findById(ID id) {
        return getCommonEntityDao().findById((id));
    }

    /**
     * Returns whether an entity with the given id exists.
     * 
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public boolean existsById(ID id) {
        return getCommonEntityDao().existsById(id);
    }

    /**
     * Returns the number of entities available.
     * 
     * @return the number of entities
     */
    public long count() {
        return getCommonEntityDao().count();
    }

    /**
     * Deletes the entity with the given id.
     * 
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    public void deleteById(ID id) {
        getCommonEntityDao().deleteById(id);
    }

    /**
     * Deletes a given entity.
     * 
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    public void delete(T entity) {
        getCommonEntityDao().delete(entity);
    }

    /**
     * Deletes the given entities.
     * 
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    public void deleteAll(Iterable<? extends T> entities) {
        getCommonEntityDao().deleteAll(entities);
    }

    /**
     * Deletes all entities managed by the repository.
     */
    public void deleteAll() {
        getCommonEntityDao().deleteAll();
    }

    /**
     * Returns all entities matching the given {@link Specification}.
     * 
     * @param spec
     * @return
     */
    public List<T> findAll(Specification<T> spec) {
        return getCommonEntityDao().findAll(spec);
    }

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     * 
     * @param spec
     * @param pageable
     * @return
     */
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return getCommonEntityDao().findAll(spec, pageable);
    }

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     * 
     * @param spec
     * @param sort
     * @return
     */
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return getCommonEntityDao().findAll(spec, sort);
    }

    /**
     * Returns the number of instances that the given {@link Specification} will return.
     * 
     * @param spec the {@link Specification} to count instances for
     * @return the number of instances
     */
    public long count(Specification<T> spec) {
        return getCommonEntityDao().count(spec);
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
     *
     * @param example must not be {@literal null}.
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
    <S extends T> Optional<S> findOne(Example<S> example) {
        return getCommonEntityDao().findOne(example);
    }

    /**
     * Returns all entities matching the given {@link Example}. In case no match could be found an empty {@link Iterable}
     * is returned.
     *
     * @param example must not be {@literal null}.
     * @return all entities matching the given {@link Example}.
     */
    <S extends T> Iterable<S> findAll(Example<S> example) {
        return getCommonEntityDao().findAll(example);
    }

    /**
     * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
     * found an empty {@link Iterable} is returned.
     *
     * @param example must not be {@literal null}.
     * @param sort the {@link Sort} specification to sort the results by, must not be {@literal null}.
     * @return all entities matching the given {@link Example}.
     * @since 1.10
     */
    <S extends T> Iterable<S> findAll(Example<S> example, Sort sort) {
        return getCommonEntityDao().findAll(example, sort);
    }

    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example must not be {@literal null}.
     * @param pageable can be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getCommonEntityDao().findAll(example, pageable);
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     * @return the number of instances matching the {@link Example}.
     */
    <S extends T> long count(Example<S> example) {
        return getCommonEntityDao().count(example);
    }

    /**
     * Checks whether the data store contains elements that match the given {@link Example}.
     *
     * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
     * @return {@literal true} if the data store contains elements that match the given {@link Example}.
     */
    <S extends T> boolean exists(Example<S> example) {
        return getCommonEntityDao().exists(example);
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected abstract CommonEntityDao<T, ID> getCommonEntityDao();
}