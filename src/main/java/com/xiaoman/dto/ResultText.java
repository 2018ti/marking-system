package com.xiaoman.dto;


public class ResultText {
    private Integer textId;

    private String content;

    private Integer markingId;

    private String leader;

    private String loadTime;

    private Double agreeRate;

    private String title;

    public ResultText(Integer textId, String content, Integer markingId, String leader, String loadTime, Double agreeRate, String title) {
        this.textId = textId;
        this.content = content;
        this.markingId = markingId;
        this.leader = leader;
        this.loadTime = loadTime;
        this.agreeRate = agreeRate;
        this.title = title;
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
        this.content = content;
    }

    public Integer getMarkingId() {
        return markingId;
    }

    public void setMarkingId(Integer markingId) {
        this.markingId = markingId;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(String loadTime) {
        this.loadTime = loadTime;
    }

    public Double getAgreeRate() {
        return agreeRate;
    }

    public void setAgreeRate(Double agreeRate) {
        this.agreeRate = agreeRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
