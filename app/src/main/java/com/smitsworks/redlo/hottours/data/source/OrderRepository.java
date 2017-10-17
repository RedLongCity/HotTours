package com.smitsworks.redlo.hottours.data.source;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.models.UserData;
import com.smitsworks.redlo.hottours.data.source.remote.OrderRemoteDataPoster;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 14.10.2017.
 * Concrete implementation for post orter to server
 */

public class OrderRepository implements OrderDataPoster {

    private static OrderRepository instance = null;

    private final OrderRemoteDataPoster remoteDataPoster;

    public OrderRepository(OrderRemoteDataPoster remoteDataPoster) {
        this.remoteDataPoster = remoteDataPoster;
    }

    public static OrderRepository getInstance(OrderRemoteDataPoster dataPoster){
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
        });
    }
}