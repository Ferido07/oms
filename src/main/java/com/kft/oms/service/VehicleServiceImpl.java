package com.kft.oms.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends CrudServiceImpl<Vehicle,Integer,VehicleRepository> implements VehicleService {
}
