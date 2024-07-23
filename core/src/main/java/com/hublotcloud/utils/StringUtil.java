package com.hublotcloud.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.DigestUtils;

import com.hublotcloud.Constants;

/**
 * StringUtil
 */
public class StringUtil {

    /**
     * string转list
     */
    public static List<String> stringToList(String str, String split) {
        String[] stringArray = str.split(split);
        return Arrays.asList(stringArray);
    }

    public static String md5String(String target) {
        return DigestUtils.md5DigestAsHex((target + Constants.MD5_SALT).getBytes());
    }

}
