package com.kft.security.service;

import com.kft.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user= userService.findByUserName(username);

        if (user== null){
            throw new UsernameNotFoundException(String.format("user %s nor found",username));

        }

        List<GrantedAuthority> authority= user.getAuthorities();
        return new org.springframework.security.core.userdetails.User(
               user.getUserName(),
                user.getPassword(),
                user.getIsActive()== 1,
                true,
                true,
                user.getIsLocked() == 0,
                user.getAuthorities()
        );
    }
}
