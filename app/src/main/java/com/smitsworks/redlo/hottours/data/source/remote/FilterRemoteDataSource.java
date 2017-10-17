package com.smitsworks.redlo.hottours.data.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.source.FilterDataSource;
import com.smitsworks.redlo.hottours.parsers.CityParser;
import com.smitsworks.redlo.hottours.parsers.CountryParser;
import com.smitsworks.redlo.hottours.parsers.HotelRatingParser;
import com.smitsworks.redlo.hottours.parsers.MealTypeParser;
import com.smitsworks.redlo.hottours.providers.CitiesProvider;
import com.smitsworks.redlo.hottours.providers.CountriesProvider;
import com.smitsworks.redlo.hottours.providers.HotelRatingsProvider;
import com.smitsworks.redlo.hottours.providers.MealTypesProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by redlongcity on 17.10.2017.
 * Implementation of the data source for getting Filters data
 */

public class FilterRemoteDataSource implements FilterDataSource {

    private static FilterRemoteDataSource instance;

    private static final String TAG="TAG";

    public static FilterRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new FilterRemoteDataSource();
        }
        return instance;
    }

    public FilterRemoteDataSource() {
    }

    @Override
    public void getCountries(@NonNull final LoadCountriesCallback callback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                    CountriesProvider provider = new CountriesProvider();
                    Response response = provider.provide();
                    if (response.isSuccessful()) {
                        try {
                            CountryParser parser = new CountryParser();
                            JSONArray array = new JSONArray(response.body().string());
                            int length = array.length();
                            if (length > 0) {
                                List<Country> countryList = new ArrayList<Country>();
                                for (int i = 0; i < length; i++) {
                                    Country country = parser.parse(array.getJSONObject(i));
                                    countryList.add(country);
                                }
                                callback.onCountriesLoaded(countryList);
                            }
                        }catch(@NonNull IOException | JSONException e){
                            Log.e(TAG,e.getLocalizedMessage());
                        }
                    }else{
                        callback.onDataNotAvailable();
                    }
            }
        });
    }

    @Override
    public void getCities(@NonNull final LoadCititesCallback callback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                CitiesProvider provider = new CitiesProvider();
                Response response = provider.provide();
                if(response.isSuccessful()){
                    try{
                        CityParser parser = new CityParser();
                        JSONArray array = new JSONArray(response.body().string());
                        int length = array.length();
                        if(length>0){
                            List<From_Cities> cityList = new ArrayList<From_Cities>();
                            for(int i=0;i<length;i++){
                                From_Cities city = parser.parse(array.getJSONObject(i));
                                cityList.add(city);
                            }
                            callback.onCitiesLoaded(cityList);
                        }
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }else{
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    @Override
    public void getHotelRatings(@NonNull final LoadHotelRatingsCallback callback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                HotelRatingsProvider provider = new HotelRatingsProvider();
                Response response = provider.provide();
                if(response.isSuccessful()){
                    try{
                        HotelRatingParser parser = new HotelRatingParser();
                        JSONArray array = new JSONArray(response.body().string());
                        int length = array.length();
                        if(length>0){
                            List<Hotel_Rating> list = new ArrayList<Hotel_Rating>();
                            for(int i=0;i<length;i++){
                                Hotel_Rating rating = parser.parse(array.getJSONObject(i));
                                list.add(rating);
                            }
                            callback.onHotelRatingsLoaded(list);
                        }
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }else{
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    @Override
    public void getMealTypes(@NonNull final LoadMealTypesCallback callback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                MealTypesProvider provider = new MealTypesProvider();
                Response response = provider.provide();
                if(response.isSuccessful()){
                    try{
                        MealTypeParser parser = new MealTypeParser();
                        JSONArray array = new JSONArray(response.body().string());
                        int length = array.length();
                        if(length>0){
                            List<Meal_Type> list = new ArrayList<Meal_Type>();
                            for(int i=0;i<length;i++){
                                Meal_Type type = parser.parse(array.getJSONObject(i));
                                list.add(type);
                            }
                            callback.onMealTypesLoaded(list);
                        }
                    }catch(@NonNull IOException | JSONException e){
                        Log.e(TAG,e.getLocalizedMessage());
                    }
                }else{
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    @Override
    public void refreshFilters() {

    }

    @Override
    public void cachedCountry(Country country) {

    }

    @Override
    public void cachedCity(From_Cities city) {

    }

    @Override
    public void cachedHotelRating(Hotel_Rating rating) {

    }

    @Override
    public void cachedMealType(Meal_Type type) {

    }

    @Override
    public Country getCachedCountry() {
        return null;
    }

    @Override
    public From_Cities getCachedCity() {
        return null;
    }

    @Override
    public Hotel_Rating getCachedRating() {
        return null;
    }

    @Override
    public Meal_Type getCachedMealType() {
        return null;
    }
}