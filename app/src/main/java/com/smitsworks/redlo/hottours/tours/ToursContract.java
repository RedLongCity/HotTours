package com.smitsworks.redlo.hottours.tours;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Tour;

import java.util.List;

/**
 * Created by redlongcity on 04.10.2017.
 * This specifies the contract between the view and the presenter.
 */

public interface ToursContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showTours(List<Tour> tasks);

        void showTourDetailsUi(Integer tourId);

        void showLoadingTourError();

        void showNoTours();

        void showFilterLable();

        void showSuccessfullyLoadedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void loadTours(boolean forceUpdate);

        void showFilterLabel();

        void openTourDetails(@NonNull Tour requestedTour);

        void setSotring(ToursSortType requestType);

        ToursSortType getSorting();


    }
}
