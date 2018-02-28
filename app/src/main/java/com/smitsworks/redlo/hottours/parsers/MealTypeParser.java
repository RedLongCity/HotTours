package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.MealTypeKeys;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Meal_Type object from JSONObject
 */

public class MealTypeParser implements Parser<Meal_Type> {

    public static final String TAG = "TAG_MEAL_TYPE_PARSER";

    @Override
    public Meal_Type parse(JSONObject json) {
        Meal_Type model = new Meal_Type();
        try {
            if (json != null) {
                if (json.length() > 0) {
                    if (json.has(MealTypeKeys.KEY_ID)) {
                        switch (json.getString(MealTypeKeys.KEY_ID)) {
                            case "1956":
                                model.setId("6");
                                break;
                            case "388":
                                model.setId("5");
                                break;
                            case "496":
                                model.setId("4");
                                break;
                            case "498":
                                model.setId("3");
                                break;
                            case "512":
                                model.setId("2");
                                break;
                            case "560":
                                model.setId("1");
                                break;
                            default:
                                model.setId("7");
                        }
                    }

                    if (json.has(MealTypeKeys.KEY_NAME)) {
                        model.setName(json.getString(MealTypeKeys.KEY_NAME));
                    }

                    if (json.has(MealTypeKeys.KEY_NAME_FULL)) {
                        model.setName_full(json.getString(MealTypeKeys.KEY_NAME_FULL));
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
