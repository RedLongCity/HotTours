package com.smitsworks.redlo.hottours.tourdetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.tourdetails.convertor.HotToursConvertor;
import com.smitsworks.redlo.hottours.tourorder.TourOrderActivity;
import com.smitsworks.redlo.hottours.tourorder.TourOrderFragment;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by redlongcity on 08.04.2018.
 */

public class TourDetailFragmentList extends ListFragment implements TourDetailsContract.View {

    public static final String ARGUMENT_TOUR_KEY = "TOUR_KEY";

    public static final int PERMISSION_CALL_PHONE = 1;

    private static final int REQUEST_EDIT_TOUR = 1;

    private String number;

    private TourDetailsContract.Presenter presenter;

    private CollapsingToolbarLayout mainCollapsing;

    private ImageView detailImage;

    private TourDetailAdapter adapter;

    private DialogFragment dialogFragment;

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new TourDetailAdapter(null);
        setListAdapter(adapter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMissingTour() {
        mainCollapsing.setTitle(getString(R.string.no_data));
    }

    @Override
    public void showCountryName(String countryName) {

    }

    @Override
    public void hideCountryName() {

    }

    @Override
    public void showRegion(String region) {

    }

    @Override
    public void hideRegion() {

    }

    @Override
    public void showHotelName(String hotelName) {

    }

    @Override
    public void hideHotelName() {

    }

    @Override
    public void showHotelRating(String rating) {

    }

    @Override
    public void hideHotelRating() {

    }

    @Override
    public void showMealType(String meal) {

    }

    @Override
    public void hideMealType() {

    }

    @Override
    public void showAdultAmount(Integer adults) {

    }

    @Override
    public void hideAdultAmount() {

    }

    @Override
    public void showChildrenAmount(Integer children) {

    }

    @Override
    public void hideChildrenAmount() {

    }

    @Override
    public void showDuration(Integer durationValue) {

    }

    @Override
    public void hideDuration() {

    }

    @Override
    public void showDateFrom(Date dateFrom) {

    }

    @Override
    public void hideDateFrom() {

    }

    @Override
    public void showPriceValue(Integer priceValue) {

    }

    @Override
    public void hidePriceValue() {

    }

    @Override
    public void showCurrencySymbol(String symbol) {

    }

    @Override
    public void hideCurrencySymbol() {

    }

    @Override
    public void showFromCity(String city) {

    }

    @Override
    public void hideFromCity() {

    }

    @Override
    public void showTransportType(String transport) {

    }

    @Override
    public void hideTransportType() {

    }

    @Override
    public void showImage(String imageUrl) {

    }

    @Override
    public void hideImage() {

    }

    @Override
    public void orderTour(String tourKey) {
        Intent intent = new Intent(getContext(), TourOrderActivity.class);
        intent.putExtra(TourOrderFragment.ARGUMENT_ORDER_TOUR_ID, tourKey);
        startActivityForResult(intent, REQUEST_EDIT_TOUR);
    }

    @Override
    public void call(String number) {
        if (number == null) {
            return;
        }

        this.number = number;
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            startPhoneEvent();
        } else {
            this.number = number;
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_CALL_PHONE);
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNotAwailableConnection() {
        dialogFragment.show(getFragmentManager(), getString(R.string.no_connection_dialog_message));
        showMessage(getString(R.string.no_connecion));
    }

    @Override
    public void showTourDescription(Object tour) {

        adapter.replaceData(HotToursConvertor.getInstance(
                getActivity(),
                getCurrencyType()
        ).convert(tour));
    }

    @Override
    public void setPresenter(TourDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void startPhoneEvent() {
        if (number == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(number));
        startActivity(intent);
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    private String getCurrencyType() {
        if (presenter != null && presenter.getCurrencyType() != null) {
            switch (presenter.getCurrencyType()) {
                case DOLLAR:
                    return "1";
                case HRYVNA:
                    return "2";
                case EURO:
                    return "10";
            }
        }
        return "2";
    }
}
