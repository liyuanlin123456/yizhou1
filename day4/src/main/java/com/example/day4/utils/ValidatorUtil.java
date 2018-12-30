package com.example.day4.utils;

import java.util.regex.Pattern;

public class ValidatorUtil {
    //验证手机号
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
    //验证密码
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{1,12}$";
    //校验密码
    public static boolean isPassword(String password){
        return Pattern.matches(REGEX_PASSWORD,password);
    }
    //校验手机号
    public static boolean isMobile(String mobile){
        return Pattern.matches(REGEX_MOBILE,mobile);
    }
}
