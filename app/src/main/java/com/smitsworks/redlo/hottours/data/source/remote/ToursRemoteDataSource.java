package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.App;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.ToursDataSource;
import com.smitsworks.redlo.hottours.parsers.TourParser;
import com.smitsworks.redlo.hottours.parsers.TourResponseParser;
import com.smitsworks.redlo.hottours.providers.TourProvider;
import com.smitsworks.redlo.hottours.providers.TourResponseProvider;
import com.smitsworks.redlo.hottours.utils.InternetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

        if(!InternetConnection.checkConnection(App.getAppContext())){
            callback.onNotAvailableConnection();
            return;
        }

                new AsyncTask<Void,Void,TourResponse>() {
                    @Override
                    protected TourResponse doInBackground(Void[] voids) {


                        TourResponseProvider provider = new TourResponseProvider();
                        Response response = provider.provide();
                        if (response == null) {
                            return null;
                        }
                        if(response.isSuccessful()){
                            try {
                                TourResponseParser parser = new TourResponseParser();
                                return  parser.parse(new JSONObject(response.body().string()));
                            }catch(@NonNull IOException | JSONException e){
                                Log.e(TAG,e.getLocalizedMessage());
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(TourResponse response) {
                        if (response == null) {
                            callback.onDataNotAvailable();
                            return;
                        }
                        callback.onToursLoaded(response);
                    }
                }.execute();
    }

    @Override
    public void getTour(@NonNull final Integer tourId, @NonNull final GetTourCallback callback) {

        if(!InternetConnection.checkConnection(App.getAppContext())){
            callback.onNotAvailableConnection();
            return;
        }
        new AsyncTask<Void, Void, Tour>() {
            @Override
            protected Tour doInBackground(Void... voids) {
                TourProvider provider = new TourProvider();
                Response response = provider.provide(tourId);
                if (response == null) {
                    return null;
                }
                if(response.isSuccessful()){
                    try{
                        TourParser parser = new TourParser();
                        return parser.parse(
                                new JSONObject(response.body().string())
                        );
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Tour tour) {
                if(tour==null){
                    callback.onDataNotAvailable();
                    return;
                }else{
                    callback.onTourLoaded(tour);
                }
            }
        }.execute();
    }

    @Override
    public void getToursByRequest(@NonNull final Request request, @NonNull final LoadToursCallback callback) {

        if(!InternetConnection.checkConnection(App.getAppContext())){
            callback.onNotAvailableConnection();
            return;
        }

        new AsyncTask<Void, Void, TourResponse>() {
            @Override
            protected TourResponse doInBackground(Void... voids) {
                TourProvider provider = new TourProvider();
                Response response = provider.provide(request);
                if (response == null) {
                    return null;
                }
                if(response.isSuccessful()){
                    try{
                        TourResponseParser parser = new TourResponseParser();
                        return parser.parse(
                                new JSONObject(response.body().string())
                        );
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(TourResponse tourResponse) {
                if (tourResponse == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onToursLoaded(tourResponse);
            }
        }.execute();
    }

    @Override
    public void refreshTours() {

    }

    @Override
    public void deleteAllTours() {

    }
}
