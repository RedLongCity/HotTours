package com.smitsworks.redlo.hottours.tourdetails.convertor;

import android.content.Context;

import com.smitsworks.redlo.hottours.tourdetails.TourDetailItem;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by redlongcity on 08.04.2018.
 */

public abstract class AbstractConvertor {

    private SimpleDateFormat formatter;

    private Context context;

    protected void initContext(Context context) {
        this.context = context;
    }

    protected TourDetailItem createItem(String title, String value) {
        TourDetailItem result = null;
        if (title != null && value != null) {
            result = new TourDetailItem(title, value);
        }
        return result;
    }

    protected TourDetailItem createItem(String title, Integer value) {
        TourDetailItem result = null;
        if (title != null && value != null) {
            result = new TourDetailItem(title, String.valueOf(value));
        }
        return result;
    }

    protected TourDetailItem createItem(String title, Date value) {
        TourDetailItem result = null;
        if (title != null && value != null) {
            result = new TourDetailItem(title, formatDate(value));
        }
        return result;
    }

    protected String getString(int id) {
        return context.getString(id);
    }

    private String formatDate(Date date) {
        if (formatter == null) {
            formatter = new SimpleDateFormat("dd-MM-yyyy");
        }
        String result = new String();
        if (date != null) {
            result = formatter.format(date);
        }
        return result;
    }
}
