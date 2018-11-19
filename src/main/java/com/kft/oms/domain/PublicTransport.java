package com.kft.oms.domain;

import javax.persistence.Entity;

@Entity
public class PublicTransport extends Vehicle {

    private Integer seatingCapacity;

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
