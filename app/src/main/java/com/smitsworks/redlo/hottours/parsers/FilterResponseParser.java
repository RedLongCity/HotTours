package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.FilterResponseKeys;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.Currency;
import com.smitsworks.redlo.hottours.data.models.FiltersResponse;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

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

    public static final String TAG = "TAG_FILTER_RESP_PARSER";

    @Override
    public FiltersResponse parse(JSONObject json) {
        FiltersResponse model = new FiltersResponse();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    Long delay = (long) json.getInt(FilterResponseKeys.KEY_DELAY);
                    List<Country> countryList = new ArrayList<Country>();
                    JSONArray countryArray = json.getJSONArray(
                            FilterResponseKeys.KEY_COUNTRY_LIST);

                    int countryArrayLength = countryArray.length();
                    if (countryArrayLength > 0) {
                        for (int i = 0; i < countryArrayLength; i++) {
                            countryList.add(CountryParser.parse(
                                    countryArray.getJSONObject(i)));
                        }
                    }

                    List<From_Cities> citiesList = new ArrayList<From_Cities>();
                    JSONArray citiesArray = json.getJSONArray(
                            FilterResponseKeys.KEY_FROM_CITIES_LIST);

                    int citiesArrayLength = citiesArray.length();
                    if (citiesArrayLength > 0) {
                        for (int i = 0; i < citiesArrayLength; i++) {
                            citiesList.add(CityParser.parse(
                                    citiesArray.getJSONObject(i)));
                        }
                    }

                    List<Hotel_Rating> ratingList = new ArrayList<Hotel_Rating>();
                    JSONArray ratingArray = json.getJSONArray(
                            FilterResponseKeys.KEY_HOTEL_RATING_LIST);

                    int ratingArrayLength = ratingArray.length();
                    if (ratingArrayLength > 0) {
                        HotelRatingParser hotelRatingParser = new HotelRatingParser();
                        for (int i = 0; i < ratingArrayLength; i++) {
                            ratingList.add(HotelRatingParser.parse(
                                    ratingArray.getJSONObject(i)));
                        }
                    }

                    List<Meal_Type> mealTypeList = new ArrayList<Meal_Type>();
                    JSONArray mealTypeArray = json.getJSONArray(
                            FilterResponseKeys.KEY_MEAL_TYPE_LIST);

                    int mealTypeArrayLength = mealTypeArray.length();
                    if (mealTypeArrayLength > 0) {
                        for (int i = 0; i < mealTypeArrayLength; i++) {
                            mealTypeList.add(MealTypeParser.parse(
                                    mealTypeArray.getJSONObject(i)));
                        }
                    }

                    List<Currency> currencyList = new ArrayList<Currency>();
                    JSONArray currencyArray = json.getJSONArray(
                            FilterResponseKeys.KEY_CURRENCY_LIST);

                    int currencyArrayLength = currencyArray.length();
                    if (currencyArrayLength > 0) {
                        for (int i = 0; i < currencyArrayLength; i++) {
                            currencyList.add(CurrencyParser.parse(
                                    currencyArray.getJSONObject(i)));
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
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return model;
    }
}
