package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.CountryKeys;
import com.smitsworks.redlo.hottours.models.Country;
import com.smitsworks.redlo.hottours.models.From_Cities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Country object from JSONObject
 */

public class CountryParser implements Parser<Country> {

    public static final String TAG = "TAG";

    @Override
    public Country parse(JSONObject json){
        try {
            if (json != null) {
                if (json.length() > 0) {
                    Country model = new Country();

                    String id = json.getString(CountryKeys.KEY_ID);
                    String name = json.getString((CountryKeys.KEY_NAME));
                    Set<From_Cities> citiesSet = new HashSet<From_Cities>();
                    JSONArray array = json.getJSONArray(CountryKeys.KEY_FROM_CITIES_SET);

                    int lengthArray = array.length();
                    if(lengthArray>0){
                        CityParser parser = new CityParser();
                        for(int i=0;i<lengthArray;i++){
                            From_Cities city = new From_Cities();
                            JSONObject innerObject = array.getJSONObject(i);
                            city = parser.parse(innerObject);
                            citiesSet.add(city);
                        }
                    }
                    model.setId(id);
                    model.setName(name);
                    model.setFrom_CitiesSet(citiesSet);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }

}
