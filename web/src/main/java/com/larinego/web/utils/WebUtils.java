package com.larinego.web.utils;

public class WebUtils {

    static final String[] VALID_LOCAL_URLS = {"ru","en","RU","EN"};

    public static boolean isLocaleValid(String locale) {
        for (int i = 0; i < VALID_LOCAL_URLS.length; i++){
            if(VALID_LOCAL_URLS[i].equals(locale)){
                return true;
            }
        }
        return false;
    }
}
