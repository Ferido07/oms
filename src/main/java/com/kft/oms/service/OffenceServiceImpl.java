package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.Offence;
import com.kft.oms.repository.OffenceRepository;
import org.springframework.stereotype.Service;

@Service
public class OffenceServiceImpl extends CrudServiceImpl<Offence,Integer,OffenceRepository> implements OffenceService {
    public OffenceServiceImpl(OffenceRepository repository) {
        super(repository);
    }
}
