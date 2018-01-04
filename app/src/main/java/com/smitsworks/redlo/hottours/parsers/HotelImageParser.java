package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.HotelImageKeys;
import com.smitsworks.redlo.hottours.data.models.Hotel_Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Hotel_Image object from JSONObject
 */

public class HotelImageParser implements Parser<Hotel_Image> {

    public static final String TAG = "TAG_HOTEL_IMAGE_PARSER";

    @Override
    public Hotel_Image parse(JSONObject json) {
        Hotel_Image model = new Hotel_Image();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    if (json.has(HotelImageKeys.KEY_ID) && !json.isNull(HotelImageKeys.KEY_ID)) {
                        model.setId(json.getInt(HotelImageKeys.KEY_ID));
                    }

                    if (json.has(HotelImageKeys.KEY_FULL)) {
                        model.setFull(json.getString(HotelImageKeys.KEY_FULL));
                    }

                    if (json.has(HotelImageKeys.KEY_THUMB)) {
                        model.setThumb(json.getString(HotelImageKeys.KEY_THUMB));
                    }

                    return model;
                }
            }
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return model;
    }
}
