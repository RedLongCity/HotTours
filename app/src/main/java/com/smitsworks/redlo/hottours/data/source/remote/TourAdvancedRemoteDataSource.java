package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.App;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.parsers.TourAdvancedResponseParser;
import com.smitsworks.redlo.hottours.providers.TourAdvancedProvider;
import com.smitsworks.redlo.hottours.utils.InternetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedRemoteDataSource implements TourAdvancedDataSource {

    public static final String TAG = "TOUR_A_REMOTE_TAG";

    private static final TourAdvancedRemoteDataSource ourInstance = new TourAdvancedRemoteDataSource();

    public static TourAdvancedRemoteDataSource getInstance() {
        return ourInstance;
    }

    private TourAdvancedRemoteDataSource() {
    }

    @Override
    public void getTours(@NonNull final LoadToursCallback callback) {
        if (!InternetConnection.checkConnection(App.getAppContext())) {
            callback.onNotAvailableConnection();
            return;
        }

        new AsyncTask<Void, Void, TourAdvancedResponse>() {
            @Override
            protected TourAdvancedResponse doInBackground(Void[] voids) {


                TourAdvancedProvider provider = new TourAdvancedProvider();
                Response response = provider.provide();
                if (response == null) {
                    return null;
                }
                if (response.isSuccessful()) {
                    try {
                        return TourAdvancedResponseParser.parse(new JSONObject(response.body().string()));
                    } catch (@NonNull IOException | JSONException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(TourAdvancedResponse response) {
                if (response == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onToursLoaded(response);
            }
        }.execute();
    }

    @Override
    public void getToursByRequest(@NonNull final SearchingRequest request, @NonNull final LoadToursCallback callback) {

        if (!InternetConnection.checkConnection(App.getAppContext())) {
            callback.onNotAvailableConnection();
            return;
        }

        new AsyncTask<Void, Void, TourAdvancedResponse>() {
            @Override
            protected TourAdvancedResponse doInBackground(Void... voids) {
                TourAdvancedProvider provider = new TourAdvancedProvider();
                Response response = provider.provide(request);
                if (response == null) {
                    return null;
                }
                if (response.isSuccessful()) {
                    try {
                        return TourAdvancedResponseParser.parse(
                                new JSONObject(response.body().string())
                        );
                    } catch (@NonNull IOException | JSONException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(TourAdvancedResponse tourResponse) {
                if (tourResponse == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onToursLoaded(tourResponse);
            }
        }.execute();

    }

    @Override
    public void getTour(@NonNull String tourKey, @NonNull GetTourCallback callback) {

    }

    @Override
    public void refreshTours() {

    }
}
