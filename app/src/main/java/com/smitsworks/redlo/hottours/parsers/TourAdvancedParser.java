package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.keys.TourAdvancedKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class TourAdvancedParser {

    public static final String TAG = "TOUR_A_PARSER_TAG";

    public static TourAdvanced parse(JSONObject json) {
        TourAdvanced model = new TourAdvanced();
        if (json != null) {
            try {
                if (json.has(TourAdvancedKeys.KEY_KEY)) {
                    model.setKey(json.getString(TourAdvancedKeys.KEY_KEY));
                }

                if (json.has(TourAdvancedKeys.KEY_COUNTRY)) {
                    model.setCountry(CountryParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_COUNTRY)));
                }

                if (json.has(TourAdvancedKeys.KEY_TYPE)) {
                    model.setType(TypeParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_TYPE)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_REGION)) {
                    model.setRegion(RegionParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_REGION)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_HOTELID)) {
                    model.setHotelId(json.getInt(
                            TourAdvancedKeys.KEY_HOTELID
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_HOTELNAME)) {
                    model.setHotelName(json.getString(
                            TourAdvancedKeys.KEY_HOTELNAME
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_MEALTYPE)) {
                    model.setMealType(MealTypeParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_MEALTYPE)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_ADULTAMOUNT)) {
                    model.setAdultAmount(
                            json.getInt(TourAdvancedKeys.KEY_ADULTAMOUNT)
                    );
                }

                if (json.has(TourAdvancedKeys.KEY_CHILDAMOUNT)) {
                    model.setChildAmount(json.getInt(
                            TourAdvancedKeys.KEY_CHILDAMOUNT));
                }

                if (json.has(TourAdvancedKeys.KEY_ACCOMODATION)) {
                    model.setAccomodation(json.getString(
                            TourAdvancedKeys.KEY_ACCOMODATION
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_ROOMTYPE)) {
                    model.setRoomType(json.getString(
                            TourAdvancedKeys.KEY_ROOMTYPE
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_DURATION)) {
                    model.setDuration(json.getInt(
                            TourAdvancedKeys.KEY_DURATION
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_DATEFROM)) {
                    model.setDateFrom(new Date(
                            json.getLong(TourAdvancedKeys.KEY_DATEFROM)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_COMBINED)) {
                    model.setCombined(json.getBoolean(
                            TourAdvancedKeys.KEY_COMBINED
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_CURRENCY)) {
                    model.setCurrency(CurrencyParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_CURRENCY)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_PRICES)) {
                    JSONArray array = json.getJSONArray(TourAdvancedKeys.KEY_PRICES);
                    for (int i = 0; i < array.length(); i++) {
                        model.getPrices().add(PriceParser.parse(
                                array.getJSONObject(i)
                        ));
                    }
                }

                if (json.has(TourAdvancedKeys.KEY_CITY)) {
                    model.setCity(CityParser.parse(
                            json.getJSONObject(TourAdvancedKeys.KEY_CITY)
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_TRANSPORTTYPE)) {
                    model.setTransportType(json.getString(
                            TourAdvancedKeys.KEY_TRANSPORTTYPE
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_IMAGES)) {
                    JSONArray array = json.getJSONArray(TourAdvancedKeys.KEY_IMAGES);
                    for (int i = 0; i < array.length(); i++) {
                        model.getImages().add(HotelImageParser.parse(
                                array.getJSONObject(i)
                        ));
                    }
                }

                if (json.has(TourAdvancedKeys.KEY_RATE)) {
                    model.setRate(json.getString(
                            TourAdvancedKeys.KEY_RATE
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_REVIEWCOUNT)) {
                    model.setReviewCount(json.getString(
                            TourAdvancedKeys.KEY_REVIEWCOUNT
                    ));
                }

                if (json.has(TourAdvancedKeys.KEY_FACILITIES)) {
                    JSONArray array = json.getJSONArray(TourAdvancedKeys.KEY_FACILITIES);
                    for (int i = 0; i < array.length(); i++) {
                        model.getFacilities().add(FacilityParser.parse(
                                array.getJSONObject(i)
                        ));
                    }
                }

            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }

        return model;
    }
}
