package com.kft.crud.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("ORGANIZATION")
public class Organization extends OffenderEntity {

    @NotBlank
    @Column(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }
}
