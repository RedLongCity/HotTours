package com.smitsworks.redlo.hottours.parsers;

import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.keys.TourAdvancedResponseKeys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedResponseParser {

    public static final String TAG = "TOUR_A_RESP_PARSER";

    public static TourAdvancedResponse parse(JSONObject json) {
        TourAdvancedResponse model = new TourAdvancedResponse();
        if (json != null) {
            try {
                if (json.has(TourAdvancedResponseKeys.KEY_COMEBACKDELAY)) {
                    model.setComeBackDelay(
                            json.getLong(TourAdvancedResponseKeys.KEY_COMEBACKDELAY));
                }

                if (json.has(TourAdvancedResponseKeys.KEY_TOURLIST)) {
                    JSONArray array = json.getJSONArray(TourAdvancedResponseKeys.KEY_TOURLIST);
                    for (int i = 0; i < array.length(); i++) {
                        model.getTourList().add(TourAdvancedParser.parse(
                                json.getJSONObject(TourAdvancedResponseKeys.KEY_TOURLIST)
                        ));
                    }
                }

                if (json.has(TourAdvancedResponseKeys.KEY_REQUEST)) {
                    model.setRequest(SearchingRequestParser.parse(
                            json.getJSONObject(TourAdvancedResponseKeys.KEY_REQUEST)
                    ));
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
        }
        return model;
    }
}
