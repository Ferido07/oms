package com.kft.security.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Msolomon on 5/25/2018.
 */
public class RoleModel {

    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private Integer isActive;

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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
