package com.smitsworks.redlo.hottours.tours;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.models.Tour;

import java.util.List;

/**
 * Created by redlongcity on 04.10.2017.
 * This specifies the contract between the view and the presenter.
 */

public interface ToursContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showTours(List<Tour> tours);

        void showTourDetailsUi(Integer tourId);

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

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode, Intent data);

        void loadTours(boolean forceUpdate);

        void loadToursByRequest(Request request, boolean forceUpdate);

        void showFilterLabel();

        void openTourDetails(@NonNull Tour requestedTour);

        void setSotring(ToursSortType requestType);

        void setCurrencyType(TourCurrencyType requestType);

        ToursSortType getSorting();

        TourCurrencyType getCurrencyType();

        void findTours();

        void stopBackgroundLoading();
    }
}
