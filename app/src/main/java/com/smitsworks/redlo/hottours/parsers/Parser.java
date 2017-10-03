package com.smitsworks.redlo.hottours.parsers;

import org.json.JSONObject;

/**
 * Created by redlongcity on 03.10.2017.
 * interface for unification parsers
 */

public interface Parser<T> {

    public T parse(JSONObject json);
}
