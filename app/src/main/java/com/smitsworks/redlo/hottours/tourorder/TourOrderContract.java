package com.smitsworks.redlo.hottours.tourorder;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.UserData;

/**
 * Created by redlongcity on 13.10.2017.
 * contract between view and presenter.
 */

public interface TourOrderContract {

    interface View extends BaseView<Presenter> {

        void showEmptyDataError();

        void showToursList();

        void setName(String name);

        void setMobileNumber(String mobileNumber);

        void setEmail(String email);

        void setCity(String city);

        void showSuccessfullPosting();

        void showFailedPosting();

        boolean isActive();

        void showNotAvailableConnection();
    }

    interface Presenter extends BasePresenter{

        void populateData();

        void createOrder(String name,String phoneNumber,String email,String city);

        void cachedName(String name);

        void cachedEmail(String email);

        void cachedCity(String city);

        void cachedPhone(String phone);

    }

}
