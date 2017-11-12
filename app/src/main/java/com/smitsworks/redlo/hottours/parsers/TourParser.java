package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


import com.smitsworks.redlo.hottours.keys.TourKeys;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.Currency;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Image;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Tour object from JSONObject
 */

public class TourParser implements Parser<Tour>{

    public static final String TAG = "TAG";

    @Override
    public Tour parse(JSONObject json) {
        Tour model = new Tour();
        if (json != null) {
            try {
                if (json.length() > 0) {

                    Integer id = json.getInt(TourKeys.KEY_ID);
                    Integer type = json.getInt(TourKeys.KEY_TYPE);

                    CountryParser countryParser = new CountryParser();
                    Country country = countryParser.parse(
                            json.getJSONObject(TourKeys.KEY_COUNTRY));

                    String region = json.getString(TourKeys.KEY_REGION);
                    Integer hotelId = json.getInt(TourKeys.KEY_HOTEL_ID);
                    String hotel = json.getString(TourKeys.KEY_HOTEL);

                    HotelRatingParser hotelRatingParser = new HotelRatingParser();
                    Hotel_Rating rating = hotelRatingParser.parse(
                            json.getJSONObject(TourKeys.KEY_HOTEL_RATING)
                    );

                    MealTypeParser mealTypeParser = new MealTypeParser();
                    Meal_Type meal_type = mealTypeParser.parse(
                            json.getJSONObject(TourKeys.KEY_MEAL_TYPE)
                    );

                    String roomType = json.getString(TourKeys.KEY_ROOM_TYPE);
                    Integer adultAmount = json.getInt(TourKeys.KEY_ADULT_AMOUNT);
                    Integer childAmount = json.getInt(TourKeys.KEY_CHILD_AMOUNT);
                    String accomodation = json.getString(TourKeys.KEY_ACCOMODATION);
                    Integer duration = json.getInt(TourKeys.KEY_DURATION);
                    Date dateFrom = new Date(json.getLong(TourKeys.KEY_DATE_FROM));
                    Integer currencyId = json.getInt(TourKeys.KEY_CURRENCY_ID);

                    String currencySymbol = json.getString(TourKeys.KEY_CURRENCY_SYMBOL);
                    Set<Price> priceSet = new HashSet<Price>();

                    JSONArray array = json.getJSONArray(TourKeys.KEY_PRICES);

                    int arrayLength = array.length();
                    if (arrayLength > 0) {
                        PriceParser priceParser = new PriceParser();
                        for (int i = 0; i < arrayLength; i++) {
                            Price price = new Price();
                            JSONObject innerObject = array.getJSONObject(i);
                            price = priceParser.parse(innerObject);
                            priceSet.add(price);
                        }
                    }
                    if (json.has(TourKeys.KEY_PRICE_OLD) && !json.isNull(TourKeys.KEY_PRICE_OLD)) {
                        Integer priceOld = json.getInt(TourKeys.KEY_PRICE_OLD);
                        model.setPrice_Old(priceOld);
                    }
                    if(json.has(TourKeys.KEY_PRICE_CHANGE_PERCENT)&&!json.isNull(TourKeys.KEY_PRICE_CHANGE_PERCENT)) {
                        Float priceChangePercent = (float) json.getDouble(
                                TourKeys.KEY_PRICE_CHANGE_PERCENT);
                        model.setPrice_Change_Percent(priceChangePercent);
                    }
                    CityParser cityParser = new CityParser();
                    From_Cities from_cities = cityParser.parse(
                            json.getJSONObject(TourKeys.KEY_FROM_CITIES)
                    );

                    String fromCityGen = json.getString(TourKeys.KEY_FROM_CITY_GEN);
                    String transportType = json.getString(TourKeys.KEY_TRANSPORT_TYPE);
                    if (json.has(TourKeys.KEY_HOTEL_IMAGES)) {
                        Set<Hotel_Image> hotelImageSet = new HashSet<Hotel_Image>();
                        JSONArray jsonArray = json.getJSONArray(TourKeys.KEY_HOTEL_IMAGES);
                        int jsonArrayLength = jsonArray.length();
                        if (jsonArrayLength > 0) {
                            HotelImageParser hotelImageParser = new HotelImageParser();
                            for (int i = 0; i < jsonArrayLength; i++) {
                                Hotel_Image image = new Hotel_Image();
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                image = hotelImageParser.parse(innerObject);
                                hotelImageSet.add(image);
                            }
                        }
                        model.setHotel_ImageSet(hotelImageSet);
                    }

                    model.setId(id);
                    model.setType(type);
                    model.setCountry(country);
                    model.setRegion(region);
                    model.setHotel_id(hotelId);
                    model.setHotel(hotel);
                    model.setHotel_Rating(rating);
                    model.setMeal_Type(meal_type);
                    model.setRoom_Type(roomType);
                    model.setAdult_Amount(adultAmount);
                    model.setChild_Amount(childAmount);
                    model.setAccomodation(accomodation);
                    model.setDuration(duration);
                    model.setDate_From(dateFrom);
                    model.setCurrency_id(currencyId);
                    model.setCurrency_Symbol(currencySymbol);
                    model.setPrices(priceSet);
                    model.setFrom_Cities(from_cities);
                    model.setFrom_City_Gen(fromCityGen);
                    model.setTransport_Type(transportType);

                }
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
    }
        return model;
    }
}
