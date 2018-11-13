package com.kft.crud.repository;

import com.kft.crud.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
