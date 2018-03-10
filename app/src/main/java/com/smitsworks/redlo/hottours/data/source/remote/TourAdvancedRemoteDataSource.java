package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.App;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.parsers.TourResponseParser;
import com.smitsworks.redlo.hottours.providers.TourResponseProvider;
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
    public void getTours(@NonNull LoadToursCallback callback) {
        if (!InternetConnection.checkConnection(App.getAppContext())) {
            callback.onNotAvailableConnection();
            return;
        }

//        new AsyncTask<Void, Void, TourAdvancedResponse>() {
//            @Override
//            protected TourAdvancedResponse doInBackground(Void[] voids) {
//
//
//                TourResponseProvider provider = new TourResponseProvider();
//                Response response = provider.provide();
//                if (response == null) {
//                    return null;
//                }
//                if (response.isSuccessful()) {
//                    try {
//                        TourResponseParser parser = new TourResponseParser();
//                        return parser.parse(new JSONObject(response.body().string()));
//                    } catch (@NonNull IOException | JSONException e) {
//                        Log.e(TAG, e.getLocalizedMessage());
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(TourResponse response) {
//                if (response == null) {
//                    callback.onDataNotAvailable();
//                    return;
//                }
//                callback.onToursLoaded(response);
//            }
//        }.execute();
    }

    @Override
    public void getToursByRequest(@NonNull SearchingRequest request, @NonNull LoadToursCallback callback) {

    }

    @Override
    public void getTour(@NonNull String tourKey, @NonNull GetTourCallback callback) {

    }

    @Override
    public void refreshTours() {

    }
}
