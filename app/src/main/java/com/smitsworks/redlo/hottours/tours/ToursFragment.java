package com.smitsworks.redlo.hottours.tours;

import com.smitsworks.redlo.hottours.data.models.Tour;

import java.util.List;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by redlongcity on 05.10.2017.
 * Display grid of tours
 */

public class ToursFragment extends Fragment implements ToursContract.View {

    private ToursContract.Presenter presenter;

    private ToursAdapter adapter;

    private View noToursView;

    private ImageView noToursIcon;

    private TextView noToursMainView;

    private LinearLayout toursView;

    public ToursFragment() {
    }

    public static ToursFragment newInstance(){return new ToursFragment();}



    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTours(List<Tour> tasks) {

    }

    @Override
    public void showTourDetailsUi(Integer tourId) {

    }

    @Override
    public void showLoadingTourError() {

    }

    @Override
    public void showNoTours() {

    }

    @Override
    public void showFilterLable() {

    }

    @Override
    public void showSuccessfullyLoadedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }

    @Override
    public void setPresenter(ToursContract.Presenter presenter) {

    }
}
