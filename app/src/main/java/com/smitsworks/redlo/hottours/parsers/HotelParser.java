package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Hotel;
import com.smitsworks.redlo.hottours.keys.HotelKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class HotelParser {

    public static final String TAG = "TAG_HOTEL_PARSER";

    public static Hotel parse(JSONObject json) {
        Hotel model = new Hotel();
        if (json != null) {
            try {
                if (json.length() > 0) {
                    if (json.has(HotelKeys.KEY_ID)) {
                        model.setId(json.getInt(HotelKeys.KEY_ID));
                    }

                    if (json.has(HotelKeys.KEY_COUNTRY)) {
                        model.setCountry(CountryParser.parse(
                                json.getJSONObject(HotelKeys.KEY_COUNTRY)));
                    }

                    if (json.has(HotelKeys.KEY_REGION)) {
                        model.setRegion(RegionParser.parse(json.getJSONObject(HotelKeys.KEY_REGION)));
                    }

                    if (json.has(HotelKeys.KEY_HOTELNAME)) {
                        model.setHotelName(json.getString(HotelKeys.KEY_HOTELNAME));
                    }

                    if (json.has(HotelKeys.KEY_HOTELREVIEWRATE)) {
                        model.setHotelReviewRate(
                                json.getString(HotelKeys.KEY_HOTELREVIEWRATE));
                    }

                    if (json.has(HotelKeys.KEY_HOTELREVIEWCOUNT)) {
                        model.setHotelReviewCount(
                                json.getString(HotelKeys.KEY_HOTELREVIEWCOUNT));
                    }

                    if (json.has(HotelKeys.KEY_FACILITIES)) {
                        JSONArray array = json.getJSONArray(HotelKeys.KEY_FACILITIES);
                        for (int i = 0; i < array.length(); i++) {
                            model.getFacilities().add(
                                    FacilityParser.parse(array.getJSONObject(i)));
                        }
                    }

                    if (json.has(HotelKeys.KEY_LAT)) {
                        model.setLat(json.getString(HotelKeys.KEY_LAT));
                    }

                    if (json.has(HotelKeys.KEY_LNG)) {
                        model.setLng(json.getString(HotelKeys.KEY_LNG));
                    }

                    if (json.has(HotelKeys.KEY_WIFIFREE)) {
                        model.setWifiFree(json.getBoolean(HotelKeys.KEY_WIFIFREE));
                    }

                    if (json.has(HotelKeys.KEY_IMAGES)) {
                        JSONArray array = json.getJSONArray(HotelKeys.KEY_IMAGES);
                        for (int i = 0; i < array.length(); i++) {
                            model.getImages().add
                                    (HotelImageParser.parse(array.getJSONObject(i)));
                        }
                    }

                    if (json.has(HotelKeys.KEY_RATING)) {
                        model.setRating(HotelRatingParser.parse(
                                json.getJSONObject(HotelKeys.KEY_RATING)));
                    }

                    if (json.has(HotelKeys.KEY_ADULTAMOUNT)) {
                        model.setAdultAmount(json.getInt(HotelKeys.KEY_ADULTAMOUNT));
                    }

                    if (json.has(HotelKeys.KEY_CHILDAMOUNT)) {
                        model.setChildAmount(json.getInt(HotelKeys.KEY_CHILDAMOUNT));
                    }

                    if (json.has(HotelKeys.KEY_ACCOMODATION)) {
                        model.setAccomodation(json.getString(HotelKeys.KEY_ACCOMODATION));
                    }

                    if (json.has(HotelKeys.KEY_TOURS)) {
                        JSONArray array = json.getJSONArray(HotelKeys.KEY_TOURS);
                        for (int i = 0; i < array.length(); i++) {
                            model.getTours().add(
                                    TourCasualParser.parse(array.getJSONObject(i)));
                        }
                    }
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
