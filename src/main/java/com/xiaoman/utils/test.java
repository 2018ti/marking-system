package com.xiaoman.utils;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        xmlUtil.TextToXml("会见会谈","会见","李克强","全体代表","6月12日上午",
        "北京",2,3,"6月12日上午，国务院总理李克强在北京会见出席第二届世界华侨华人工商大会的全体代表，指出中国将推动新一轮高水平对外开放，进一步放宽外资市场准入，创造更加公平便利的营商环境，对外开放的大门会越开越大。希望华商充分发挥联通中外、汇聚资源的独特优势，积极参与“一带一路”建设和中国同世界各国的经贸合作，拓展三方合作，更好实现互利共赢。");
    }
}
