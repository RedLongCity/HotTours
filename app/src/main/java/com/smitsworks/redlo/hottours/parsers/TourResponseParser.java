package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.TourResponseKeys;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.models.Tour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by redlongcity on 03.10.2017.
 * class for parsing TourResponse object from JSONObject
 */

public class TourResponseParser implements Parser<TourResponse> {

    public static final String TAG = "TAG_TOUR_RESPONSE";

    @Override
    public TourResponse parse(JSONObject json) {
        TourResponse model = new TourResponse();
        if (json != null) {
            try {
                if (json.length() > 0) {

                    if (json.has(TourResponseKeys.KEY_COMEBACK_DELAY) && !json.isNull(TourResponseKeys.KEY_COMEBACK_DELAY)) {
                        model.setComeBackDelay((long) json.getInt(TourResponseKeys.KEY_COMEBACK_DELAY));
                    }

                    if (json.has(TourResponseKeys.KEY_TOUR_LIST) && !json.isNull(TourResponseKeys.KEY_TOUR_LIST)) {

                        JSONArray array = json.getJSONArray(TourResponseKeys.KEY_TOUR_LIST);

                        int arrayLength = array.length();
                        if (arrayLength > 0) {
                            List<Tour> tourList = new ArrayList<Tour>();
                            TourParser parser = new TourParser();
                            for (int i = 0; i < arrayLength; i++) {
                                Tour tour = new Tour();
                                JSONObject innerObject = array.getJSONObject(i);
                                tour = parser.parse(innerObject);
                                tourList.add(tour);
                            }
                            model.setTourList(tourList);
                        }
                    }

                    if (json.has(TourResponseKeys.KEY_REQUEST) && !json.isNull(TourResponseKeys.KEY_REQUEST)) {
                        HotToursRequestParser parser = new HotToursRequestParser();
                        model.setRequest(
                                parser.parse(json.getJSONObject(TourResponseKeys.KEY_REQUEST))
                        );
                    }
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
