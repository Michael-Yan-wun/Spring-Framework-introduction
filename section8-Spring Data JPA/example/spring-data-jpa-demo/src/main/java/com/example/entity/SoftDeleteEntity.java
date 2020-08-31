package com.example.entity;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class SoftDeleteEntity extends BaseEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Taipei")
    protected Date createTime;

    protected String createUser;
    protected String createUsername;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Taipei")
    protected Date updateTime;

    protected String updateUser;
    protected String updateUsername;
    protected boolean deleted = false;

    /**
     * 建立時間
     */
    @Column(name = "create_time", nullable = false, updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 建立人員-姓名
     */
    @Column(name = "create_user", nullable = false, updatable = false, length = 50)
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 建立人員-帳號
     */
    @Column(name = "create_username", nullable = false, updatable = false, length = 50)
    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    /**
     * 修改時間
     */
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改人員-姓名
     */
    @Column(name = "update_user", length = 50)
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 修改人員-帳號
     */
    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

    /**
     * 刪除狀態
     */
    @Column(name = "deleted", nullable = false)
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * 刪除狀態
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date(System.currentTimeMillis());
        if (createTime == null)
            setCreateTime(now);
        if (updateTime == null)
            setUpdateTime(now);

        setCreateUser("System");

    }

    @PreUpdate
    public void preUpdate() {
        if (updateTime == null)
            setUpdateTime(new Date());


        setUpdateUser("System");
        setUpdateUsername("System");
    }
}