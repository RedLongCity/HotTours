package com.smitsworks.redlo.hottours.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by redlongcity on 28.02.2018.
 */

public class DateUtils {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private static final Calendar calendar = Calendar.getInstance();

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return formatter.format(date);
    }

    public static Date getCurrentDate() {
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getDateAfterWeek() {
        calendar.add(Calendar.DATE, 7);
        return new Date(calendar.getTimeInMillis());
    }
}
