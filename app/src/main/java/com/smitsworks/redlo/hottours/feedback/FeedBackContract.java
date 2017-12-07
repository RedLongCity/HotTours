package com.smitsworks.redlo.hottours.feedback;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

/**
 * Created by redlongcity on 07.12.2017.
 * contract between view and presenter
 */

public class FeedBackContract {

    interface View extends BaseView<Presenter> {

        void showEmptyDataError();

        void showToursList();

        void setName(String name);

        void setDevice(String device);

        void setEmail(String email);

        void setFeedBack(String feedBack);

        void showSuccessfullPosting();

        void showFailedPosting();

        boolean isActive();

        void showNotAvailableConnection();
    }

    interface Presenter extends BasePresenter {

        void populateData();

        void createCallback(String name, String device, String email, String feedBack);

    }
}
