package com.smitsworks.redlo.hottours.data.source.datasource;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.FeedBack;

/**
 * Created by redlongcity on 07.12.2017.
 * interface for providing access to callback events
 */

public interface FeedBackDataSource {

    interface PostFeedBackCallback{//O_o

        void onFeedBackPosted();

        void onPostFailed();

        void onNotAvailableConnection();
    }

    void postFeedBack(@NonNull FeedBack feedBack, @NonNull PostFeedBackCallback callback);
}
