package com.kft.oms.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AssociationModel {

    private Integer id;

    @Size(max=150)
    @NotBlank
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
