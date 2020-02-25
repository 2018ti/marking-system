package com.xiaoman.dto;

import lombok.Data;

@Data
public class agreementMarking {
    private String trigger;
    private String marking1;
    private String marking2;
    private String marking3;
    private String marking4;
    private double agreeRate;
    private String content;

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

    public double getAgreeRate() {
        return agreeRate;
    }

    public void setAgreeRate(double agreeRate) {
        this.agreeRate = agreeRate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
