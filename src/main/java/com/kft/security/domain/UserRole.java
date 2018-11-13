package com.kft.security.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Msolomon on 5/18/2018.
 */
@Entity
public class UserRole extends AbstractEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
