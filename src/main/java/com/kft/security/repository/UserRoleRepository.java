package com.kft.security.repository;

import com.kft.security.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    List<UserRole> findByRoleId(Integer id);

}
