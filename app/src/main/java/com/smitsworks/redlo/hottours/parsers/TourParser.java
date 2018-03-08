package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Hotel_Image;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.keys.TourKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
                        model.setCountry(CountryParser.parse(
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
                        model.setHotel_Rating(HotelRatingParser.parse(
                                json.getJSONObject(TourKeys.KEY_HOTEL_RATING)
                        ));
                    }

                    if (json.has(TourKeys.KEY_MEAL_TYPE)) {
                        model.setMeal_Type(MealTypeParser.parse(
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
                        JSONArray array = json.getJSONArray(TourKeys.KEY_PRICES);

                        int arrayLength = array.length();
                        if (arrayLength > 0) {
                            for (int i = 0; i < arrayLength; i++) {
                                model.getPrices().add(PriceParser.parse(array.getJSONObject(i)));
                            }
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
                        model.setFrom_Cities(CityParser.parse(
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
                            for (int i = 0; i < jsonArrayLength; i++) {
                                JSONObject innerObject = jsonArray.getJSONObject(i);
                                hotelImageSet.add(HotelImageParser.parse(innerObject));
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
