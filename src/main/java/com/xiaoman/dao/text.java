package com.xiaoman.dao;

import lombok.Data;

@Data
public class text {
    private Integer textId;

    private String content;

    private String marking;

    private String leader;

    public text(Integer textId, String content, String marking, String leader) {
        this.textId = textId;
        this.content = content;
        this.marking = marking;
        this.leader = leader;
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
}