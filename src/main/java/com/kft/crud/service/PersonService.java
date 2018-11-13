package com.kft.crud.service;

import com.kft.crud.domain.Person;
import com.kft.crud.repository.PersonRepository;

/**
 * Created by Msolomon on 5/21/2018.
 */
public interface PersonService extends CrudService<Person,Integer,PersonRepository> {
}
