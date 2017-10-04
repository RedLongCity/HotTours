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

    public static final String TAG = "TAG";

    @Override
    public Price parse(JSONObject json) {
        try{
            if (json != null) {
                if(json.length()>0){
                    Price model = new Price();

                    Integer id = json.getInt(PriceKeys.KEY_ID);
                    Integer cost = json.getInt(PriceKeys.KEY_COST);

                    CurrencyParser parser = new CurrencyParser();
                    Currency currency = parser.parse(json.getJSONObject(PriceKeys.KEY_CURRENCY));

                    model.setId(id);
                    model.setCost(cost);
                    model.setCurrency(currency);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
