package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Region;
import com.smitsworks.redlo.hottours.keys.RegionKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class RegionParser {

    public static final String TAG = "REGION_PARSER_TAG";

    public static Region parse(JSONObject json) {
        Region model = new Region();
        if (json != null) {
            try {
                if (json.has(RegionKeys.KEY_ID)) {
                    model.setId(json.getString(RegionKeys.KEY_ID));
                }

                if (json.has(RegionKeys.KEY_NAME)) {
                    model.setName(json.getString(RegionKeys.KEY_NAME));
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
