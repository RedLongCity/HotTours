package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.CurrencyKeys;
import com.smitsworks.redlo.hottours.data.models.Currency;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Currency object from JSONObject
 */

public class CurrencyParser implements Parser<Currency> {

    public static final String TAG = "TAG_CURRENCY_PARSER";

    @Override
    public Currency parse(JSONObject json) {
        Currency model = new Currency();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    if (json.has(CurrencyKeys.KEY_ID)) {
                        model.setId(json.getString(CurrencyKeys.KEY_ID));
                    }

                    if (json.has(CurrencyKeys.KEY_NAME)) {
                        model.setName(json.getString(CurrencyKeys.KEY_NAME));
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
