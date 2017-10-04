package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;
import com.smitsworks.redlo.hottours.parsers.TourParser;
import com.smitsworks.redlo.hottours.parsers.TourResponseParser;
import com.smitsworks.redlo.hottours.providers.TourProvider;
import com.smitsworks.redlo.hottours.providers.TourResponseProvider;
import com.smitsworks.redlo.hottours.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * Created by redlongcity on 04.10.2017.
 * Implementation of the data source for getting Tours data
 */

public class ToursRemoteDataSource implements ToursDataSource{

    private static ToursRemoteDataSource INSTANCE;

    public static final String TAG = "TAG";

    public static ToursRemoteDataSource getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new ToursRemoteDataSource();
        }
        return INSTANCE;
    }

    public ToursRemoteDataSource() {
    }

    @Override
    public void getTours(@NonNull final LoadToursCallback callback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                TourResponseProvider provider = new TourResponseProvider();
                Response response = provider.provide();
                if(response.isSuccessful()){
                    try {
                        TourResponseParser parser = new TourResponseParser();
                        TourResponse tourResponse = parser.parse(
                                new JSONObject(response.body().string()));
                        callback.onToursLoaded(tourResponse);
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }else{
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    @Override
    public void getTour(@NonNull final Integer tourId, @NonNull final GetTourCallback callback) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    TourProvider provider = new TourProvider();
                    Response response = provider.provide(tourId);
                    if(response.isSuccessful()){
                        try{
                            TourParser parser = new TourParser();
                            Tour tour = parser.parse(
                                    new JSONObject(response.body().string())
                            );
                            callback.onTourLoaded(tour);
                        }catch(@NonNull IOException | JSONException e){
                            Log.e(TAG,e.getLocalizedMessage());
                        }
                    }else{
                        callback.onDataNotAvailable();
                    }
                }
            });
    }

    @Override
    public void refreshTours() {

    }

    @Override
    public void deleteAllTours() {

    }
}
