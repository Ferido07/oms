package com.kft.security.repository;

import com.kft.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String username);
}
