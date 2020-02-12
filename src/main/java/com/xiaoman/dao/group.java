package com.xiaoman.dao;

import lombok.Data;

@Data
public class group {
    private Integer groupId;

    private String leader;

    public group(Integer groupId, String leader) {
        this.groupId = groupId;
        this.leader = leader;
    }

    public group() {
        super();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }
}