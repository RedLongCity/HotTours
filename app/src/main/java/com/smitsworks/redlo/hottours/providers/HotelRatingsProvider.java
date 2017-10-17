package com.smitsworks.redlo.hottours.providers;

import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 17.10.2017.
 * class for getting hotel ratings data from server
 */

public class HotelRatingsProvider implements Urls,Provider {
    @Override
    public Response provide() {
        return HttpUtils.getDataByURL(HOTEL_RATINGS_URL);
    }
}
