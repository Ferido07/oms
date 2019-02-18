package com.kft.oms.domain;

import com.kft.crud.domain.AbstractEntity;
import com.kft.oms.constants.OffenderType;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OffenceCodeSection  extends AbstractEntity{
    // The next 2 are only added for convenience purposes to the user.
    //Header like 9.3.3
    @Column(length = 9, nullable = false, unique = true)
    private String headerLabel;

    //like አሽከርካሪ ረዳትን የሚያስጠይቁ that describes the section.
    @Column(nullable = false, unique = true)
    private String header;

    //Driver, Association, VehicleOwner,... maybe use an enum or interface or Abstract superclass
    //Canceled: maybe add a column or foreign key to reference the entity_type from OffenderEntity
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OffenderType offenderType;

    @OneToMany(mappedBy = "section")
    private Set<OffenceCode> offenceCodes;


    public String getHeaderLabel() {
        return headerLabel;
    }

    public void setHeaderLabel(String headerLabel) {
        this.headerLabel = headerLabel;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public OffenderType getOffenderType() {
        return offenderType;
    }

    public void setOffenderType(OffenderType offenderType) {
        this.offenderType = offenderType;
    }

    public Set<OffenceCode> getOffenceCodes() {
        return offenceCodes;
    }

    public void setOffenceCodes(Set<OffenceCode> offenceCodes) {
        this.offenceCodes = offenceCodes;
    }
}
