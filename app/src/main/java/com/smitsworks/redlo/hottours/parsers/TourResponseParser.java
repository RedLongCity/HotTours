package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.keys.TourResponseKeys;
import com.smitsworks.redlo.hottours.data.models.Request;
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

public class TourResponseParser implements Parser<TourResponse>{

    public static final String TAG = "TAG";

    @Override
    public TourResponse parse(JSONObject json) {
        try{
            if (json != null) {
                if(json.length()>0){
                    TourResponse model = new TourResponse();

                 Long comeBackDelay = (long)json.getInt(TourResponseKeys.KEY_COMEBACK_DELAY);

                    List<Tour> tourList = new ArrayList<Tour>();
                    JSONArray array = json.getJSONArray(TourResponseKeys.KEY_TOUR_LIST);

                    int arrayLength=array.length();
                    if(arrayLength>0){
                        TourParser parser = new TourParser();
                        for (int i = 0; i <arrayLength ; i++) {
                            Tour tour = new Tour();
                            JSONObject innerObject = array.getJSONObject(i);
                            tour = parser.parse(innerObject);
                            tourList.add(tour);
                        }
                    }

                    RequestParser requestParser = new RequestParser();
                    Request request = requestParser.parse(
                            json.getJSONObject(TourResponseKeys.KEY_REQUEST));

                    model.setComeBackDelay(comeBackDelay);
                    model.setTourList(tourList);
                    model.setRequest(request);

                    return model;
                }
            }
        }catch (JSONException je){
            Log.i(TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }
}
