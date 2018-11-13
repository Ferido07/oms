package com.kft.security.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.security.domain.Role;
import com.kft.security.dto.RoleModel;
import com.kft.security.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Service
@Transactional
public class RoleServiceImpl extends CrudServiceImpl<Role,Integer,RoleRepository> implements RoleService {


    @Override
    public Role create(RoleModel model) {

        Role role= new Role();

        role.setName(model.getName());
        role.setDescription(model.getDescription());
        role.setActive(true);

        return repository.save(role);
    }

    @Override
    public RoleModel edit(Integer id) {
        Optional<Role> role = repository.findById(id);
        RoleModel model = new RoleModel();
        role.ifPresent(role1 -> {
            model.setId(role1.getId());
            model.setName(role1.getName());
            model.setDescription(role1.getDescription());
            model.setIsActive( role1.getIsActive() ? 1 : 0);
        });

        return model;
    }

    @Override
    public Role update(RoleModel model) {
       Optional<Role> role = repository.findById(model.getId());
        final Role[] updatedRole = new Role[1];
       role.ifPresent(role1 -> {
           role1.setName(model.getName());
           role1.setDescription(model.getDescription());
           role1.setActive(model.getIsActive()== 1? true : false);
           updatedRole[0] = repository.save(role1);
       });

        return updatedRole[0];
    }
}
