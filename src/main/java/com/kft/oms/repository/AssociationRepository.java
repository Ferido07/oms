package com.kft.oms.repository;

import com.kft.oms.domain.Association;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociationRepository extends JpaRepository<Association,Integer> {
    Optional<Association> findByName(String name);
}
