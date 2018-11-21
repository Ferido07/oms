package com.kft.oms.repository;

import com.kft.oms.domain.Offence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffenceRepository extends JpaRepository<Offence,Integer> {
}
