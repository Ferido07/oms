package com.kft.security.service;

import com.kft.crud.service.CrudServiceImpl;
import com.kft.security.domain.Role;
import com.kft.security.domain.User;
import com.kft.security.domain.UserRole;
import com.kft.security.dto.AdminUserView;
import com.kft.security.dto.OfficeUserView;
import com.kft.security.repository.RoleRepository;
import com.kft.security.repository.UserRepository;
import com.kft.security.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Msolomon on 5/21/2018.
 */
@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<User,Integer,UserRepository> implements UserService{


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public List<AdminUserView> adminUserList(Iterable<User> adminUser) {
        List<AdminUserView> adminUserViews= new ArrayList<>();
        for (User user : adminUser){
            AdminUserView adminUserView= new AdminUserView();

            adminUserView.setUserName(user.getUserName());
           // adminUserView.setPassword(user.getPassword());
            adminUserView.setIsLocked(user.getIsLocked());
            adminUserView.setId(user.getId());
            if(user.getPerson() != null){
                adminUserView.setFirstName(user.getPerson().getFirstName());
                adminUserView.setMiddleName(user.getPerson().getMiddleName());
                adminUserView.setLastName(user.getPerson().getLastName());

            }

            adminUserViews.add(adminUserView);
        }

        return adminUserViews;
    }

    @Override
    public List<OfficeUserView> officeUserList(Iterable<User> officeUser) {
        List<OfficeUserView> officeUserViews= new ArrayList<>();
        for (User user : officeUser){
            OfficeUserView officeUserView= new OfficeUserView();

            officeUserView.setUserName(user.getUserName());
            //officeUserView.setPassword(user.getPassword());
            officeUserView.setIsLocked(user.getIsLocked());
            officeUserView.setId(user.getId());
            if(user.getPerson() != null){
                officeUserView.setFirstName(user.getPerson().getFirstName());
                officeUserView.setMiddleName(user.getPerson().getMiddleName());
                officeUserView.setLastName(user.getPerson().getLastName());
                officeUserView.setGender(user.getPerson().getGender());
                officeUserView.setDateOfBirth(user.getPerson().getDateOfBirth());
                officeUserView.setIdentificationNumber(user.getPerson().getIdentificationNumber());



            }

            officeUserViews.add(officeUserView);
        }
        return officeUserViews;
    }


    @Override
    public User findByUserName(String username) {
        return repository.findByUserName(username);
    }

    @Override
    public List<User> findByRoleName(String roleName) {
        Role role = roleRepository.findByName(roleName);
        List<UserRole> userRoles=userRoleRepository.findByRoleId(role.getId());
        List<User> users= new ArrayList<>();
        for (UserRole userRole: userRoles){
            User user=repository.getOne(userRole.getUser().getId());
            users.add(user);
        }
        return users;
    }
}
