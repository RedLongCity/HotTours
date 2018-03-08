package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.keys.RequestKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 01.01.2018.
 * class for parsing HotToursRequest object from JSONObject
 */

public class HotToursRequestParser implements Parser<HotToursRequest> {

    public static final String TAG = "TAG_HOT_REQUEST_PARSER";

    @Override
    public HotToursRequest parse(JSONObject json) {
        HotToursRequest request = new HotToursRequest();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    if (json.has(RequestKeys.KEY_ID) && !json.isNull(RequestKeys.KEY_ID)) {
                        request.setId(json.getInt(RequestKeys.KEY_ID));
                    }

                    if (json.has(RequestKeys.KEY_COUNTRY) && !json.isNull(RequestKeys.KEY_COUNTRY)) {
                        request.setCountry(CountryParser.parse(
                                json.getJSONObject(RequestKeys.KEY_COUNTRY)));
                    }

                    if (json.has(RequestKeys.KEY_FROM_CITIES) && !json.isNull(RequestKeys.KEY_FROM_CITIES)) {
                        request.setFrom_Cities(CityParser.parse(
                                json.getJSONObject(RequestKeys.KEY_FROM_CITIES)
                        ));
                    }

                    if (json.has(RequestKeys.KEY_HOTEL_RARING) && !json.isNull(RequestKeys.KEY_HOTEL_RARING)) {
                        request.setHotel_Rating(json.getString(
                                RequestKeys.KEY_HOTEL_RARING));
                    }

                    if (json.has(RequestKeys.KEY_NIGHT_FROM) && !json.isNull(RequestKeys.KEY_NIGHT_FROM)) {
                        request.setNight_From(
                                json.getInt(RequestKeys.KEY_NIGHT_FROM));
                    }

                    if (json.has(RequestKeys.KEY_NIGHT_TILL) && !json.isNull(RequestKeys.KEY_NIGHT_FROM)) {
                        request.setNight_Till(
                                json.getInt(RequestKeys.KEY_NIGHT_TILL)
                        );
                    }

                    if (json.has(RequestKeys.KEY_MEAL_TYPE) && !json.isNull(RequestKeys.KEY_MEAL_TYPE)) {
                        request.setMeal_Type(MealTypeParser.parse(
                                json.getJSONObject(RequestKeys.KEY_MEAL_TYPE)
                        ));
                    }

                    return request;
                }
            }
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return request;
    }
}
