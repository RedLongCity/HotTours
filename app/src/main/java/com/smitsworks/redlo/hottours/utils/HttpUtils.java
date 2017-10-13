package com.smitsworks.redlo.hottours.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by redlongcity on 03.10.2017.
 * class for incapsulating work with http
 */

public class HttpUtils {

    public static final String TAG = "TAG";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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

    public static Response postData(String url,Object data){
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().
                    url(url).
                    post(RequestBody.create(JSON,String.valueOf(data))).
                    build();

           return client.newCall(request).execute();

        } catch (@NonNull IOException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

}
