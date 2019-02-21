package com.kft.oms.repository;

import com.kft.oms.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    Optional<Vehicle> findByPlateNo(String plateNo);
    List<Vehicle> findAllByPlateNoStartingWith(String plateNo);
    Page<Vehicle> findAllByPlateNoStartingWith(String plateNo, Pageable pageable);
    Optional<Vehicle> findVehicleBySideNo(String sideNo);
}
