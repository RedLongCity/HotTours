package com.smitsworks.redlo.hottours.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 04.10.2017.
 * class for providing Tour from server
 */

public class TourProvider implements Provider,Urls {


    @Override
    public Response provide() {
        return null;
    }

    public Response provide(Integer id){
        return HttpUtils.getDataByURL(TOUR_URL+"/"+id);
    }

    public Response provide(Request request){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return HttpUtils.postData(REQUEST_TOUR_URL,gson.toJson(request));
    }
}
