package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Offence;
import com.kft.oms.repository.OffenceRepository;

public interface OffenceService extends CrudService<Offence,Integer,OffenceRepository> {
}
