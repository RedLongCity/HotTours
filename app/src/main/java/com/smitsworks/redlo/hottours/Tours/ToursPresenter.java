package com.smitsworks.redlo.hottours.Tours;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Tour;


/**
 * Created by redlongcity on 04.10.2017.
 * Listens to user actions from the UI, retrieves the data and uprades the UI
 */

public class ToursPresenter implements ToursContract.Presenter {
    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTours(boolean forceUpdate) {

    }

    @Override
    public void openTourDetails(@NonNull Tour requestedTask) {

    }

    @Override
    public void setSotring(ToursSortType requestType) {

    }

    @Override
    public ToursSortType getSorting() {
        return null;
    }
}
