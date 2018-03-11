package com.smitsworks.redlo.hottours.utils;

/**
 * Created by redlongcity on 11.03.2018.
 */

public class MealTypeIdWrapper {

    public static String wrapId(String id) {
        switch (id) {
            case "1956":
                return "6";
            case "388":
                return "5";
            case "496":
                return "4";
            case "498":
                return "3";
            case "512":
                return "2";
            case "560":
                return "1";
            default:
                return "7";
        }
    }

    public static String unwrapId(String id) {
        switch (id) {
            case "6":
                return "1956";
            case "5":
                return "388";
            case "4":
                return "496";
            case "3":
                return "498";
            case "2":
                return "512";
            case "1":
                return "560";
            default:
                return "7";
        }
    }
}
