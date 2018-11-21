package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.OffenceCode;
import com.kft.oms.repository.OffenceCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class OffenceCodeServiceImpl extends CrudServiceImpl<OffenceCode,Integer,OffenceCodeRepository> implements OffenceCodeService {
}
