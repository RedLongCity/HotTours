package com.smitsworks.redlo.hottours.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 18.10.2017.
 * posts Request to server
 */

public class RequestProvider implements Provider,Urls {
    @Override
    public Response provide() {
        return null;
    }

    public Response provide(Request request){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return HttpUtils.postData(REQUEST_TOUR_URL,gson.toJson(request));
    }
}
