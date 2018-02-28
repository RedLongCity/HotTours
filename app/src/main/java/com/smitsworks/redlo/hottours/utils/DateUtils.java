package com.smitsworks.redlo.hottours.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 28.02.2018.
 */

public class DateUtils {

    public static String formatDate(Date date){
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}
