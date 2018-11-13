package com.kft.security.domain;

import com.kft.crud.domain.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

/**
 * Created by Msolomon on 5/17/2018.
 */
@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

    private String name;

    private String description;

    private boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
