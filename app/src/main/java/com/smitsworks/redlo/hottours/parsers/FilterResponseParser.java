package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.FilterResponseKeys;
import com.smitsworks.redlo.hottours.models.Country;
import com.smitsworks.redlo.hottours.models.Currency;
import com.smitsworks.redlo.hottours.models.FiltersResponse;
import com.smitsworks.redlo.hottours.models.From_Cities;
import com.smitsworks.redlo.hottours.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.models.Meal_Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing FilterResponse object from JSONObject
 */

public class FilterResponseParser implements Parser<FiltersResponse> {

    public static final String TAG = "TAG";

    @Override
    public FiltersResponse parse(JSONObject json) {
        try {
            if (json != null) {
                if(json.length()>0){
                    FiltersResponse model = new FiltersResponse();

                    Long delay = (long) json.getInt(FilterResponseKeys.KEY_DELAY);
                    List<Country> countryList = new ArrayList<Country>();
                    JSONArray countryArray = json.getJSONArray(
                            FilterResponseKeys.KEY_COUNTRY_LIST);

                    int countryArrayLength = countryArray.length();
                    CountryParser countryParser = new CountryParser();
                    if(countryArrayLength>0){
                        for(int i=0;i<countryArrayLength;i++){
                            Country country = new Country();
                            JSONObject innerOnject = countryArray.getJSONObject(i);
                            country = countryParser.parse(innerOnject);
                            countryList.add(country);
                        }
                    }

                    List<From_Cities> citiesList = new ArrayList<From_Cities>();
                    JSONArray citiesArray = json.getJSONArray(
                            FilterResponseKeys.KEY_FROM_CITIES_LIST);

                    int citiesArrayLength = citiesArray.length();
                    if(citiesArrayLength>0){
                        CityParser cityParser = new CityParser();
                        for(int i=0;i<citiesArrayLength;i++){
                            From_Cities city = new From_Cities();
                            JSONObject innerObject = citiesArray.getJSONObject(i);
                            city = cityParser.parse(innerObject);
                            citiesList.add(city);
                        }
                    }

                    List<Hotel_Rating> ratingList = new ArrayList<Hotel_Rating>();
                    JSONArray ratingArray = json.getJSONArray(
                            FilterResponseKeys.KEY_HOTEL_RATING_LIST);

                    int ratingArrayLength = ratingArray.length();
                    if(ratingArrayLength>0){
                        HotelRatingParser hotelRatingParser = new HotelRatingParser();
                        for(int i=0;i<ratingArrayLength;i++){
                            Hotel_Rating rating = new Hotel_Rating();
                            JSONObject innerOnbject = ratingArray.getJSONObject(i);
                            rating = hotelRatingParser.parse(innerOnbject);
                            ratingList.add(rating);
                        }
                    }

                    List<Meal_Type> mealTypeList = new ArrayList<Meal_Type>();
                    JSONArray mealTypeArray = json.getJSONArray(
                            FilterResponseKeys.KEY_MEAL_TYPE_LIST);

                    int mealTypeArrayLength = mealTypeArray.length();
                    if(mealTypeArrayLength>0){
                        MealTypeParser mealTypeParser = new MealTypeParser();
                        for(int i=0;i<mealTypeArrayLength;i++){
                            Meal_Type mealType = new Meal_Type();
                            JSONObject innerObject = mealTypeArray.getJSONObject(i);
                            mealType = mealTypeParser.parse(innerObject);
                            mealTypeList.add(mealType);
                        }
                    }

                    List<Currency> currencyList = new ArrayList<Currency>();
                    JSONArray currencyArray = json.getJSONArray(
                            FilterResponseKeys.KEY_CURRENCY_LIST);

                    int currencyArrayLength = currencyArray.length();
                    if(currencyArrayLength>0){
                        CurrencyParser currencyParser = new CurrencyParser();
                        for (int i = 0; i <currencyArrayLength ; i++) {
                            Currency currency = new Currency();
                            JSONObject innerObject = currencyArray.getJSONObject(i);
                            currency = currencyParser.parse(innerObject);
                            currencyList.add(currency);
                        }
                    }

                    model.setDelay(delay);
                    model.setCountriesList(countryList);
                    model.setFrom_CititesList(citiesList);
                    model.setHotel_RatingList(ratingList);
                    model.setMeal_TypeList(mealTypeList);
                    model.setCurrencyList(currencyList);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
