package com.smitsworks.redlo.hottours.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedProvider implements Provider, Urls {

    @Override
    public Response provide() {
        return null;
    }

    public Response provide(String key) {
        return HttpUtils.getDataByURL(TOUR_ADVANCED_URL + "/" + key);
    }

    public Response provide(SearchingRequest request) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return HttpUtils.postData(TOUR_ADV_BY_REQ, gson.toJson(request));
    }
}
