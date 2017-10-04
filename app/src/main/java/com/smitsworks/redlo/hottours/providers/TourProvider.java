package com.smitsworks.redlo.hottours.providers;

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
}
