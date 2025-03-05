package com.thanhnt10.dodobook.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonUtil {

    public static String requestIdAtNow() {
        String pattern = "yyyy/MM/dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        todayAsString = todayAsString
                .replace("/", "")
                .replace(":", "")
                .replace(" ", "");

        String random = RandomStringUtils.randomNumeric(6);

        return todayAsString + random;
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static boolean nonNullOrEmpty(Object[] array) {
        return !isNullOrEmpty(array);
    }

    public static String trimKeyword(String keyword) {
        if(keyword == null){
            return null;
        }

        var strTrimmed = keyword.trim();
        if (isNullOrBlank(strTrimmed)) {
            return null;
        }
        else {
            return strTrimmed;
        }
    }

    // Tim kiem tuong doi
    public static String searchRelative(String keyword){
        if(isNullOrEmpty(keyword)){
            return keyword;
        }
        return "%" + keyword.toLowerCase() + "%";
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true; // Nếu parse thành công
        } catch (NumberFormatException e) {
            return false; // Nếu không parse được
        }
    }

    public static boolean isNumeric(String str) {
        return NumberUtils.isCreatable(str);
    }

    public static <T> List<T> getDifference(List<T> list1, List<T> list2) {
        List<T> diff1 = new ArrayList<>(list1);
        List<T> diff2 = new ArrayList<>(list2);

        diff1.removeAll(list2);
        diff2.removeAll(list1);

        List<T> result = new ArrayList<>();
        result.addAll(diff1);
        result.addAll(diff2);

        return result;
    }
}
