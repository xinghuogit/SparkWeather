package com.sparkweather.utils;

public class ObjectUtils {

    public static <T> T checkNotNull(T content, Object errorMessage) {
        if (content == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return content;
    }
}
