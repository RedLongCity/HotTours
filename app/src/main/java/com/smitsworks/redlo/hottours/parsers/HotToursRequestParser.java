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

    public static final String TAG = "TAG";

    @Override
    public HotToursRequest parse(JSONObject json) {
        try{
            if (json != null) {
                if(json.length()>0){
                    HotToursRequest request = new HotToursRequest();

                    if(json.has(RequestKeys.KEY_ID)){
                        request.setId(json.getInt(RequestKeys.KEY_ID));
                    }

                    if(json.has(RequestKeys.KEY_COUNTRY)){
                        CountryParser parser = new CountryParser();
                        request.setCountry(parser.parse(
                                json.getJSONObject(RequestKeys.KEY_COUNTRY)));
                    }

                    if(json.has(RequestKeys.KEY_FROM_CITIES)){
                        CityParser parser = new CityParser();
                        request.setFrom_Cities(parser.parse(
                                json.getJSONObject(RequestKeys.KEY_FROM_CITIES)
                        ));
                    }

                    if(json.has(RequestKeys.KEY_HOTEL_RARING)){
                        request.setHotel_Rating(json.getString(
                                RequestKeys.KEY_HOTEL_RARING));
                    }

                    if(json.has(RequestKeys.KEY_NIGHT_FROM)){
                        request.setNight_From(
                                json.getInt(RequestKeys.KEY_NIGHT_FROM));
                    }

                    if(json.has(RequestKeys.KEY_NIGHT_TILL)){
                        request.setNight_Till(
                                json.getInt(RequestKeys.KEY_NIGHT_TILL)
                        );
                    }

                    if(json.has(RequestKeys.KEY_MEAL_TYPE)){
                        MealTypeParser parser = new MealTypeParser();
                        request.setMeal_Type(parser.parse(
                                json.getJSONObject(RequestKeys.KEY_MEAL_TYPE)
                        ));
                    }

                    return request;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
