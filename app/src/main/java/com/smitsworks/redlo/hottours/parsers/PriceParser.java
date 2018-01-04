package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.PriceKeys;
import com.smitsworks.redlo.hottours.data.models.Currency;
import com.smitsworks.redlo.hottours.data.models.Price;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Country object from JSONObject
 */

public class PriceParser implements Parser<Price> {

    public static final String TAG = "TAG_PRICE_PARSER";

    @Override
    public Price parse(JSONObject json) {
        Price model = new Price();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    if (json.has(PriceKeys.KEY_ID) && !json.isNull(PriceKeys.KEY_ID)) {
                        model.setId(json.getInt(PriceKeys.KEY_ID));
                    }

                    if (json.has(PriceKeys.KEY_COST)) {
                        model.setCost(json.getInt(PriceKeys.KEY_COST));
                    }

                    if (json.has(PriceKeys.KEY_CURRENCY)) {
                        CurrencyParser parser = new CurrencyParser();
                        model.setCurrency(parser.parse(
                                json.getJSONObject(PriceKeys.KEY_CURRENCY)));
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
