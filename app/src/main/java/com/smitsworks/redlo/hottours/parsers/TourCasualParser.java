package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.TourCasual;
import com.smitsworks.redlo.hottours.keys.TourCasualKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class TourCasualParser {

    public static final String TAG = "TOUR_CAS_PARSER_TAG";

    public static TourCasual parse(JSONObject json) {
        TourCasual model = new TourCasual();
        if (json != null) {
            try {
                if (json.has(TourCasualKeys.KEY_KEY)) {
                    model.setKey(json.getString(
                            TourCasualKeys.KEY_KEY
                    ));
                }

                if (json.has(TourCasualKeys.KEY_MEALTYPE)) {
                    model.setMealType(MealTypeParser.parse(
                            json.getJSONObject(TourCasualKeys.KEY_MEALTYPE)
                    ));
                }

                if (json.has(TourCasualKeys.KEY_ROOMTYPE)) {
                    model.setRoomType(json.getString(
                            TourCasualKeys.KEY_ROOMTYPE
                    ));
                }

                if (json.has(TourCasualKeys.KEY_DURATION)) {
                    model.setDuration(json.getInt(
                            TourCasualKeys.KEY_DURATION
                    ));
                }

                if (json.has(TourCasualKeys.KEY_DATEFROM)) {
                    model.setDateFrom(new Date(
                            json.getLong(TourCasualKeys.KEY_DATEFROM)
                    ));
                }

                if (json.has(TourCasualKeys.KEY_COMBINED)) {
                    model.setCombined(json.getBoolean(
                            TourCasualKeys.KEY_COMBINED
                    ));
                }

                if (json.has(TourCasualKeys.KEY_CURRENCY)) {
                    model.setCurrency(CurrencyParser.parse(
                            json.getJSONObject(TourCasualKeys.KEY_CURRENCY)
                    ));
                }

                if (json.has(TourCasualKeys.KEY_PRICES)) {
                    JSONArray array = json.getJSONArray(TourCasualKeys.KEY_PRICES);
                    for (int i = 0; i < array.length(); i++) {
                        model.getPrices().add(PriceParser.parse(
                                array.getJSONObject(i)
                        ));
                    }
                }

                if (json.has(TourCasualKeys.KEY_CITY)) {
                    model.setCity(CityParser.parse(
                            json.getJSONObject(TourCasualKeys.KEY_CITY)
                    ));
                }

                if (json.has(TourCasualKeys.KEY_TRANSPORTTYPE)) {
                    model.setTransportType(json.getString(
                            TourCasualKeys.KEY_TRANSPORTTYPE
                    ));
                }

                if (json.has(TourCasualKeys.KEY_HOTEL)) {
                    model.setHotel(HotelParser.parse(
                            json.getJSONObject(TourCasualKeys.KEY_HOTEL)
                    ));
                }

            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
