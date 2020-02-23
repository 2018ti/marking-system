package com.xiaoman.dao;

import com.xiaoman.dao.marking;
import lombok.Data;

@Data
public class DoneWork {
    private String content;
    private Integer textId;
    private String leader;
    private Double agreeRate;
    private marking marking;
}
