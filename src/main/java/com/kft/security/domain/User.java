package com.kft.security.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.crud.domain.Person;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Msolomon on 5/17/2018.
 */
@Entity
public class User extends AbstractEntity {

    private static final long serialVersionUID = 2353528370345499815L;

    @ManyToOne
    private Person person;

    private String userName;

    private String password;

    private Integer isActive;

    private Integer isLoggedIn;

    private Integer failedLoginAttempts;

    private Integer isLocked;

    private Date lastLoginTimestamp;

    private Integer firstLogin;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {
        super();
        failedLoginAttempts = 0;
        isLocked = 0;
        isLoggedIn = 0;
        isActive = 1;
        firstLogin= 0;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Integer isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Date lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public Integer getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Integer firstLogin) {
        this.firstLogin = firstLogin;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(this.roles);
        return authorities;

    }
}
