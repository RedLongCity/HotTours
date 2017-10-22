package com.smitsworks.redlo.hottours.tourfiltering;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.lists.cities.CitiesActivity;
import com.smitsworks.redlo.hottours.lists.countries.CountriesActivity;
import com.smitsworks.redlo.hottours.lists.hotelratings.HotelRatingsActivity;
import com.smitsworks.redlo.hottours.lists.mealtypes.MealTypesActivity;

/**
 * Created by redlobgcity on 22.10.2017.
 * shows fields for creating filters
 */

public class TourFilteringFragment extends Fragment implements TourFilteringContract.View {

    private TourFilteringContract.Presenter presenter;

    private TextView fromCity;

    private TextView toCountry;

    private TextView hotelRating;

    private TextView mealType;

    private TextView dateFrom;

    private TextView dateTill;

    private TextView adults;

    private TextView children;

    private Button find;

    private RangeBar range;

    public TourFilteringFragment() {
    }

    public static TourFilteringFragment newInstance(){
        return new TourFilteringFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tour_filtering_frag,container,false);
        setHasOptionsMenu(true);

        fromCity = (TextView) root.findViewById(R.id.from_city_tv);
        toCountry = (TextView) root.findViewById(R.id.to_country_tv);
        hotelRating = (TextView) root.findViewById(R.id.hotel_rating_filters_tv);
        mealType = (TextView) root.findViewById(R.id.meal_type_filters_tv);
        dateFrom = (TextView) root.findViewById(R.id.date_from_filters_tv);
        dateTill = (TextView) root.findViewById(R.id.date_till_filters_tv);
        adults = (TextView) root.findViewById(R.id.adult_amount_filters_tv);
        children = (TextView) root.findViewById(R.id.children_amount_filters_tv);
        find = (Button) root.findViewById(R.id.but_find);
        range = (RangeBar) root.findViewById(R.id.rangebar);

        fromCity.setOnClickListener(clickListener);
        toCountry.setOnClickListener(clickListener);
        hotelRating.setOnClickListener(clickListener);
        mealType.setOnClickListener(clickListener);
        dateFrom.setOnClickListener(clickListener);
        dateTill.setOnClickListener(clickListener);
        adults.setOnClickListener(clickListener);
        children.setOnClickListener(clickListener);
        find.setOnClickListener(clickListener);
        range.setOnRangeBarChangeListener(rangeListener);

        return root;
    }

    @Override
    public void showCountry(String name) {
        toCountry.setText(name);
    }

    @Override
    public void showCity(String city) {
        fromCity.setText(city);
    }

    @Override
    public void showHotelRating(String rating) {
        hotelRating.setText(rating);
    }

    @Override
    public void showMealType(String name) {
        mealType.setText(name);
    }

    @Override
    public void showDateFrom(String date) {
        dateFrom.setText(date);
    }

    @Override
    public void showDateTill(String date) {
        dateTill.setText(date);
    }

    @Override
    public void showAdultsAmount(Integer adultsValue) {
        adults.setText(adultsValue);
    }

    @Override
    public void showChildrenAmount(Integer childrenValue) {
        children.setText(childrenValue);
    }

    @Override
    public void openCountriesUI() {
        Intent intent = new Intent(getContext(), CountriesActivity.class);
        startActivity(intent);
    }

    @Override
    public void openCitiesUI() {
        Intent intent = new Intent(getContext(), CitiesActivity.class);
        startActivity(intent);
    }

    @Override
    public void openHotelRatingsUI() {
        Intent intent = new Intent(getContext(), HotelRatingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void openMealTypesUI() {
        Intent intent = new Intent(getContext(), MealTypesActivity.class);
        startActivity(intent);
    }

    @Override
    public void openDateFromUI() {

    }


    @Override
    public void openDateTillUI() {

    }

    @Override
    public void openAdultsUI() {
        Intent intent = new Intent(getContext(),AdultsActivity.class);
        startActivity(intent);
    }
    @Override
    public void showTours(Request request) {
        Intent intent = new Intent();
        intent.putExtra(request,TourFilteringActivity.ON_REQUEST);
        getActivity().setResult(TourFilterngActivity.REQUEST_EXTRA);
        getActivity().finish();
    }

    @Override
    public void openChildrenUI() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(TourFilteringContract.Presenter presenter) {

    }

    View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.from_city_tv:
                    presenter.openCitites();
                    break;
                case R.id.to_country_tv:
                    presenter.openCountries();
                    break;
                case R.id.hotel_rating_filters_tv:
                    presenter.openHotelRatings();
                    break;
                case R.id.meal_type_filters_tv:
                    presenter.openMealTypes();
                    break;
                case R.id.date_from_filters_tv:
                    presenter.openDateFrom();
                    break;
                case R.id.date_till_filters_tv:
                    presenter.openDateTill();
                    break;
                case R.id.adult_amount_filters_tv:
                    presenter.openAdults();
                    break;
                case R.id.children_amount_filters_tv:
                    presenter.openChildren();
                    break;
                case R.id.but_find:
                    presenter.createRequest();
                    break;
            }
        }
    };

    RangeBar.OnRangeBarChangeListener rangeListener = new RangeBar.OnRangeBarChangeListener(){

        @Override
        public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex,
                                          String leftPinValue, String rightPinValue) {
            presenter.setNightFrom(Integer.valueOf(leftPinValue));
            presenter.setNightTill(Integer.valueOf(rightPinValue));
        }
    };
}
