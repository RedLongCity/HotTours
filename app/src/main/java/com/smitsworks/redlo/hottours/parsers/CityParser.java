package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.CityKeys;
import com.smitsworks.redlo.hottours.models.Country;
import com.smitsworks.redlo.hottours.models.From_Cities;

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
        try{
            if(json!=null){
                if(json.length()>0){
                    From_Cities model = new From_Cities();

                    String id = json.getString(CityKeys.KEY_ID);
                    String name = json.getString(CityKeys.KEY_NAME);
                    JSONArray array = json.getJSONArray(CityKeys.KEY_COUNTRY_SET);
                    Set<Country> countrySet = new HashSet<Country>();

                    int lengthArray = array.length();
                    if(lengthArray>0){
                        CountryParser parser = new CountryParser();
                        for(int i=0;i<lengthArray;i++){
                            Country country = new Country();
                            JSONObject innerObject = array.getJSONObject(i);
                            country = parser.parse(innerObject);
                            countrySet.add(country);
                        }
                    }
                    model.setId(id);
                    model.setName(name);
                    model.setCountrySet(countrySet);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
