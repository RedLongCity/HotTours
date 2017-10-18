package com.smitsworks.redlo.hottours.providers;

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
        return HttpUtils.postData(REQUEST_URL,request);
    }
}
