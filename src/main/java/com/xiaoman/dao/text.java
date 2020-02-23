package com.xiaoman.dao;

import lombok.Data;

import java.util.Date;

@Data
public class text {
    private Integer textId;

    private String content;

    private Integer markingId;

    private String leader;
    
    private Date loadTime;

    private Double agreeRate;
}