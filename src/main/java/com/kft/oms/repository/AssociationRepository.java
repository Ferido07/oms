package com.kft.oms.repository;

import com.kft.oms.domain.Association;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociationRepository extends JpaRepository<Association,Integer> {
    Optional<Association> findByName(String name);
    Page<Association> findByNameStartingWith(String name, Pageable pageable);
}
