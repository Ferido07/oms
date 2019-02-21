package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Association;
import com.kft.oms.model.AssociationModel;
import com.kft.oms.repository.AssociationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AssociationService extends CrudService<Association,Integer,AssociationRepository> {
    Optional<Association> findByName(String name);
    Optional<AssociationModel> findByIdAsAssociationModel(Integer id);
    Optional<AssociationModel> findByNameAsAssociationModel(String name);
    Page<AssociationModel> findAll(Pageable pageable);
    Page<AssociationModel> findByNameStartingWith(String name, Pageable pageable);
}
