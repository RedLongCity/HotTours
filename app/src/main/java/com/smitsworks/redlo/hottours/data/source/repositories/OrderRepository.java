package com.smitsworks.redlo.hottours.data.source.repositories;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.source.datasource.OrderDataPoster;
import com.smitsworks.redlo.hottours.data.source.remote.OrderRemoteDataPoster;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 14.10.2017.
 * Concrete implementation for posting order to server
 */

public class OrderRepository implements OrderDataPoster {

    private static OrderRepository instance = null;

    private final OrderRemoteDataPoster remoteDataPoster;

    private String cachedName;

    private String cachedCity;

    private String cachedPhone;

    private String cachedEmail;

    private OrderRepository(OrderRemoteDataPoster remoteDataPoster) {
        this.remoteDataPoster = remoteDataPoster;
    }

    public static OrderRepository getInstance(@NonNull OrderRemoteDataPoster dataPoster){
        if (instance == null) {
            instance = new OrderRepository(dataPoster);
        }
        return instance;
    }

    @Override
    public void postOrder(@NonNull Order order,@NonNull PostOrderCallback callback ) {
        checkNotNull(callback);
        postOrderToRemoteDataSource(callback,order);
    }

    private void postOrderToRemoteDataSource(@NonNull final PostOrderCallback callback,
                                             @NonNull Order order){
        remoteDataPoster.postOrder(order, new PostOrderCallback() {
            @Override
            public void onOrderPosted() {
                callback.onOrderPosted();
            }

            @Override
            public void onPostFailed() {
                callback.onPostFailed();
            }

            @Override
            public void onNotAvailableConnection() {
                callback.onNotAvailableConnection();
            }
        });
    }

    @Override
    public String getCachedName() {
        return cachedName;
    }

    @Override
    public String getCachedPhone() {
        return cachedPhone;
    }

    @Override
    public String getCachedEmail() {
        return cachedEmail;
    }

    @Override
    public String getCachedCity() {
        return cachedCity;
    }

    @Override
    public void cacheName(String name) {
        this.cachedName = name;
    }

    @Override
    public void cachePhone(String phone) {
        this.cachedPhone = phone;
    }

    @Override
    public void cacheEmail(String email) {
        this.cachedEmail = email;
    }

    @Override
    public void cacheCity(String city) {
        this.cachedCity = city;
    }
}
