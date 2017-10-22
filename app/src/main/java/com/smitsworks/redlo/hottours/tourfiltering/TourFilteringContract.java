package com.smitsworks.redlo.hottours.tourfiltering;

import android.content.Intent;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Request;

import java.util.Date;

/**
 * Created by redlongcity on 17.10.2017.
 * contract between presenter and fragment for showing Filters
 */

public interface TourFilteringContract {

    interface View extends BaseView<Presenter>{

        void showCountry(String name);

        void showCity(String city);

        void showHotelRating(String rating);

        void showMealType(String name);

        void showDateFrom(String date);

        void showDateTill(String date);

        void showAdultsAmount(Integer adults);

        void showChildrenAmount(Integer children);

        void openCountriesUI();

        void openCitiesUI();

        void openHotelRatingsUI();

        void openMealTypesUI();

        void openDateFromUI();

        void openDateTillUI();

        void openAdultsUI();

        void openChildrenUI();

        void showTours(Request request);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void openCountries();

        void openCitites();

        void openHotelRatings();

        void openMealTypes();

        void openDateFrom();

        void openDateTill();

        void openAdults();

        void openChildren();

        void createRequest();

        void setNightFrom(Integer nightFrom);

        void setNightTill(Integer nightTill);

        void result(int requestCode, int resultCode, Intent data);

    }
}
