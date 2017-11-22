package com.smitsworks.redlo.hottours.tourfiltering;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.smitsworks.redlo.hottours.calendar.CalendarActivity;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.lists.adults.AdultsActivity;
import com.smitsworks.redlo.hottours.lists.children.ChildrenActivity;
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

    public static final String DATE_EXTRA ="DATE_EXTRA";

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
    public void openCalendar() {
        filteringView.openCalendarUI();
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
                    if(data.hasExtra(DATE_EXTRA)){
                        dateFrom = data.getStringExtra(DATE_EXTRA);
                        filteringView.showDate(dateFrom);
                    }
                }
                break;
            case AdultsActivity.REQUEST_CHOOSE_ADULTS_AMOUNT:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(ADULTS_EXTRA)){
                        adults = data.getIntExtra(ADULTS_EXTRA,2);
                        filteringView.showAdultsAmount(adults);
                    }
                }
                break;
            case ChildrenActivity.REQUEST_CHOOSE_CHILDREN_AMOUNT:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(CHILDREN_EXTRA)){
                        children = data.getIntExtra(CHILDREN_EXTRA,0);
                        filteringView.showChildrenAmount(children);
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
        }else{
            request.setCountry(null);
        }

        if (cityId != null) {
            From_Cities city = new From_Cities();
            city.setId(cityId);
            request.setFrom_Cities(city);
        }else{
            request.setFrom_Cities(null);
        }

        if (mealTypeId != null) {
            Meal_Type type = new Meal_Type();
            type.setId(mealTypeId);
            request.setMeal_Type(type);
        }else{
            request.setMeal_Type(null);
        }

        if (hotelRatingId != null) {
            request.setHotel_Rating(hotelRatingId);
        }else{
            request.setHotel_Rating("3:78");
        }

        if (nightFrom != null) {
            request.setNight_From(nightFrom);
        }else{
            request.setNight_From(2);
        }

        if (nightTill != null) {
            request.setNight_Till(nightTill);
        }else{
            request.setNight_Till(7);
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
