package com.smitsworks.redlo.hottours.feedback;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.data.source.datasource.FeedBackDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.FeedBackRepository;

/**
 * Created by redlongcity on 07.12.2017.
 * class listeners to user actions from the UI, retrieves the data and update the UI as required
 */

public class FeedBackPresenter implements FeedBackContract.Presenter{

    private final FeedBackRepository feedBackRepository;

    private final FeedBackContract.View feedBackView;

    public FeedBackPresenter(@NonNull FeedBackRepository feedBackRepository,
                             @NonNull FeedBackContract.View feedBackView) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackView = feedBackView;

        feedBackView.setPresenter(this);
    }

    @Override
    public void start() {
        populateData();
    }

    @Override
    public void populateData() {
        if (feedBackRepository == null) {
            return;
        }

        String name = feedBackRepository.getCachedName();
        if (name != null) {
            feedBackView.setName(name);
        }

        String email = feedBackRepository.getCachedEmail();
        if (email != null) {
            feedBackView.setEmail(email);
        }

        String device = feedBackRepository.getCachedDevice();
        if (device != null) {
            feedBackView.setDevice(device);
        }

        String feedBack = feedBackRepository.getCachedFeedBack();
        if (feedBack != null) {
            feedBackView.setFeedBack(feedBack);
        }
    }

    @Override
    public void createCallback(String name, String device, String email, final String feedBack) {
        FeedBack entity = new FeedBack();
        entity.setName(name);
        entity.setDevice(device);
        entity.setEmail(email);
        entity.setFeedBack(feedBack);
        if(feedBack.isEmpty()){
            feedBackView.showEmptyDataError();
        }else{
            feedBackView.showToursList();
//            feedBackRepository.postFeedBack(entity, new FeedBackDataSource.PostFeedBackCallback() {todo only for test
//                @Override
//                public void onFeedBackPosted() {
//                    if(!feedBackView.isActive()){
//                        return;
//                    }
//                    feedBackView.showToursList();
//                }
//
//                @Override
//                public void onPostFailed() {
//                    if(!feedBackView.isActive()){
//                        return;
//                    }
//                    feedBackView.showFailedPosting();
//                }
//
//                @Override
//                public void onNotAvailableConnection() {
//                    if(!feedBackView.isActive()){
//                        return;
//                    }
//                    feedBackView.showNotAvailableConnection();
//                }
//            });
        }
    }

    @Override
    public void cachedName(String name) {
        if (feedBackRepository == null || name == null) {
            return;
        }
        feedBackRepository.cacheName(name);
    }

    @Override
    public void cachedEmail(String email) {
        if (feedBackRepository == null|| email == null) {
            return;
        }
        feedBackRepository.cacheEmail(email);
    }

    @Override
    public void cachedDevice(String device) {
        if (feedBackRepository == null || device == null) {
            return;
        }
        feedBackRepository.cacheDevice(device);
    }

    @Override
    public void cacheFeedBack(String feedBack) {
        if (feedBackRepository == null || feedBack == null) {
            return;
        }
        feedBackRepository.cacheFeedBack(feedBack);
    }
}
