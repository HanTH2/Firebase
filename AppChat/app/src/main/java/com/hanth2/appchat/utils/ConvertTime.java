package com.hanth2.appchat.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zero on 15/08/2016.
 */
public class ConvertTime {
    public static String convertTimestamp(long time) {
//        Date date = new Date(time);
//        String result = new SimpleDateFormat("HH:mm").format(date);
//        if (result.indexOf(0) == 0) {
//            result = result.substring(1, result.length());
//        }
//        return result;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));

        String localTime = date.format(currentLocalTime);

        return localTime;
    }
}
