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

    public static final String TAG = "TAG";

    @Override
    public Currency parse(JSONObject json) {
        try{
            if(json!=null){
                if(json.length()>0){
                    Currency model = new Currency();

                    String id = json.getString(CurrencyKeys.KEY_ID);
                    String name = json.getString(CurrencyKeys.KEY_NAME);

                    model.setId(id);
                    model.setName(name);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
