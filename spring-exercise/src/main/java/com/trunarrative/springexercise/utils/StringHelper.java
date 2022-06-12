package com.trunarrative.springexercise.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @className: StringUtils
 * @author: wenjie.xia
 * @description: string operation
 * @date: 11/06/2022 01:02
 * @version: 1.0
 */
public class StringHelper {

    private static String DIGIT_REGEX = "[0-9]+";

    /**
     * check string whether is numeric
     * @param str target string
     * @return whether or not
     */
    public static boolean isNumeric(String str) {
        return str.matches(DIGIT_REGEX);
    }

    /**
     * regular expression matching
     * @param string
     * @param regex
     * @return extracted content
     */
    public static String getPatternCode(String string, String regex){
        Pattern pattern = compile(regex);
        Matcher m = pattern.matcher(string);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
