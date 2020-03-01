package com.xiaoman.dto;


import lombok.Data;

//已办数据结果集
@Data
public class DoneWorkResult {
    private String content;
    private String leader;
    private String title;
    private Integer textId;
    private Integer markingId;
    private String eventType;
    private String trigger;
    private String marking1;
    private String marking2;
    private String marking3;
    private String marking4;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Integer getMarkingId() {
        return markingId;
    }

    public void setMarkingId(Integer markingId) {
        this.markingId = markingId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getMarking1() {
        return marking1;
    }

    public void setMarking1(String marking1) {
        this.marking1 = marking1;
    }

    public String getMarking2() {
        return marking2;
    }

    public void setMarking2(String marking2) {
        this.marking2 = marking2;
    }

    public String getMarking3() {
        return marking3;
    }

    public void setMarking3(String marking3) {
        this.marking3 = marking3;
    }

    public String getMarking4() {
        return marking4;
    }

    public void setMarking4(String marking4) {
        this.marking4 = marking4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DoneWorkResult{" +
                "content='" + content + '\'' +
                ", leader='" + leader + '\'' +
                ", textId=" + textId +
                ", markingId=" + markingId +
                ", eventType='" + eventType + '\'' +
                ", trigger='" + trigger + '\'' +
                ", marking1='" + marking1 + '\'' +
                ", marking2='" + marking2 + '\'' +
                ", marking3='" + marking3 + '\'' +
                ", marking4='" + marking4 + '\'' +
                '}';
    }
}
