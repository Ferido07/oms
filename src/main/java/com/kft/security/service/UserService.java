package com.kft.security.service;

import com.kft.crud.service.CrudService;
import com.kft.security.domain.User;
import com.kft.security.dto.AdminUserView;
import com.kft.security.dto.OfficeUserView;
import com.kft.security.repository.UserRepository;

import java.util.List;

/**
 * Created by Msolomon on 5/21/2018.
 */
public interface UserService extends CrudService<User,Integer,UserRepository> {

    List<AdminUserView> adminUserList(Iterable<User> adminUser);

    List<OfficeUserView> officeUserList(Iterable<User> officeUser);

    User findByUserName(String username);

    List<User> findByRoleName(String roleName);
}
