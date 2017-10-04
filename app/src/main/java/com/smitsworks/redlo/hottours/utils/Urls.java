package com.smitsworks.redlo.hottours.utils;

/**
 * Created by redlo on 04.10.2017.
 */

public interface Urls {

    static final String URL="http://localhost:8084/EasyTour";
    static final String URL_JSON=URL+"/json";
    static final String FILTERS_URL=URL_JSON+"/getfilters";
    static final String BASE_REQUEST_URL=URL_JSON+"/gettours?hotel_rating=3:78"+
            "&night_from=2&night_till=7";
    static final String TOUR_URL="URL_JSON+/tour";

}
