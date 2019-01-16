package com.kft.oms.service;

import com.kft.crud.service.CrudService;
import com.kft.oms.domain.Vehicle;
import com.kft.oms.domain.VehicleOwnership;
import com.kft.oms.repository.VehicleOwnershipRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleOwnershipService extends CrudService<VehicleOwnership,Integer,VehicleOwnershipRepository> {
    Optional<VehicleOwnership> getByLibreNo(String libreNo);
    List<VehicleOwnership> getByVehicle(Vehicle vehicle);
}
