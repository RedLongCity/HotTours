package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.CityKeys;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing From_Cities object from JSONObject
 */

public class CityParser implements Parser<From_Cities> {

    public static final String TAG = "TAG_CITY_PARSER";

    @Override
    public From_Cities parse(JSONObject json) {
        From_Cities model = new From_Cities();
        try {
            if (json != null) {
                if (json.length() > 0) {

                    if (json.has(CityKeys.KEY_ID)) {
                        model.setId(json.getString(CityKeys.KEY_ID));
                    }

                    if (json.has(CityKeys.KEY_NAME)) {
                        model.setName(json.getString(CityKeys.KEY_NAME));
                    }
                }
            }
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return model;
    }
}
