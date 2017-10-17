package com.smitsworks.redlo.hottours.providers;

import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 17.10.2017.
 * class for getting meal types data from the server
 */

public class MealTypesProvider implements Urls,Provider {
    @Override
    public Response provide() {
        return HttpUtils.getDataByURL(MEAL_TYPES_URL);
    }
}
