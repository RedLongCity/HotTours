package com.smitsworks.redlo.hottours.data.source.repositories;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.data.source.datasource.FeedBackDataSource;
import com.smitsworks.redlo.hottours.data.source.remote.FeedBackRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 07.12.2017.
 * Concrete implementation for posting feedback to server
 */

public class FeedBackRepository implements FeedBackDataSource {

    private static FeedBackRepository instance = null;

    private final FeedBackRemoteDataSource remoteDataSource;

    private String cachedName;

    private String cachedEmail;

    private String cachedDevice;

    private String cachedFeedBack;

    private FeedBackRepository(FeedBackRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static FeedBackRepository getInstance(@NonNull FeedBackRemoteDataSource dataSource) {
        if (instance == null) {
            instance = new FeedBackRepository(dataSource);
        }
        return instance;
    }

    @Override
    public void postFeedBack(@NonNull FeedBack feedBack, @NonNull PostFeedBackCallback callback) {
        checkNotNull(callback);
        postFeedBackToRemoteDataSource(callback, feedBack);
    }

    private void postFeedBackToRemoteDataSource(@NonNull final PostFeedBackCallback callback,
                                                @NonNull FeedBack feedBack) {
        remoteDataSource.postFeedBack(feedBack, new PostFeedBackCallback() {
            @Override
            public void onFeedBackPosted() {
                callback.onFeedBackPosted();
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
    public String getCachedDevice() {
        return cachedDevice;
    }

    @Override
    public String getCachedEmail() {
        return cachedEmail;
    }

    @Override
    public void cacheName(String name) {
        this.cachedName = name;
    }

    @Override
    public void cacheEmail(String email) {
        this.cachedEmail = email;
    }

    @Override
    public void cacheDevice(String device) {
        this.cachedDevice = device;
    }

    @Override
    public String getCachedFeedBack() {
        return cachedFeedBack;
    }

    @Override
    public void cacheFeedBack(String feedBack) {
        this.cachedFeedBack = feedBack;
    }
}
