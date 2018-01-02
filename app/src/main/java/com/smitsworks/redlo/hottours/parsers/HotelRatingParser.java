package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.HotelRatingKeys;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 *
 */

public class HotelRatingParser implements Parser<Hotel_Rating> {

    public static final String TAG = "TAG";

    @Override
    public Hotel_Rating parse(JSONObject json) {
        try{
            if(json!=null){
                if(json.length()>0){
                    Hotel_Rating model = new Hotel_Rating();

                    if(json.has(HotelRatingKeys.KEY_ID)){
                        model.setId(json.getString(HotelRatingKeys.KEY_ID));
                    }

                    if(json.has(HotelRatingKeys.KEY_NAME)){
                        model.setName(json.getString(HotelRatingKeys.KEY_NAME));
                    }

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
