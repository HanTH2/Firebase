package com.hanth2.appchat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zero on 15/08/2016.
 */
public class ConvertTime {
    public static String convertTimestamp(long time) {
        Date date = new Date(time);
        String result = new SimpleDateFormat("HH:mm").format(date);
        if (result.indexOf(0) == 0) {
            result = result.substring(1, result.length());
        }
        return result;
    }
}
