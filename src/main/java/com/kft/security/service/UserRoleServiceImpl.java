package com.kft.security.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.security.domain.UserRole;
import com.kft.security.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends CrudServiceImpl<UserRole,Integer,UserRoleRepository> implements UserRoleService{
    public UserRoleServiceImpl(UserRoleRepository repository) {
        super(repository);
    }
}
