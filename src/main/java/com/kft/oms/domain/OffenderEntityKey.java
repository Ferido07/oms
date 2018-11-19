package com.kft.oms.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OffenderEntityKey implements Serializable {
    private Integer offenderId;
    private String offenderType;



    public Integer getOffenderId() {
        return offenderId;
    }

    public void setOffenderId(Integer offenderId) {
        this.offenderId = offenderId;
    }

    public String getOffenderType() {
        return offenderType;
    }

    public void setOffenderType(String offenderType) {
        this.offenderType = offenderType;
    }

}