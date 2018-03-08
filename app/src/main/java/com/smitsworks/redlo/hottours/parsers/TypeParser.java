package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Type;
import com.smitsworks.redlo.hottours.keys.TypeKeys;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class TypeParser {

    public static final String TAG = "TYPE_PARSER_TAG";

    public static Type parse(JSONObject json) {
        Type model = new Type();
        if (json != null) {
            try {
                if (json.has(TypeKeys.KEY_ID)) {
                    model.setId(json.getString(
                            TypeKeys.KEY_ID
                    ));
                }

                if (json.has(TypeKeys.KEY_NAME)) {
                    model.setName(json.getString(
                            TypeKeys.KEY_NAME
                    ));
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
