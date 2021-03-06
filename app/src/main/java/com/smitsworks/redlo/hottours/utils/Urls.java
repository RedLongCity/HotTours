package com.smitsworks.redlo.hottours.utils;

/**
 * Created by redlongcity on 04.10.2017.
 * interface for storaging urls
 */

public interface Urls {

    static final String URL="http://10.0.2.2:8080/EasyTourLite";
//    static final String URL="http://localhost:8080/EasyTourLite";
//    static final String URL="http://rest-easytour.193b.starter-ca-central-1.openshiftapps.com";
    static final String URL_JSON=URL+"/json";
    static final String FILTERS_URL=URL_JSON+"/getfilters";
    static final String BASE_REQUEST_URL=URL_JSON+"/gettours?hotel_rating=3:78"+
            "&night_from=2&night_till=7";

                            //GET

    static final String TOUR_URL=URL_JSON+"/tour";
    static final String COUNTRIES_URL=URL_JSON+"/country";
    static final String CITIES_URL=URL_JSON+"/city";
    static final String HOTEL_RATINGS_URL=URL_JSON+"/hotelrating";
    static final String MEAL_TYPES_URL=URL_JSON+"/mealtype";

                            //POST
    static final String REQUEST_TOUR_URL=URL_JSON+"/gettoursbyrequest";
    static final String REQUEST_URL=URL_JSON+"/request";
    static final String ORDER_URL=URL_JSON+"/order";
    static final String FEEDBACK_URL=URL_JSON+"/feedback";
}
