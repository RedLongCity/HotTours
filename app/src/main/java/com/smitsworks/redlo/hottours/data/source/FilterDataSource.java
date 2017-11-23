package com.smitsworks.redlo.hottours.data.source;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import java.util.List;

/**
 * Created by redlongcity on 17.10.2017.
 * interface for providing access to variable data sources
 */

public interface FilterDataSource {

    interface LoadCountriesCallback{

        void onCountriesLoaded(List<Country> countries);

        void onDataNotAvailable();

    }

    interface LoadCititesCallback{

        void onCitiesLoaded(List<From_Cities> cities);

        void onDataNotAvailable();
    }

    interface LoadHotelRatingsCallback{

        void onHotelRatingsLoaded(List<Hotel_Rating> ratings);

        void onDataNotAvailable();
    }

    interface LoadMealTypesCallback{

        void onMealTypesLoaded(List<Meal_Type> types);

        void onDataNotAvailable();
    }

    void getCountries(@NonNull LoadCountriesCallback callback);

    void getCities(@NonNull LoadCititesCallback callback);

    void getHotelRatings(@NonNull LoadHotelRatingsCallback callback);

    void getMealTypes(@NonNull LoadMealTypesCallback callback);

    void refreshFilters();

    void cachedCountry(Country country);

    void cachedCity(From_Cities city);

    void cachedHotelRating(Hotel_Rating rating);

    void cachedMealType(Meal_Type type);

    void cachedNightFrom(Integer nightFrom);

    void cachedNightTill(Integer nightTill);

    void cachedAdultsAmount(Integer adults);

    void cachedChildrenAmount(Integer children);

    Country getCachedCountry();

    From_Cities getCachedCity();

    Hotel_Rating getCachedRating();

    Meal_Type getCachedMealType();

    Integer getCachedNightFrom();

    Integer getCachedNightTill();

    Integer getCachedAdultsAmount();

    Integer getCachedChildrenAmount();

}
