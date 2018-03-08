package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Facility;
import com.smitsworks.redlo.hottours.keys.FacilityKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class FacilityParser{

    public static final String TAG = "TAG_FACILITY_PARSER";

    public static Facility parse(JSONObject json) {
        Facility model = new Facility();
        if (json == null) {
            try {
                if (json.length() > 0) {
                    if (json.has(FacilityKeys.KEY_ID)) {
                        model.setId(json.getString(FacilityKeys.KEY_ID));
                    }

                    if (json.has(FacilityKeys.KEY_NAME)) {
                        model.setName(json.getString(FacilityKeys.KEY_NAME));
                    }

                    if (json.has(FacilityKeys.KEY_CATEGORYID)) {
                        model.setCategoryId(json.getString(FacilityKeys.KEY_CATEGORYID));
                    }

                    if (json.has(FacilityKeys.KEY_CATEGORY)) {
                        model.setCategory(json.getString(FacilityKeys.KEY_CATEGORY));
                    }

                    if (json.has(FacilityKeys.KEY_MAIN)) {
                        model.setMain(json.getBoolean(FacilityKeys.KEY_MAIN));
                    }

                    if (json.has(FacilityKeys.KEY_PAID)) {
                        model.setPaid(json.getBoolean(FacilityKeys.KEY_PAID));
                    }
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }

        return model;
    }
}
