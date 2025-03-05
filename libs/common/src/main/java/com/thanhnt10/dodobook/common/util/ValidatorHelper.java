package com.thanhnt10.dodobook.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorHelper {
    private static final String URL_REGEX = "^(https?:\\/\\/)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(\\/[^\\s]*)?$";

    public static boolean isValidUrl(String url) {
        if(url == null) return true;

        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
