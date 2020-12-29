package com.example.utils;

public class ChangeLengthTextUtil {

    public static String changeLength(String str) {
        if (str.length() > 20)
            return str.substring(0, 20);
        return str;
    }

}
