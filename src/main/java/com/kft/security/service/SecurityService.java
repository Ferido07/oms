package com.kft.security.service;

import com.kft.security.domain.User;
import com.kft.security.dto.ChangePassword;
import com.kft.security.dto.NewOfficeUser;
import com.kft.security.dto.NewUser;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Msolomon on 5/18/2018.
 */
public interface SecurityService {

    PasswordEncoder getPasswordEncoder();

    void createAdminUser(NewUser newUser);

    org.springframework.security.core.userdetails.User getCurrentUserFromCtx();

    User getCurrentUser();

    void createOfficeUser(NewOfficeUser newOfficeUser);

    void lockUser(User user);

    void unLockUser(User user);

    void changePassword(ChangePassword changePassword);

    boolean resetPassword(User user);

    boolean isLogged();


}
