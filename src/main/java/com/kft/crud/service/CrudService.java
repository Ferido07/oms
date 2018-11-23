package com.kft.crud.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Msolomon on 5/21/2018.
 */
public interface CrudService<TEntity,TPrimaryKey extends Serializable,TRepository extends JpaRepository<TEntity,TPrimaryKey>> {

    List<TEntity> findAll();

    TEntity save(TEntity Tentity);

    List<TEntity> save(Iterable<TEntity> entities);

    void delete(TPrimaryKey id);

    void deleteAll(Iterable<TEntity> entities);

    Optional<TEntity> findById(TPrimaryKey id);

    long count();

    void flushRepository();
}
