package com.smitsworks.redlo.hottours.tourfiltering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.calendar.CalendarActivity;
import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.lists.adults.AdultsActivity;
import com.smitsworks.redlo.hottours.lists.children.ChildrenActivity;
import com.smitsworks.redlo.hottours.lists.cities.CitiesActivity;
import com.smitsworks.redlo.hottours.lists.countries.CountriesActivity;
import com.smitsworks.redlo.hottours.lists.hotelratings.HotelRatingsActivity;
import com.smitsworks.redlo.hottours.lists.mealtypes.MealTypesActivity;

import static com.google.common.base.Preconditions.checkNotNull;

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

    private LinearLayout fromCityLayout;

    private LinearLayout toCountryLayout;

    private LinearLayout hotelRatingLayout;

    private LinearLayout mealTypeLayout;

//    private TextView date;

//    private TextView adults;
//
//    private TextView children;

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

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode,resultCode,data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        find = (Button) getActivity().findViewById(R.id.but_find);
        find.setOnClickListener(clickListener);
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
//        date = (TextView) root.findViewById(R.id.date_filters_tv);
//        adults = (TextView) root.findViewById(R.id.adult_amount_filters_tv);
//        children = (TextView) root.findViewById(R.id.children_amount_filters_tv);
        range = (RangeBar) root.findViewById(R.id.rangebar);

        fromCityLayout = (LinearLayout) root.findViewById(R.id.from_city_layout);
        fromCityLayout.setOnClickListener(clickListener);

        toCountryLayout = (LinearLayout) root.findViewById(R.id.to_country_layout);
        toCountryLayout.setOnClickListener(clickListener);

        hotelRatingLayout = (LinearLayout) root.findViewById(R.id.hotel_rating_layout);
        hotelRatingLayout.setOnClickListener(clickListener);

        mealTypeLayout = (LinearLayout) root.findViewById(R.id.meal_type_layout);
        mealTypeLayout.setOnClickListener(clickListener);

        fromCity.setOnClickListener(clickListener);
        toCountry.setOnClickListener(clickListener);
        hotelRating.setOnClickListener(clickListener);
        mealType.setOnClickListener(clickListener);

//        date.setOnClickListener(clickListener);
//        adults.setOnClickListener(clickListener);
//        children.setOnClickListener(clickListener);
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
    public void showDate(String dateValue) {
//        date.setText(dateValue);
    }

    @Override
    public void showAdultsAmount(Integer adultsValue) {
//        adults.setText(String.valueOf(adultsValue));
    }

    @Override
    public void showChildrenAmount(Integer childrenValue) {
//        children.setText(String.valueOf(childrenValue));
    }

    @Override
    public void showNightsRange(Integer nightsFrom, Integer nightsTill) {
        range.setRangePinsByValue(nightsFrom,nightsTill);
    }

    @Override
    public void openCountriesUI() {
        Intent intent = new Intent(getContext(), CountriesActivity.class);
        startActivityForResult(intent,CountriesActivity.REQUEST_CHOOSE_COUNTRY);
    }

    @Override
    public void openCitiesUI() {
        Intent intent = new Intent(getContext(), CitiesActivity.class);
        startActivityForResult(intent,CitiesActivity.REQUEST_CHOOSE_CITY);
    }

    @Override
    public void openHotelRatingsUI() {
        Intent intent = new Intent(getContext(), HotelRatingsActivity.class);
        startActivityForResult(intent,HotelRatingsActivity.REQUEST_CHOOSE_RATING);
    }

    @Override
    public void openMealTypesUI() {
        Intent intent = new Intent(getContext(), MealTypesActivity.class);
        startActivityForResult(intent,MealTypesActivity.REQUEST_CHOOSE_MEAL_TYPE);
    }

    @Override
    public void openCalendarUI() {
        Intent intent = new Intent(getContext(), CalendarActivity.class);
        startActivityForResult(intent,CalendarActivity.REQUEST_CHOOSE_DATE);
    }

    @Override
    public void openAdultsUI() {
        Intent intent = new Intent(getContext(),AdultsActivity.class);
        startActivityForResult(intent,AdultsActivity.REQUEST_CHOOSE_ADULTS_AMOUNT);
    }

    @Override
    public void openChildrenUI() {
        Intent intent = new Intent(getContext(),ChildrenActivity.class);
        startActivityForResult(intent,ChildrenActivity.REQUEST_CHOOSE_CHILDREN_AMOUNT);
    }

    @Override
    public void showTours(HotToursRequest request) {
        Intent intent = new Intent();
        intent.putExtra(TourFilteringActivity.ON_REQUEST,request);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(TourFilteringContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.from_city_tv:
                    presenter.openCitites();
                    break;
                case R.id.from_city_layout:
                    presenter.openCitites();
                    break;
                case R.id.to_country_tv:
                    presenter.openCountries();
                    break;
                case R.id.to_country_layout:
                    presenter.openCountries();
                    break;
                case R.id.hotel_rating_filters_tv:
                    presenter.openHotelRatings();
                    break;
                case R.id.hotel_rating_layout:
                    presenter.openHotelRatings();
                    break;
                case R.id.meal_type_filters_tv:
                    presenter.openMealTypes();
                    break;
                case R.id.meal_type_layout:
                    presenter.openMealTypes();
                    break;
//                case R.id.date_filters_tv:
//                    presenter.openCalendar();
//                    break;
//                case R.id.adult_amount_filters_tv:
//                    presenter.openAdults();
//                    break;
//                case R.id.children_amount_filters_tv:
//                    presenter.openChildren();
//                    break;
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
