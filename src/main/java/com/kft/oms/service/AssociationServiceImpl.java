package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.config.Mapper;
import com.kft.oms.domain.Association;
import com.kft.oms.model.AssociationModel;
import com.kft.oms.repository.AssociationRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
 class AssociationServiceImpl extends CrudServiceImpl<Association,Integer,AssociationRepository> implements AssociationService {

    private final Mapper mapper;

    public AssociationServiceImpl(AssociationRepository repository, Mapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public Optional<Association> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<AssociationModel> findByIdAsAssociationModel(Integer id) {
        return repository.findById(id).map(association -> mapper.map(association,AssociationModel.class));
    }

    @Override
    public Optional<AssociationModel> findByNameAsAssociationModel(String name) {
        return repository.findByName(name).map(association -> mapper.map(association, AssociationModel.class));
    }

    @Override
    public Page<AssociationModel> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(association -> mapper.map(association,AssociationModel.class));
    }

    @Override
    public Page<AssociationModel> findByNameStartingWith(String name, Pageable pageable) {
        return repository.findByNameStartingWith(name, pageable).map(association -> mapper.map(association,AssociationModel.class));
    }
}
