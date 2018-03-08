package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.keys.SearchingRequestKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class SearchingRequestParser {

    public static final String TAG = "S_REQUEST_LOG";

    public static SearchingRequest parse(JSONObject json) {
        SearchingRequest model = new SearchingRequest();
        if (json != null) {
            try {
                if (json.has(SearchingRequestKeys.KEY_ID)) {
                    model.setId(json.getInt(SearchingRequestKeys.KEY_ID));
                }

                if (json.has(SearchingRequestKeys.KEY_TYPE)) {
                    model.setType(json.getInt(SearchingRequestKeys.KEY_TYPE));
                }

                if (json.has(SearchingRequestKeys.KEY_KIND)) {
                    model.setKind(json.getInt(SearchingRequestKeys.KEY_KIND));
                }

                if (json.has(SearchingRequestKeys.KEY_COUNTRY)) {
                    model.setCountry(CountryParser.parse(
                            json.getJSONObject(SearchingRequestKeys.KEY_COUNTRY)));
                }

                if (json.has(SearchingRequestKeys.KEY_CITY)) {
                    model.setCity(CityParser.parse(
                            json.getJSONObject(SearchingRequestKeys.KEY_CITY)));
                }

                if (json.has(SearchingRequestKeys.KEY_REGION)) {
                    model.setRegion(RegionParser.parse(
                            json.getJSONObject(SearchingRequestKeys.KEY_REGION)
                    ));
                }

                if (json.has(SearchingRequestKeys.KEY_HOTEL)) {
                    model.setHotel(json.getString(SearchingRequestKeys.KEY_HOTEL));
                }

                if (json.has(SearchingRequestKeys.KEY_RATINGSET)) {
                    JSONArray array = json.getJSONArray(SearchingRequestKeys.KEY_RATINGSET);
                    for (int i = 0; i < array.length(); i++) {
                        model.getRatingSet().add(
                                HotelRatingParser.parse(array.getJSONObject(i))
                        );
                    }
                }

                if (json.has(SearchingRequestKeys.KEY_ADULTAMOUNT)) {
                    model.setAdultAmount(json.getInt(
                            SearchingRequestKeys.KEY_ADULTAMOUNT));
                }

                if (json.has(SearchingRequestKeys.KEY_CHILDAMOUNT)) {
                    model.setChildAmount(json.getInt(
                            SearchingRequestKeys.KEY_CHILDAMOUNT));
                }

                if (json.has(SearchingRequestKeys.KEY_CHILDAGE)) {
                    model.setChildAge(json.getString(
                            SearchingRequestKeys.KEY_CHILDAGE));
                }

                if (json.has(SearchingRequestKeys.KEY_NIGHTFROM)) {
                    model.setNightFrom(json.getInt(
                            SearchingRequestKeys.KEY_NIGHTFROM));
                }

                if (json.has(SearchingRequestKeys.KEY_NIGHTTILL)) {
                    model.setNightTill(json.getInt(
                            SearchingRequestKeys.KEY_NIGHTTILL));
                }

                if (json.has(SearchingRequestKeys.KEY_DATEFROM)) {
                    model.setDateFrom(new Date(json.getLong(
                            SearchingRequestKeys.KEY_DATEFROM)));
                }

                if (json.has(SearchingRequestKeys.KEY_DATETILL)) {
                    model.setDateTill(new Date(json.getLong(
                            SearchingRequestKeys.KEY_DATETILL)));
                }

                if (json.has(SearchingRequestKeys.KEY_MEALTYPE)) {
                    model.setMealType(MealTypeParser.parse(
                            json.getJSONObject(SearchingRequestKeys.KEY_MEALTYPE)));
                }

                if (json.has(SearchingRequestKeys.KEY_PRICEFROM)) {
                    model.setPriceFrom(json.getInt(
                            SearchingRequestKeys.KEY_PRICEFROM));
                }

                if (json.has(SearchingRequestKeys.KEY_PRICETILL)) {
                    model.setPriceTill(json.getInt(
                            SearchingRequestKeys.KEY_PRICETILL));
                }

                if (json.has(SearchingRequestKeys.KEY_CURRENCY)) {
                    model.setCurrency(CurrencyParser.parse(
                            json.getJSONObject(SearchingRequestKeys.KEY_CURRENCY)));
                }

                if (json.has(SearchingRequestKeys.KEY_ONLYSTANDART)) {
                    model.setOnlyStandart(json.getInt(
                            SearchingRequestKeys.KEY_ONLYSTANDART));
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
