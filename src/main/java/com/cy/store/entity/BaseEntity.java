package com.cy.store.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 */
public class BaseEntity implements Serializable {
    private String createdUser;//创建人
    private Date createdTime;//创建时间
    private String modifiedUser;//修改人
    private Date modifiedTime;//修改时间

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        if (getCreatedUser() != null ? !getCreatedUser().equals(that.getCreatedUser()) : that.getCreatedUser() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() != null)
            return false;
        if (getModifiedUser() != null ? !getModifiedUser().equals(that.getModifiedUser()) : that.getModifiedUser() != null)
            return false;
        return getModifiedTime() != null ? getModifiedTime().equals(that.getModifiedTime()) : that.getModifiedTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getCreatedUser() != null ? getCreatedUser().hashCode() : 0;
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getModifiedUser() != null ? getModifiedUser().hashCode() : 0);
        result = 31 * result + (getModifiedTime() != null ? getModifiedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
