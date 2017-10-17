package com.smitsworks.redlo.hottours.data.source;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 17.10.2017.
 * Concrete implementation for load filters from the data source and save them into a
 * cache
 */

public class FiltersRepository implements FilterDataSource {

    private static FiltersRepository instance = null;

    private final FilterDataSource remoteDataSource;

    List<Country> cachedCountries;

    Country cachedCountry;

    List<From_Cities> cachedCities;

    From_Cities cachedCity;

    List<Hotel_Rating> cachedHotelRatings;

    Hotel_Rating cachedRating;

    List<Meal_Type> cachedMealTypes;

    Meal_Type cachedMealType;

    boolean countryCacheIsDirty = false;

    boolean cityCacheIsDirty = false;

    boolean hotelRatingsCacheIsDirty = false;

    boolean mealTypesCacheIsDirty = false;

    public FiltersRepository(FilterDataSource remoteDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }

    public static FiltersRepository getInstance(FilterDataSource remoteDataSource){
        if (instance == null) {
            instance = new FiltersRepository(remoteDataSource);
        }
        return instance;
    }

    @Override
    public void refreshFilters() {
        countryCacheIsDirty = true;
        cityCacheIsDirty = true;
        hotelRatingsCacheIsDirty = true;
        mealTypesCacheIsDirty = true;
    }

    @Override
    public void getCountries(@NonNull LoadCountriesCallback callback) {
        checkNotNull(callback);

        if(cachedCountries!=null && !countryCacheIsDirty){
            callback.onCountriesLoaded(cachedCountries);
            return;
        }

        if(countryCacheIsDirty){
            getCountriesFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getCities(@NonNull LoadCititesCallback callback) {
        checkNotNull(callback);

        if(cachedCities!=null && !cityCacheIsDirty){
            callback.onCitiesLoaded(cachedCities);
            return;
        }

        if(cityCacheIsDirty){
            getCitiesFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getHotelRatings(@NonNull LoadHotelRatingsCallback callback) {
        checkNotNull(callback);

        if(cachedHotelRatings!=null && !hotelRatingsCacheIsDirty){
            callback.onHotelRatingsLoaded(cachedHotelRatings);
            return;
        }

        if(hotelRatingsCacheIsDirty){
            getHotelRatingsFromRemoteDaaSource(callback);
        }
    }

    @Override
    public void getMealTypes(@NonNull LoadMealTypesCallback callback) {
        checkNotNull(callback);

        if(cachedMealTypes!=null && !mealTypesCacheIsDirty){
            callback.onMealTypesLoaded(cachedMealTypes);
            return;
        }

        if(mealTypesCacheIsDirty){
            getMealTypesFromRemoteDataSource(callback);
        }
    }

    private void getCountriesFromRemoteDataSource(
            @NonNull final LoadCountriesCallback callback){
        remoteDataSource.getCountries(new LoadCountriesCallback() {
            @Override
            public void onCountriesLoaded(List<Country> countries) {
                refreshCountryCache(countries);
                callback.onCountriesLoaded(countries);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getCitiesFromRemoteDataSource(
            @NonNull final LoadCititesCallback callback){
        remoteDataSource.getCities(new LoadCititesCallback() {
            @Override
            public void onCitiesLoaded(List<From_Cities> cities) {
                refreshCitiesCache(cities);
                callback.onCitiesLoaded(cities);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getHotelRatingsFromRemoteDaaSource(
            @NonNull final LoadHotelRatingsCallback callback){
        remoteDataSource.getHotelRatings(new LoadHotelRatingsCallback() {
            @Override
            public void onHotelRatingsLoaded(List<Hotel_Rating> ratings) {
                refreshHotelRatingCache(ratings);
                callback.onHotelRatingsLoaded(ratings);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getMealTypesFromRemoteDataSource(
            @NonNull final LoadMealTypesCallback callback){
        remoteDataSource.getMealTypes(new LoadMealTypesCallback() {
            @Override
            public void onMealTypesLoaded(List<Meal_Type> types) {
                refreshMealTypesCache(types);
                callback.onMealTypesLoaded(types);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCountryCache(List<Country> countries){
        cachedCountries = countries;
        countryCacheIsDirty = false;
    }

    private void refreshCitiesCache(List<From_Cities> cities){
        cachedCities = cities;
        cityCacheIsDirty = false;
    }

    private void refreshHotelRatingCache(List<Hotel_Rating> ratings){
        cachedHotelRatings = ratings;
        hotelRatingsCacheIsDirty = false;
    }

    private void refreshMealTypesCache(List<Meal_Type> types){
        cachedMealTypes = types;
        mealTypesCacheIsDirty = false;
    }

    @Override
    public void cachedCountry(Country country) {
        cachedCountry = country;
    }

    @Override
    public void cachedCity(From_Cities city) {
        cachedCity = city;
    }

    @Override
    public void cachedHotelRating(Hotel_Rating rating) {
        cachedRating = rating;
    }

    @Override
    public void cachedMealType(Meal_Type type) {
        cachedMealType = type;
    }

    @Override
    public Country getCachedCountry() {
        return cachedCountry;
    }

    @Override
    public From_Cities getCachedCity() {
        return cachedCity;
    }

    @Override
    public Hotel_Rating getCachedRating() {
        return cachedRating;
    }

    @Override
    public Meal_Type getCachedMealType() {
        return cachedMealType;
    }
}
