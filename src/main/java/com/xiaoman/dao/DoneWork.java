package com.xiaoman.dao;

import com.xiaoman.dao.marking;

public class DoneWork {
    private String content;
    private Integer textId;
    private String leader;
    private marking marking;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public com.xiaoman.dao.marking getMarking() {
        return marking;
    }

    public void setMarking(com.xiaoman.dao.marking marking) {
        this.marking = marking;
    }

    @Override
    public String toString() {
        return "DoneWork{" +
                "content='" + content + '\'' +
                ", textId=" + textId +
                ", leader='" + leader + '\'' +
                ", marking=" + marking +
                '}';
    }
}
