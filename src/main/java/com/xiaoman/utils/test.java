package com.xiaoman.utils;

import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException {

        String  a="aaa";
        String b="aaa";
        System.out.println(a==b);
    }
    static boolean compare(String str1, String str2) {
        return ((str1 == str2) || (str1 != null && str1.equals(str2)));
    }

}
