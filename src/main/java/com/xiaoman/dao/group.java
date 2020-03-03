package com.xiaoman.dao;

import lombok.Data;

@Data
public class group {

    private Integer groupId;

    private String leader;

    private String groupName;

    public group(Integer groupId, String leader, String groupName) {
        this.groupId = groupId;
        this.leader = leader;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}