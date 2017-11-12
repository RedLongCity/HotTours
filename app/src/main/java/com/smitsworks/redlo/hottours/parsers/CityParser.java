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

public class CityParser implements Parser<From_Cities>{

    public static final String TAG = "TAG";

    @Override
    public From_Cities parse(JSONObject json) {
        From_Cities model = new From_Cities();
        try{
            if(json!=null){
                if(json.length()>0){
                    String id = json.getString(CityKeys.KEY_ID);
                    model.setId(id);
                    String name = json.getString(CityKeys.KEY_NAME);
                    model.setName(name);
                    if(json.has(CityKeys.KEY_COUNTRY_SET)) {
                        JSONArray array = json.getJSONArray(CityKeys.KEY_COUNTRY_SET);
                        Set<Country> countrySet = new HashSet<Country>();
                        int lengthArray = array.length();
                        if (lengthArray > 0) {
                            CountryParser parser = new CountryParser();
                            for (int i = 0; i < lengthArray; i++) {
                                Country country = new Country();
                                JSONObject innerObject = array.getJSONObject(i);
                                country = parser.parse(innerObject);
                                countrySet.add(country);
                            }
                        }
                        model.setCountrySet(countrySet);
                    }
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return model;
    }
}
