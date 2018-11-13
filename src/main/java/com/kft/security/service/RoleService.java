package com.kft.security.service;

import com.kft.crud.service.CrudService;
import com.kft.security.domain.Role;
import com.kft.security.dto.RoleModel;
import com.kft.security.repository.RoleRepository;

/**
 * Created by Msolomon on 5/21/2018.
 */
public interface RoleService extends CrudService<Role,Integer,RoleRepository> {
    Role create(RoleModel model);

    RoleModel edit(Integer id);

    Role update(RoleModel model);
}
