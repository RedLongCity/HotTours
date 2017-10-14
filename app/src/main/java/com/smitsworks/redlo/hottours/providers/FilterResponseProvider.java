package com.smitsworks.redlo.hottours.providers;

import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 04.10.2017.
 * class for getting response from server
 */

public class FilterResponseProvider implements Urls,Provider{

    @Override
    public Response provide() {
        return HttpUtils.getDataByURL(FILTERS_URL);
    }
}
