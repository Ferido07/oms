package com.kft.security.service;

import com.kft.crud.domain.Person;
import com.kft.crud.service.PersonService;
import com.kft.security.constants.DefaultPassword;
import com.kft.security.domain.Role;
import com.kft.security.domain.User;
import com.kft.security.domain.UserRole;
import com.kft.security.dto.ChangePassword;
import com.kft.security.dto.NewOfficeUser;
import com.kft.security.dto.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Service
public class SecurityServiceImpl  implements SecurityService{

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final PersonService personService;

    private final UserRoleService userRoleService;

    @Autowired
    public SecurityServiceImpl(PasswordEncoder passwordEncoder, UserService userService, PersonService personService, UserRoleService userRoleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.personService = personService;
        this.userRoleService = userRoleService;
    }


    @Override
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public void createAdminUser(NewUser newUser) {



        Person person = new Person();
        person.setFirstName(newUser.getFirstName());
        person.setMiddleName(newUser.getMiddleName());
        person.setLastName(newUser.getLastName());
        Person savedPerson=personService.save(person);

        User user= new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setIsActive(1);
        user.setPerson(savedPerson);
        User savedUser= userService.save(user);

//        if (newUser.getRoles().size()== 0){
//        }

        for (Role role: newUser.getRoles()){

            UserRole userRole= new UserRole();
            userRole.setUser(savedUser);
            userRole.setRole(role);

            userRoleService.save(userRole);
        }


    }



    @Override
    public org.springframework.security.core.userdetails.User getCurrentUserFromCtx() {

        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User currentUser= getCurrentUserFromCtx();

        return userService.findByUserName(currentUser.getUsername());
    }

    @Override
    public void createOfficeUser(NewOfficeUser newOfficeUser) {


        Person person = new Person();
        person.setFirstName(newOfficeUser.getFirstName());
        person.setMiddleName(newOfficeUser.getMiddleName());
        person.setLastName(newOfficeUser.getLastName());
        person.setDateOfBirth(newOfficeUser.getDateOfBirth());
        person.setGender(newOfficeUser.getGender().toString());
        Person savedPerson= personService.save(person);

        User user= new User();
        user.setUserName(newOfficeUser.getUserName());
        user.setPassword(passwordEncoder.encode(newOfficeUser.getPassword()));
        user.setIsActive(1);
        user.setPerson(savedPerson);
        User savedUser=userService.save(user);

        for (Role role: newOfficeUser.getRoles()){

            UserRole userRole= new UserRole();
            userRole.setUser(savedUser);
            userRole.setRole(role);

            userRoleService.save(userRole);
        }

    }

    @Override
    public void lockUser(User user) {
        user.setIsLocked(1);
        userService.save(user);
    }

    @Override
    public void unLockUser(User user) {
        user.setIsLocked(0);
        userService.save(user);
    }

    @Override
    public void changePassword(ChangePassword changePassword) {

        User currentUser= getCurrentUser();
        currentUser.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        currentUser.setFirstLogin(1);
        userService.save(currentUser);
    }

    @Override
    public boolean resetPassword(User user) {
        try {
            user.setPassword(passwordEncoder.encode(DefaultPassword.DEFAULT_PASSWORD));
            user.setFirstLogin(0);
            userService.save(user);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean isLogged() {
        try{
            org.springframework.security.core.userdetails.User currentUser=getCurrentUserFromCtx();
            User isUser=  userService.findByUserName(currentUser.getUsername());
            return isUser != null;
        }catch (Exception e){
            return false;
        }

    }
}
