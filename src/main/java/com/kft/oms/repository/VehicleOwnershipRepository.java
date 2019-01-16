package com.kft.oms.repository;

import com.kft.oms.domain.Vehicle;
import com.kft.oms.domain.VehicleOwnership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleOwnershipRepository extends JpaRepository<VehicleOwnership,Integer>{
    Optional<VehicleOwnership> getByLibreNo(String libreNo);
    List<VehicleOwnership> getByVehicle(Vehicle vehicle);
    List<VehicleOwnership> getByVehicleAndStatus(Vehicle vehicle, int status);
}
