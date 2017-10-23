package com.smitsworks.redlo.hottours.tourfiltering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.lists.cities.CitiesActivity;
import com.smitsworks.redlo.hottours.lists.countries.CountriesActivity;
import com.smitsworks.redlo.hottours.lists.hotelratings.HotelRatingsActivity;
import com.smitsworks.redlo.hottours.lists.mealtypes.MealTypesActivity;

/**
 * Created by redlongcity on 18.10.2017.
 * Listen to user actions from the UI retrieves the data and updates the UI
 */

public class TourFilteringPresenter implements TourFilteringContract.Presenter {

    private final TourFilteringContract.View filteringView;

    @Nullable
    private String countryId;

    @Nullable
    private String cityId;

    @Nullable
    private String hotelRatingId;

    @Nullable
    private String mealTypeId;

    @Nullable
    private String ratingId;

    @Nullable
    private String typeId;

    @Nullable
    private String dateFrom;

    @Nullable
    private Integer adults;

    @Nullable
    private Integer children;

    @Nullable
    private Integer nightFrom;

    @Nullable
    private Integer nightTill;

    @Nullable
    private Request request;

    @Nullable
    private String countryName;

    @Nullable
    private String cityName;

    @Nullable
    private String hotelRatingValue;

    @Nullable
    private String mealTypeName;

    public static final String COUNTRY_EXTRA="COUNTRY_EXTRA";

    public static final String COUNTRY_NAME_EXTRA="COUNTRY_NAME_EXTRA";

    public static final String CITY_EXTRA="CITY_EXTRA";

    public static final String CITY_NAME_EXTRA="CITY_NAME_EXTRA";

    public static final String HOTEL_RATING_EXTRA="HOTEL_RATING_EXTRA";

    public static final String HOTEL_RATING_NAME_EXTRA="HOTEL_RATING_NAME_EXTRA";

    public static final String MEAL_TYPE_EXTRA="MEAL_TYPE_EXTRA";

    public static final String MEAL_TYPE_NAME_EXTRA="MEAL_TYPE_NAME_EXTRA";

    public static final String DATE_FROM_EXTRA="DATA_FROM_EXTRA";

    public static final String ADULTS_EXTRA="ADULTS_EXTRA";

    public static final String CHILDREN_EXTRA="CHILDREN_EXTRA";

    public TourFilteringPresenter(TourFilteringContract.View filteringView) {
        this.filteringView = filteringView;

        filteringView.setPresenter(this);
    }

    @Override
    public void start() {
        populateFilters();
    }

    @Override
    public void openCountries() {
        filteringView.openCountriesUI();
    }

    @Override
    public void openCitites() {
        filteringView.openCitiesUI();
    }

    @Override
    public void openHotelRatings() {
        filteringView.openHotelRatingsUI();
    }

    @Override
    public void openMealTypes() {
        filteringView.openMealTypesUI();
    }

    @Override
    public void openDateFrom() {
        filteringView.openDateFromUI();
    }

    @Override
    public void openDateTill() {
        filteringView.openDateFromUI();
    }

    @Override
    public void openAdults() {
        filteringView.openAdultsUI();
    }

    @Override
    public void openChildren() {
        filteringView.openChildrenUI();
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case CountriesActivity.REQUEST_CHOOSE_COUNTRY:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(COUNTRY_EXTRA)){
                        countryId = data.getStringExtra(COUNTRY_EXTRA);
                        countryName = data.getStringExtra(COUNTRY_NAME_EXTRA);
                        filteringView.showCountry(countryName);
                    }
                }
                break;
            case CitiesActivity.REQUEST_CHOOSE_CITY:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(CITY_EXTRA)){
                        cityId = data.getStringExtra(CITY_EXTRA);
                        cityName = data.getStringExtra(CITY_NAME_EXTRA);
                        filteringView.showCity(cityName);
                    }
                }
                break;
            case HotelRatingsActivity.REQUEST_CHOOSE_RATING:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(HOTEL_RATING_EXTRA)){
                        hotelRatingId = data.getStringExtra(HOTEL_RATING_EXTRA);
                        hotelRatingValue = data.getStringExtra(HOTEL_RATING_NAME_EXTRA);
                        filteringView.showHotelRating(hotelRatingValue);
                    }
                }
                break;
            case MealTypesActivity.REQUEST_CHOOSE_MEAL_TYPE:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(MEAL_TYPE_EXTRA)){
                        mealTypeId = data.getStringExtra(MEAL_TYPE_EXTRA);
                        mealTypeName = data.getStringExtra(MEAL_TYPE_NAME_EXTRA);
                        filteringView.showMealType(mealTypeName);
                    }
                }
                break;
            case CalendarActivity.REQUEST_CHOOSE_DATE:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(DATE_FROM_EXTRA)){
                        dateFrom = data.getStringExtra(DATE_FROM_EXTRA);
                        filteringView.showDateFrom(dateFrom);
                    }
                }
                break;
        }
    }

    @Override
    public void createRequest() {
        Request request = new Request();

        if (countryId != null) {
            Country country= new Country();
            country.setId(countryId);
            request.setCountry(country);
        }

        if (cityId != null) {
            From_Cities city = new From_Cities();
            city.setId(cityId);
            request.setFrom_Cities(city);
        }

        if (mealTypeId != null) {
            Meal_Type type = new Meal_Type();
            type.setId(mealTypeId);
            request.setMeal_Type(type);
        }

        if (hotelRatingId != null) {
            request.setHotel_Rating(hotelRatingId);
        }

        if (nightFrom != null) {
            request.setNight_From(nightFrom);
        }

        if (nightTill != null) {
            request.setNight_Till(nightTill);
        }

        filteringView.showTours(request);
    }

    private void populateFilters(){

    }
    @Override
    public void setNightFrom(@Nullable Integer nightFrom) {
        this.nightFrom = nightFrom;
    }
    @Override
    public void setNightTill(@Nullable Integer nightTill) {
        this.nightTill = nightTill;
    }
}
