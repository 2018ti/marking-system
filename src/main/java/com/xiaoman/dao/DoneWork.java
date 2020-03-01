package com.xiaoman.dao;

import com.xiaoman.dao.marking;
import lombok.Data;

@Data
public class DoneWork {
    private String content;
    private Integer textId;
    private String leader;
    private Double agreeRate;
    private String title;
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

    public Double getAgreeRate() {
        return agreeRate;
    }

    public void setAgreeRate(Double agreeRate) {
        this.agreeRate = agreeRate;
    }

    public com.xiaoman.dao.marking getMarking() {
        return marking;
    }

    public void setMarking(com.xiaoman.dao.marking marking) {
        this.marking = marking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
