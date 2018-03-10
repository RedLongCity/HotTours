package com.smitsworks.redlo.hottours.advanced;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;
import com.smitsworks.redlo.hottours.tours.ToursSortType;

import java.util.List;

/**
 * Created by redlongcity on 10.03.2018.
 */

public interface ToursAdvancedContract {

    interface View extends BaseView<ToursAdvancedContract.Presenter> {

        void setLoadingIndicator(boolean active);

        void showTours(List<TourAdvanced> tours);

        void showTourDetailsUi(String key);

        void showNoTours();

        void showUpdatingTours();

        void showLoadingTourError();

        void showSuccessfullyLoadedMessage();

        void showSuccessfullyUpdatedMessage();

        void showSuccessfullyPostedOrderMessage();

        void showSuccesfullyPostedFeedBackMessage();

        void showUpdatingMessage();

        boolean isActive();

        void showFilteringPopUpMenu();

        void showCurrencyTypePopUpMenu();

        void setCurrencyType(TourCurrencyType requestType);

        TourCurrencyType getTourCurrencyType();

        void findToursUI();

        void showNotAvailableConnection();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode, Intent data);

        void loadTours(boolean forceUpdate);

        void loadToursByRequest(SearchingRequest request, boolean forceUpdate);

        void showFilterLabel();

        void openTourDetails(@NonNull TourAdvanced requestedTour);

        void setSotring(ToursSortType requestType);

        void setCurrencyType(TourCurrencyType requestType);

        ToursSortType getSorting();

        TourCurrencyType getCurrencyType();

        void findTours();

        void stopBackgroundLoading();
    }
}
