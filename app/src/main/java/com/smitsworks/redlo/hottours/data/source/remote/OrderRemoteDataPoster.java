package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.App;
import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.source.datasource.OrderDataPoster;
import com.smitsworks.redlo.hottours.providers.OrderProvider;
import com.smitsworks.redlo.hottours.utils.InternetConnection;

import okhttp3.Response;

/**
 * Created by redlongcity on 14.10.2017.
 * class for posting Order to server
 */

public class OrderRemoteDataPoster implements OrderDataPoster {

    private static OrderRemoteDataPoster instance;

    public static final String TAG = "TAG";

    public static OrderRemoteDataPoster getInstance(){
        if (instance == null) {
            instance = new OrderRemoteDataPoster();
        }
        return instance;
    }

    public OrderRemoteDataPoster() {
    }

    @Override
    public void postOrder(@NonNull final Order order,
                          @NonNull final PostOrderCallback callback) {

        if(!InternetConnection.checkConnection(App.getAppContext())){
            callback.onNotAvailableConnection();
            return;
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                OrderProvider provider = new OrderProvider();
                Response response = provider.provide(order);
                if (response == null) {
                    return null;
                }
                if(response.isSuccessful()){
                    callback.onOrderPosted();
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
    public String getCachedPhone() {
        return null;
    }

    @Override
    public String getCachedEmail() {
        return null;
    }

    @Override
    public String getCachedCity() {
        return null;
    }

    @Override
    public void cacheName(String name) {

    }

    @Override
    public void cachePhone(String phone) {

    }

    @Override
    public void cacheEmail(String email) {

    }

    @Override
    public void cacheCity(String city) {

    }
}
