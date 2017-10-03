package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.HotelImageKeys;
import com.smitsworks.redlo.hottours.models.Hotel_Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Hotel_Image object from JSONObject
 */

public class HotelImageParser implements Parser<Hotel_Image> {

    public static final String TAG = "TAG";

    @Override
    public Hotel_Image parse(JSONObject json) {
        try{
            if(json!=null){
                if(json.length()>0){
                    Hotel_Image model = new Hotel_Image();

                    Integer id = json.getInt(HotelImageKeys.KEY_ID);
                    String full = json.getString(HotelImageKeys.KEY_FULL);
                    String thumb = json.getString(HotelImageKeys.KEY_THUMB);

                    model.setId(id);
                    model.setFull(full);
                    model.setThumb(thumb);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
