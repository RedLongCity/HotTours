package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.HotelFilter;
import com.smitsworks.redlo.hottours.keys.HotelFilterKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class HotelFilterParser {

    public static final String TAG = "H_FILTER_PARSER_LOG";

    public static HotelFilter parse(JSONObject json) {
        HotelFilter model = new HotelFilter();
        if (json != null) {
            try {
                if (json.has(HotelFilterKeys.KEY_ID)) {
                    model.setId(json.getString(HotelFilterKeys.KEY_ID));
                }

                if (json.has(HotelFilterKeys.KEY_NAME)) {
                    model.setName(json.getString(HotelFilterKeys.KEY_NAME));
                }

                if (json.has(HotelFilterKeys.KEY_RATING)) {
                    model.setRating(HotelRatingParser.parse(
                            json.getJSONObject(HotelFilterKeys.KEY_RATING)));
                }

                if (json.has(HotelFilterKeys.KEY_REGION)) {
                    model.setRegion(RegionParser.parse(
                            json.getJSONObject(HotelFilterKeys.KEY_REGION)));
                }

                if (json.has(HotelFilterKeys.KEY_TYPESET)) {
                    JSONArray array = json.getJSONArray(HotelFilterKeys.KEY_TYPESET);
                    for (int i = 0; i < array.length(); i++) {
                        model.getTypeSet().add(TypeParser.parse(
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
