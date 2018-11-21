package com.kft.crud.repository;

import com.kft.crud.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
}
