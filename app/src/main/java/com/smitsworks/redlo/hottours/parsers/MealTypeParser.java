package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.MealTypeKeys;
import com.smitsworks.redlo.hottours.models.Meal_Type;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Meal_Type object from JSONObject
 */

public class MealTypeParser implements Parser<Meal_Type> {

    public static final String TAG = "TAG";

    @Override
    public Meal_Type parse(JSONObject json) {
        try{
            if(json!=null){
                if(json.length()>0){
                    Meal_Type model = new Meal_Type();

                    String id = json.getString(MealTypeKeys.KEY_ID);
                    String name = json.getString(MealTypeKeys.KEY_NAME);
                    String nameFull = json.getString(MealTypeKeys.KEY_NAME_FULL);

                    model.setId(id);
                    model.setName(name);
                    model.setName_full(nameFull);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
