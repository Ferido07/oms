package com.kft.security.repository;

import com.kft.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String name);
}
