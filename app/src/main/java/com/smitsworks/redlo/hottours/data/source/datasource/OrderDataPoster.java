package com.smitsworks.redlo.hottours.data.source.datasource;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Order;

/**
 * Created by redlongcity on 14.10.2017.
 * interface for providing access to variable data sources
 */

public interface OrderDataPoster {

    interface PostOrderCallback{

        void onOrderPosted();

        void onPostFailed();

        void onNotAvailableConnection();
    }

    void postOrder(@NonNull Order order,@NonNull PostOrderCallback callback);

}
