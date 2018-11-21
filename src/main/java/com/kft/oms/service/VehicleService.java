package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.repository.VehicleRepository;

public interface VehicleService extends CrudService<Vehicle,Integer,VehicleRepository> {
}
