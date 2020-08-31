package com.example.demo.service;

import java.io.Serializable;
import java.util.*;
import javax.persistence.criteria.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.SoftDeleteEntity;


public abstract class SoftDeleteEntityService<T extends SoftDeleteEntity, ID extends Serializable> extends EntityService<T, ID> {

    @Override
    public List<T> findAll() {

        return findAll(new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.<Boolean> get("deleted"), false);
            }

        });
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("deleted"), false);
            }
        }, pageable);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("deleted"), false);
            }
        }, sort);
    }

    @Override
    public long count() {
        return count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.<Boolean> get("deleted"), false);
            }
        });
    }

    //TODO 待修正
    /*
    @Override
    public T getOne(ID id) {
    //      TODO: no finished
        return super.getOne(id);
    }
     */

    /*    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return findOne(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.<Boolean> get("deleted"), false);
            }
        });
    }*/

    public Optional<T> findById(final ID id) {
        return findOne(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(new Predicate[] { cb.equal(root.get("id"), id), cb.equal(root.get("deleted"), false) });
            }
        });
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        for (T entity : entities) {
            entity.setDeleted(true);
            save(entity);
        }
    }

    @Override
    public void delete(T entity) {
        if (entity != null) {
            entity.setDeleted(true);
            save(entity);
        }
    }

    @Override
    public void deleteById(ID id) {
        T entity = findById(id).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            save(entity);
        }
    }

    @Override
    public void deleteAll() {
        List<T> entities = findAll();
        for (T entity : entities) {
            entity.setDeleted(true);
            save(entity);
        }
    }
}