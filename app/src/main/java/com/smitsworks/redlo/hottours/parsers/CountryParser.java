package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.CountryKeys;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;

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
    public Country parse(JSONObject json) {
        Country model = new Country();
        try {
            if (json != null) {
                if (json.length() > 0) {

                    if (json.has(CountryKeys.KEY_ID)) {
                        model.setId(json.getString(CountryKeys.KEY_ID));
                    }

                    if (json.has(CountryKeys.KEY_NAME)) {
                        model.setName(json.getString((CountryKeys.KEY_NAME)));
                    }

                    if (json.has(CountryKeys.KEY_FROM_CITIES_SET)) {
                        Set<From_Cities> citiesSet = new HashSet<From_Cities>();
                        JSONArray array = json.getJSONArray(CountryKeys.KEY_FROM_CITIES_SET);

                        int lengthArray = array.length();
                        if (lengthArray > 0) {
                            CityParser parser = new CityParser();
                            for (int i = 0; i < lengthArray; i++) {
                                From_Cities city = new From_Cities();
                                JSONObject innerObject = array.getJSONObject(i);
                                city = parser.parse(innerObject);
                                citiesSet.add(city);
                            }
                        }
                        model.setFrom_CitiesSet(citiesSet);
                    }
                }
            }
        } catch (JSONException je) {
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return model;
    }
}
