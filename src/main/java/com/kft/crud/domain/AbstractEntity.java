package com.kft.crud.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Msolomon on 5/17/2018.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable= false)
    private Integer id;

    @Column(name = "VERSION")
    @Version
    private Integer version = 0;

    @Column(name = "DML_FLAG")
    private Integer dmlFlag;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDmlFlag() {
        return dmlFlag;
    }

    public void setDmlFlag(Integer dmlFlag) {
        this.dmlFlag = dmlFlag;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new Date();
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null)
            this.updatedBy = currentAuth.getName();
        else
            this.updatedBy = "SYSTEM";
    }
    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null)
            this.createdBy = currentAuth.getName();
        else
            this.createdBy = "SYSTEM";
    }
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        AbstractEntity other = (AbstractEntity) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }
}

