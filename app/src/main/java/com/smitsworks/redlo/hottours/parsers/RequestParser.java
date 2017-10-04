package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.RequestKeys;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.models.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing Request object from JSONObject
 */

public class RequestParser implements Parser<Request>{

    public static final String TAG = "TAG";

    @Override
    public Request parse(JSONObject json) {
        try{
            if (json != null) {
                if(json.length()>0){
                    Request model = new Request();

                Integer id = json.getInt(RequestKeys.KEY_ID);

                    CountryParser countryParser = new CountryParser();
                    Country country = countryParser.parse(
                            json.getJSONObject(RequestKeys.KEY_COUNTRY));

                    CityParser cityParser = new CityParser();
                    From_Cities city = cityParser.parse(json.getJSONObject(
                            RequestKeys.KEY_FROM_CITIES));

                    String hotelRating = json.getString(RequestKeys.KEY_HOTEL_RARING);
                    Integer nightFrom = json.getInt(RequestKeys.KEY_NIGHT_FROM);
                    Integer nightTill = json.getInt(RequestKeys.KEY_NIGHT_TILL);

                    MealTypeParser mealTypeParser = new MealTypeParser();
                    Meal_Type meal_type = mealTypeParser.parse(
                            json.getJSONObject(RequestKeys.KEY_MEAL_TYPE));

                    Long requestDelay = (long) json.getInt(RequestKeys.KEY_REQUEST_DELAY);

                    model.setId(id);
                    model.setCountry(country);
                    model.setFrom_Cities(city);
                    model.setHotel_Rating(hotelRating);
                    model.setNight_From(nightFrom);
                    model.setNight_Till(nightTill);
                    model.setMeal_Type(meal_type);
                    model.setRequestDelay(requestDelay);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
