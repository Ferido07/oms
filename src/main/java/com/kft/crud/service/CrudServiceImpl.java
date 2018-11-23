package com.kft.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Msolomon on 5/21/2018.
 */
public abstract class CrudServiceImpl<TEntity,TPrimaryKey extends Serializable,TRepository extends JpaRepository<TEntity,TPrimaryKey>>
        implements CrudService<TEntity,TPrimaryKey,TRepository> {

    protected final TRepository repository;

    @Autowired
    public CrudServiceImpl(TRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public TEntity save(TEntity Tentity) {
        return repository.save(Tentity);
    }

    @Override
    public List<TEntity> save(Iterable<TEntity> entities) {
        return repository.saveAll(entities) ;
    }

    @Override
    public void delete(TPrimaryKey id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<TEntity> entities) {
        repository.deleteInBatch(entities);
    }

    @Override
    public Optional<TEntity> findById(TPrimaryKey id) {
        return  repository.findById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void flushRepository() {
        repository.flush();
    }
}
