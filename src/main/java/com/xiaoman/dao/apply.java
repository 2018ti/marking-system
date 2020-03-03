package com.xiaoman.dao;

public class apply {
    private Integer applyId;

    private String userName;

    private String applyRole;

    private String status;

    public apply(Integer applyId, String userName, String applyRole, String status) {
        this.applyId = applyId;
        this.userName = userName;
        this.applyRole = applyRole;
        this.status = status;
    }

    public apply() {
        super();
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getApplyRole() {
        return applyRole;
    }

    public void setApplyRole(String applyRole) {
        this.applyRole = applyRole == null ? null : applyRole.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}