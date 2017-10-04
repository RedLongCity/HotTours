package com.smitsworks.redlo.hottours.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by redlongcity on 03.10.2017.
 * class for incapsulating work with http
 */

public class HttpUtils {

    public static final String TAG = "TAG";

    public static Response getDataByURL(String url){

        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().
                    url(url).build();
            return client.newCall(request).execute();
        } catch (@NonNull IOException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
