package com.smitsworks.redlo.hottours.tourfiltering;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smitsworks.redlo.hottours.calendar.CalendarActivity;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.source.FiltersRepository;
import com.smitsworks.redlo.hottours.data.source.ToursRepository;
import com.smitsworks.redlo.hottours.lists.adults.AdultsActivity;
import com.smitsworks.redlo.hottours.lists.children.ChildrenActivity;
import com.smitsworks.redlo.hottours.lists.cities.CitiesActivity;
import com.smitsworks.redlo.hottours.lists.countries.CountriesActivity;
import com.smitsworks.redlo.hottours.lists.hotelratings.HotelRatingsActivity;
import com.smitsworks.redlo.hottours.lists.mealtypes.MealTypesActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 18.10.2017.
 * Listen to user actions from the UI retrieves the data and updates the UI
 */

public class TourFilteringPresenter implements TourFilteringContract.Presenter {

    private final TourFilteringContract.View filteringView;

    private final FiltersRepository filtersRepository;

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

    public TourFilteringPresenter(@NonNull FiltersRepository filtersRepository,
                                  @NonNull TourFilteringContract.View filteringView) {
        this.filtersRepository = checkNotNull(filtersRepository);
        this.filteringView = checkNotNull(filteringView);
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

                        Country country = new Country();
                        if (countryId != null) {
                        country.setId(countryId);
                        }
                        if (countryName != null) {
                            country.setName(countryName);
                        }
                        filtersRepository.cachedCountry(country);

                        filteringView.showCountry(countryName);
                    }
                }
                break;
            case CitiesActivity.REQUEST_CHOOSE_CITY:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(CITY_EXTRA)){
                        cityId = data.getStringExtra(CITY_EXTRA);
                        cityName = data.getStringExtra(CITY_NAME_EXTRA);

                        From_Cities city = new From_Cities();
                        if (cityId != null) {
                            city.setId(cityId);
                        }
                        if (cityName != null) {
                            city.setName(cityName);
                        }
                        filtersRepository.cachedCity(city);

                        filteringView.showCity(cityName);
                    }
                }
                break;
            case HotelRatingsActivity.REQUEST_CHOOSE_RATING:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(HOTEL_RATING_EXTRA)){
                        hotelRatingId = data.getStringExtra(HOTEL_RATING_EXTRA);
                        hotelRatingValue = data.getStringExtra(HOTEL_RATING_NAME_EXTRA);

                        Hotel_Rating rating = new Hotel_Rating();
                        if (hotelRatingId != null) {
                            rating.setId(hotelRatingId);
                        }
                        if (hotelRatingValue != null) {
                            rating.setName(hotelRatingValue);
                        }
                        filtersRepository.cachedHotelRating(rating);

                        filteringView.showHotelRating(hotelRatingValue);
                    }
                }
                break;
            case MealTypesActivity.REQUEST_CHOOSE_MEAL_TYPE:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(MEAL_TYPE_EXTRA)){
                        mealTypeId = data.getStringExtra(MEAL_TYPE_EXTRA);
                        mealTypeName = data.getStringExtra(MEAL_TYPE_NAME_EXTRA);

                        Meal_Type type = new Meal_Type();
                        if (mealTypeId != null) {
                            type.setId(mealTypeId);
                        }
                        if (mealTypeName != null) {
                            type.setName_full(mealTypeName);
                        }
                        filtersRepository.cachedMealType(type);

                        filteringView.showMealType(mealTypeName);
                    }
                }
                break;
            case CalendarActivity.REQUEST_CHOOSE_DATE:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(DATE_EXTRA)){
                        dateFrom = data.getStringExtra(DATE_EXTRA);//need update when field would be added
                        filteringView.showDate(dateFrom);
                    }
                }
                break;
            case AdultsActivity.REQUEST_CHOOSE_ADULTS_AMOUNT:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(ADULTS_EXTRA)){
                        adults = data.getIntExtra(ADULTS_EXTRA,2);
                        filtersRepository.cachedAdultsAmount(adults);
                        filteringView.showAdultsAmount(adults);
                    }
                }
                break;
            case ChildrenActivity.REQUEST_CHOOSE_CHILDREN_AMOUNT:
                if(resultCode == Activity.RESULT_OK){
                    if(data.hasExtra(CHILDREN_EXTRA)){
                        children = data.getIntExtra(CHILDREN_EXTRA,0);
                        filtersRepository.cachedChildrenAmount(children);
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
        if (filtersRepository == null) {
            return;
        }

        if(countryName!=null) {
            filteringView.showCountry(countryName);
        }else{
            Country country = filtersRepository.getCachedCountry();
            if (country != null) {
                countryId = country.getId();
                countryName = country.getName();
                filteringView.showCountry(countryName);
            }
        }


        if (cityName != null) {
            filteringView.showCity(cityName);
        }else{
            From_Cities city = filtersRepository.getCachedCity();
            if (city != null) {
                cityId = city.getId();
                cityName = city.getName();
                filteringView.showCity(cityName);
            }
        }

        if (hotelRatingValue != null) {
            filteringView.showHotelRating(hotelRatingValue);
        }else{
            Hotel_Rating rating = filtersRepository.getCachedRating();
            if (rating != null) {
                hotelRatingId = rating.getId();
                hotelRatingValue = rating.getName();
                filteringView.showHotelRating(hotelRatingValue);
            }
        }

        if (mealTypeName != null) {
            filteringView.showMealType(mealTypeName);
        }else{
            Meal_Type type = filtersRepository.getCachedMealType();
            if (type != null) {
                mealTypeId = type.getId();
                mealTypeName = type.getName_full();
                filteringView.showMealType(mealTypeName);
            }
        }

        if (adults != null) {
            filteringView.showAdultsAmount(adults);
        }else{
            Integer adultsAmount = filtersRepository.getCachedAdultsAmount();
            if (adultsAmount != null) {
                adults = adultsAmount;
                filteringView.showAdultsAmount(adults);
            }
        }

        if (children != null) {
            filteringView.showChildrenAmount(children);
        }else{
            Integer childrenAmount = filtersRepository.getCachedChildrenAmount();
            if (childrenAmount != null) {
                children = childrenAmount;
                filteringView.showChildrenAmount(children);
            }
        }

        if (nightFrom == null) {
            Integer from = filtersRepository.getCachedNightFrom();
            if (from != null) {
                nightFrom = from;
            }else{
                nightFrom = 1;
            }
        }

        if (nightTill == null) {
            Integer till = filtersRepository.getCachedNightTill();
            if (till != null) {
                nightTill = till;
            }else{
                nightTill = 9;
            }
        }

        filteringView.showNightsRange(nightFrom,nightTill);

    }

    @Override
    public void setNightFrom(@Nullable Integer nightFrom) {
        this.nightFrom = nightFrom;
        if (nightFrom != null) {
            filtersRepository.cachedNightFrom(nightFrom);
        }
    }
    @Override
    public void setNightTill(@Nullable Integer nightTill) {
        this.nightTill = nightTill;
        if (nightTill != null) {
            filtersRepository.cachedNightTill(nightTill);
        }
    }
}
