package com.kft.crud.service;

import com.kft.crud.domain.Person;
import com.kft.crud.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Service
@Transactional
public class PersonServiceImpl extends CrudServiceImpl<Person,Integer,PersonRepository> implements PersonService {
}
