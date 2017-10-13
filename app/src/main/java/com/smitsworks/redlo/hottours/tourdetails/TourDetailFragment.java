package com.smitsworks.redlo.hottours.tourdetails;

import android.app.Activity;
import android.content.Intent;
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
import com.smitsworks.redlo.hottours.tours.ToursActivity;
import com.squareup.picasso.Picasso;

import java.sql.Date;

import static com.google.common.base.Preconditions.checkNotNull;

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
                presenter.orderTour();
            }
        });
        return root;
    }

    @Override
    public void orderTour(Integer tourId) {
        Intent intent = new Intent(getContext(),TourOrderActivity.class);
        intent.putExtra(TourOrderFragment.ARGUMENT_TOUR_ID,tourId);
        startActivityForResult(intent,REQUEST_EDIT_TOUR);
    }

    public void showTours(){
        Intent intent = new Intent(getContext(), ToursActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            mainCollapsing.setTitle("");
            fromCity.setText(R.string.loading);
            hotelTitle.setText(R.string.loading);
            hotelRating.setText(R.string.loading);
            mealType.setText(R.string.loading);
            adultAmount.setText(R.string.loading);
            childrenAmount.setText(R.string.loading);
            duration.setText(R.string.loading);
            fromDate.setText(R.string.loading);
            price.setText(R.string.loading);
            currencySymbol.setText(R.string.loading);
            transportType.setText(R.string.loading);
        }
    }

    @Override
    public void showMissingTour() {
        mainCollapsing.setTitle(getString(R.string.no_data));
    }

    @Override
    public void showCountryName(@NonNull String countryName) {
        mainCollapsing.setTitle(countryName);
    }

    @Override
    public void hideCountryName() {
        mainCollapsing.setTitle(getString(R.string.no_data));
    }

    @Override
    public void showRegion(String region) {
        mainCollapsing.setTitle(mainCollapsing.getTitle()+" "+region);
    }

    @Override
    public void hideRegion() {
        mainCollapsing.setTitle(mainCollapsing.getTitle()+" "+R.string.no_data);
    }

    @Override
    public void showHotelName(@NonNull String hotelName) {
        hotelTitle.setVisibility(View.VISIBLE);
        hotelTitle.setText(hotelName);
    }

    @Override
    public void hideHotelName() {
        hotelTitle.setVisibility(View.GONE);
    }

    @Override
    public void showHotelRating(@NonNull String rating) {
        hotelRating.setVisibility(View.VISIBLE);
        hotelRating.setText(rating);
    }

    @Override
    public void hideHotelRating() {
        hotelRating.setVisibility(View.GONE);
    }

    @Override
    public void showMealType(@NonNull String meal) {
        mealType.setVisibility(View.VISIBLE);
        mealType.setText(meal);
    }

    @Override
    public void hideMealType() {
        mealType.setVisibility(View.GONE);
    }

    @Override
    public void showAdultAmount(@NonNull Integer adults) {
        adultAmount.setVisibility(View.VISIBLE);
        adultAmount.setText(adults);
    }

    @Override
    public void hideAdultAmount() {
        adultAmount.setVisibility(View.GONE);
    }

    @Override
    public void showChildrenAmount(@NonNull Integer children) {
        childrenAmount.setVisibility(View.VISIBLE);
        childrenAmount.setText(children);
    }

    @Override
    public void hideChildrenAmount() {
        childrenAmount.setVisibility(View.GONE);
    }

    @Override
    public void showDuration(@NonNull Integer durationValue) {
        duration.setVisibility(View.VISIBLE);
        duration.setText(durationValue);
    }

    @Override
    public void hideDuration() {
        duration.setVisibility(View.GONE);
    }

    @Override
    public void showDateFrom(@NonNull Date dateFrom) {
        fromDate.setVisibility(View.VISIBLE);
        fromDate.setText(dateFrom.toString());
    }

    @Override
    public void hideDateFrom() {
        fromDate.setVisibility(View.GONE);
    }

    @Override
    public void showPriceValue(@NonNull Integer priceValue) {
        price.setVisibility(View.VISIBLE);
        price.setText(priceValue);
    }

    @Override
    public void hidePriceValue() {
        price.setVisibility(View.GONE);
    }

    @Override
    public void showCurrencySymbol(@NonNull String symbol) {
        currencySymbol.setVisibility(View.VISIBLE);
        currencySymbol.setText(symbol);
    }

    @Override
    public void hideCurrencySymbol() {
        currencySymbol.setVisibility(View.GONE);
    }

    @Override
    public void showFromCity(@NonNull String city) {
        fromCity.setVisibility(View.VISIBLE);
        fromCity.setText(city);
    }

    @Override
    public void hideFromCity() {
        fromCity.setVisibility(View.GONE);
    }

    @Override
    public void showTransportType(@NonNull String transport) {
        transportType.setVisibility(View.VISIBLE);
        transportType.setText(transport);
    }

    @Override
    public void hideTransportType() {
        transportType.setVisibility(View.GONE);
    }

    @Override
    public void showImage(@NonNull String imageUrl) {
        detailImage.setVisibility(View.VISIBLE);
        Picasso.with(getActivity()).load(imageUrl).
                placeholder(R.drawable.tour_placeholder).into(detailImage);
    }

    @Override
    public void hideImage() {
        detailImage.setVisibility(View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(TourDetailsContract.Presenter presenter) {
        presenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_EDIT_TOUR){
            showTours();
            if(resultCode== Activity.RESULT_OK){
                getActivity().finish();
            }
        }
    }
}
