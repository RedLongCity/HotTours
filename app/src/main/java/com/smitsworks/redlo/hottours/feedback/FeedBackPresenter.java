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

    }

    @Override
    public void populateData() {

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
            feedBackRepository.postFeedBack(entity, new FeedBackDataSource.PostFeedBackCallback() {
                @Override
                public void onFeedBackPosted() {
                    if(!feedBackView.isActive()){
                        return;
                    }
                    feedBackView.showSuccessfullPosting();
                    feedBackView.showToursList();
                }

                @Override
                public void onPostFailed() {
                    if(!feedBackView.isActive()){
                        return;
                    }
                    feedBackView.showFailedPosting();
                }

                @Override
                public void onNotAvailableConnection() {
                    if(!feedBackView.isActive()){
                        return;
                    }
                    feedBackView.showNotAvailableConnection();
                }
            });
        }
    }
}
