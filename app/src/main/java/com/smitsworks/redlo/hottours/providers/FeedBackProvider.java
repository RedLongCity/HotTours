package com.smitsworks.redlo.hottours.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 07.12.2017.
 * Posts feedback to server
 */

public class FeedBackProvider implements Provider,Urls {
    @Override
    public Response provide() {
        return null;
    }

    public Response provide(FeedBack feedBack){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return HttpUtils.postData(FEEDBACK_URL, gson.toJson(feedBack));
    }
}
