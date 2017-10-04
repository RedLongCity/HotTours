package com.smitsworks.redlo.hottours.providers;

import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlo on 04.10.2017.
 */

public class TourResponseProvider implements Urls, Provider {

    @Override
    public Response provide() {
        return HttpUtils.getDataByURL(BASE_REQUEST_URL);
    }
}
