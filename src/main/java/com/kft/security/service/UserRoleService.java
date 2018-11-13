package com.kft.security.service;

import com.kft.crud.service.CrudService;
import com.kft.security.domain.UserRole;
import com.kft.security.repository.UserRoleRepository;

/**
 * Created by Msolomon on 5/21/2018.
 */
public interface UserRoleService extends CrudService<UserRole,Integer,UserRoleRepository> {
}
