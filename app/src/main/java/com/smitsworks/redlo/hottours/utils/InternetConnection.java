package com.smitsworks.redlo.hottours.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
/**
 * Created by redlongcity on 03.10.2017.
 * class for checking internet connection
 */

public class InternetConnection {

    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */
    public static boolean checkConnection(@NonNull Context context) {
        return  ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
