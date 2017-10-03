package com.cristph.template.utils;

import static java.lang.Character.isWhitespace;


public class StringUtils {

    public static boolean isEmptyOrWhitespaceOnly(String str) {
        if (str != null && str.length() != 0) {
            int length = str.length();

            for (int i = 0; i < length; ++i) {
                if (!isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
