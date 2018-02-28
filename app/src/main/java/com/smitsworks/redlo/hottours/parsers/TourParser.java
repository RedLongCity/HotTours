package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class TourParser implements Parser<Tour> {

    public static final String TAG = "TAG_TOUR_PARSER";

    @Override
    public Tour parse(JSONObject json) {
        Tour model = new Tour();
        if (json != null) {
            try {
                if (json.length() > 0) {

                    if (json.has(TourKeys.KEY_KEY)) {
                        model.setKey(json.getString(TourKeys.KEY_KEY));
                    }

                    if (json.has(TourKeys.KEY_TYPE)) {
                        model.setType(json.getInt(TourKeys.KEY_TYPE));
                    }

                    if (json.has(TourKeys.KEY_COUNTRY)) {
                        CountryParser countryParser = new CountryParser();
                        model.setCountry(countryParser.parse(
                                json.getJSONObject(TourKeys.KEY_COUNTRY)));
                    }

                    if (json.has(TourKeys.KEY_REGION)) {
                        model.setRegion(json.getString(TourKeys.KEY_REGION));
                    }

                    if (json.has(TourKeys.KEY_HOTEL_ID)) {
                        model.setHotel_id(json.getInt(TourKeys.KEY_HOTEL_ID));
                    }

                    if (json.has(TourKeys.KEY_HOTEL)) {
                        model.setHotel(json.getString(TourKeys.KEY_HOTEL));
                    }

                    if (json.has(TourKeys.KEY_HOTEL_RATING)) {
                        HotelRatingParser parser = new HotelRatingParser();
                        model.setHotel_Rating(parser.parse(
                                json.getJSONObject(TourKeys.KEY_HOTEL_RATING)
                        ));
                    }

                    if (json.has(TourKeys.KEY_MEAL_TYPE)) {
                        MealTypeParser mealTypeParser = new MealTypeParser();
                        model.setMeal_Type(mealTypeParser.parse(
                                json.getJSONObject(TourKeys.KEY_MEAL_TYPE)));
                    }

                    if (json.has(TourKeys.KEY_ROOM_TYPE)) {
                        model.setRoom_Type(json.getString(TourKeys.KEY_ROOM_TYPE));
                    }

                    if (json.has(TourKeys.KEY_ADULT_AMOUNT)) {
                        model.setAdult_Amount(json.getInt(TourKeys.KEY_ADULT_AMOUNT));
                    }

                    if (json.has(TourKeys.KEY_CHILD_AMOUNT)) {
                        model.setChild_Amount(json.getInt(TourKeys.KEY_CHILD_AMOUNT));
                    }

                    if (json.has(TourKeys.KEY_ACCOMODATION)) {
                        model.setAccomodation(json.getString(TourKeys.KEY_ACCOMODATION));
                    }

                    if (json.has(TourKeys.KEY_DURATION)) {
                        model.setDuration(json.getInt(TourKeys.KEY_DURATION));
                    }

                    if (json.has(TourKeys.KEY_DATE_FROM)) {
                        Date dateFrom = new Date(json.getLong(TourKeys.KEY_DATE_FROM));
                        model.setDate_From(dateFrom);
                    }

                    if (json.has(TourKeys.KEY_CURRENCY_ID)) {
                        model.setCurrency_id(json.getInt(TourKeys.KEY_CURRENCY_ID));
                    }

                    if (json.has(TourKeys.KEY_CURRENCY_SYMBOL)) {
                        model.setCurrency_Symbol(json.getString(TourKeys.KEY_CURRENCY_SYMBOL));
                    }

                    if (json.has(TourKeys.KEY_PRICES)) {
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
                            model.setPrices(priceSet);
                        }
                    }

                    if (json.has(TourKeys.KEY_PRICE_OLD) && !json.isNull(TourKeys.KEY_PRICE_OLD)) {
                        Integer priceOld = json.getInt(TourKeys.KEY_PRICE_OLD);
                        model.setPrice_Old(priceOld);
                    }
                    if (json.has(TourKeys.KEY_PRICE_CHANGE_PERCENT) && !json.isNull(TourKeys.KEY_PRICE_CHANGE_PERCENT)) {
                        Float priceChangePercent = (float) json.getDouble(
                                TourKeys.KEY_PRICE_CHANGE_PERCENT);
                        model.setPrice_Change_Percent(priceChangePercent);
                    }

                    if (json.has(TourKeys.KEY_FROM_CITIES)) {
                        CityParser cityParser = new CityParser();
                        model.setFrom_Cities(cityParser.parse(
                                json.getJSONObject(TourKeys.KEY_FROM_CITIES))
                        );
                    }


                    if (json.has(TourKeys.KEY_FROM_CITY_GEN)) {
                        model.setFrom_City_Gen(json.getString(TourKeys.KEY_FROM_CITY_GEN));
                    }

                    if (json.has(TourKeys.KEY_TRANSPORT_TYPE)) {
                        switch (json.getString(TourKeys.KEY_TRANSPORT_TYPE)) {
                            case "flight":
                                model.setTransport_Type("авиа");
                                break;
                            case "bus":
                                model.setTransport_Type("автобус");
                                break;
                            default:
                                model.setTransport_Type("авиа");

                        }
                    }
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

                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
