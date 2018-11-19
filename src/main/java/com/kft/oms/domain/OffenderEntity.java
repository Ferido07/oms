package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class OffenderEntity implements Serializable{

    @EmbeddedId
    private OffenderEntityKey entityKey;

    public OffenderEntityKey getEntityKey() {
        return entityKey;
    }

    public void setEntityKey(OffenderEntityKey entityKey) {
        this.entityKey = entityKey;
    }

}

