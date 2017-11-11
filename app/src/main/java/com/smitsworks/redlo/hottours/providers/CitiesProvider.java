package com.smitsworks.redlo.hottours.providers;

import android.os.AsyncTask;

import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 17.10.2017.
 * class for getting citiesfrom the server
 */

public class CitiesProvider extends AsyncTask<Void,Void,Response> implements Urls,Provider {
    @Override
    public Response provide() {
        return HttpUtils.getDataByURL(CITIES_URL);
    }

    @Override
    protected Response doInBackground(Void... voids) {
        return HttpUtils.getDataByURL(CITIES_URL);
    }
}
