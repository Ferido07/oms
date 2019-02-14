package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Association;
import com.kft.oms.model.AssociationModel;
import com.kft.oms.repository.AssociationRepository;

import java.util.Optional;

public interface AssociationService extends CrudService<Association,Integer,AssociationRepository> {
    Optional<Association> findByName(String name);
    Optional<AssociationModel> findByIdAsAssociationModel(Integer id);
    Optional<AssociationModel> findByNameAsAssociationModel(String name);
}
