package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.model.VehicleModel;
import com.kft.oms.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleService extends CrudService<Vehicle,Integer,VehicleRepository> {

    Optional<VehicleModel> findByPlateNo(String plateNo);
    List<VehicleModel> findByPlateNoStartingWith(String plateNo);
    Optional<VehicleModel> findBySideNo(String sideNo);
}
