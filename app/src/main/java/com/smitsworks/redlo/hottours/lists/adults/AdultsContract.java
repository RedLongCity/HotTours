package com.smitsworks.redlo.hottours.lists.adults;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

/**
 * Created by redlongcity on 23.10.2017.
 * This interface declares the relationships between the view and the presenter
 */

public interface AdultsContract {

    interface  View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadingAdultsError();

        void showSuccessfullyLoadedMessage();

        void showAdults();

        void chooseAdultsAmountUI(Integer amount);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadAdults();

        void chooseAdultsAmount(@NonNull Integer amount);

    }
}
