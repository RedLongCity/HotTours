package com.smitsworks.redlo.hottours.tourdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;

import org.w3c.dom.Text;

import java.sql.Date;

/**
 * Created by redlongcity on 11.10.2017.
 * class like UI bridge to activity
 */

public class TourDetailFragment extends Fragment implements TourDetailsContract.View {

    @NonNull
    private static final String ARGUMENT_TOUR_ID="TOUR_ID";

    @NonNull
    private static final int REQUEST_EDIT_TOUR = 1;

    private TourDetailsContract.Presenter presenter;

    private CollapsingToolbarLayout mainCollapsing;

    private ImageView detailImage;

    private FloatingActionButton orderButton;

    private TextView fromCity;

    private TextView hotelTitle;

    private TextView hotelRating;

    private TextView mealType;

    private TextView adultAmount;

    private TextView childrenAmount;

    private TextView duration;

    private TextView fromDate;

    private TextView price;

    private TextView currencySymbol;

    private TextView transportType;

    public static TourDetailFragment newInstance(@Nullable Integer tourId){
        Bundle argumentss = new Bundle();
        argumentss.putInt(ARGUMENT_TOUR_ID,tourId);
        TourDetailFragment fragment = new TourDetailFragment();
        fragment.setArguments(argumentss);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tour_detail_frag,container,false);
        setHasOptionsMenu(true);

        mainCollapsing = (CollapsingToolbarLayout) root.findViewById(R.id.main_collapsing);
        detailImage = (ImageView) root.findViewById(R.id.detail_image);
        fromCity = (TextView) root.findViewById(R.id.city_tv);
        hotelTitle = (TextView) root.findViewById(R.id.hotel_name_tv);
        hotelRating = (TextView) root.findViewById(R.id.hotel_rating_tv);
        mealType = (TextView) root.findViewById(R.id.meal_type_tv);
        adultAmount = (TextView) root.findViewById(R.id.adult_amount_tv);
        childrenAmount = (TextView) root.findViewById(R.id.children_amount_tv);
        duration = (TextView) root.findViewById(R.id.duration_tv);
        fromDate = (TextView) root.findViewById(R.id.date_from_tv);
        price = (TextView) root.findViewById(R.id.price_value_tv);
        currencySymbol = (TextView) root.findViewById(R.id.currency_symbol_tv);
        transportType = (TextView) root.findViewById(R.id.transport_type_tv);

        orderButton = (FloatingActionButton) getActivity().findViewById(R.id.order_but);

        orderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Nullable


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMissingTour() {

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
    public void showHotelRating(String hotelRating) {

    }

    @Override
    public void hideHotelRating() {

    }

    @Override
    public void showMealType(String mealType) {

    }

    @Override
    public void hideMealType() {

    }

    @Override
    public void showAdultAmount(Integer adultsAmount) {

    }

    @Override
    public void hideAdultAmount() {

    }

    @Override
    public void showChildrenAmount(Integer childAmount) {

    }

    @Override
    public void hideChildrenAmount() {

    }

    @Override
    public void showDuration(Integer duration) {

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
    public void showCurrencySymbol(String currencySymbol) {

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
    public void showTransportType(String transportType) {

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
    public void showTour(Integer tourId) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(TourDetailsContract.Presenter presenter) {

    }
}
