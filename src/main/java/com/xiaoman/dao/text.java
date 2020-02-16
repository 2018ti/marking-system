package com.xiaoman.dao;

import java.util.Date;

public class text {
    private Integer textId;

    private String content;

    private String marking;

    private String leader;

    private Date loadTime;

    public text(Integer textId, String content, String marking, String leader, Date loadTime) {
        this.textId = textId;
        this.content = content;
        this.marking = marking;
        this.leader = leader;
        this.loadTime = loadTime;
    }

    public text() {
        super();
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking == null ? null : marking.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }
}