package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.App;
import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.data.source.datasource.FeedBackDataSource;
import com.smitsworks.redlo.hottours.providers.FeedBackProvider;
import com.smitsworks.redlo.hottours.utils.InternetConnection;

import okhttp3.Response;

/**
 * Created by redlongcity on 07.12.2017.
 * class for posting FeedBack to server
 */

public class FeedBackRemoteDataSource implements FeedBackDataSource{

    private static FeedBackRemoteDataSource instance;

    public static final String TAG = "TAG";

    public static FeedBackRemoteDataSource getInstance(){
        if (instance == null) {
            instance = new FeedBackRemoteDataSource();
        }
        return instance;
    }

    public FeedBackRemoteDataSource() {
    }

    @Override
    public void postFeedBack(@NonNull final FeedBack feedBack,
                             @NonNull final PostFeedBackCallback callback) {
        if(!InternetConnection.checkConnection(App.getAppContext())){
            callback.onNotAvailableConnection();
            return;
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                FeedBackProvider provider = new FeedBackProvider();
                Response response = provider.provide(feedBack);
                if (response == null) {
                    return null;
                }
                if(response.isSuccessful()){
                    callback.onFeedBackPosted();
                }else{
                    callback.onPostFailed();
                }
                return null;
            }
        }.execute();
    }

    @Override
    public String getCachedName() {
        return null;
    }

    @Override
    public String getCachedDevice() {
        return null;
    }

    @Override
    public String getCachedEmail() {
        return null;
    }

    @Override
    public void cacheName(String name) {

    }

    @Override
    public void cacheEmail(String email) {

    }

    @Override
    public void cacheDevice(String device) {

    }

    @Override
    public String getCachedFeedBack() {
        return null;
    }

    @Override
    public void cacheFeedBack(String feedBack) {

    }
}
