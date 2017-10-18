package com.smitsworks.redlo.hottours.tourfiltering;

import android.content.Intent;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

import java.util.Date;

/**
 * Created by redlongcity on 17.10.2017.
 * contract between presenter and fragment for showing Filters
 */

public interface TourFilteringContract {

    interface View extends BaseView<Presenter>{

        void showCountry(String name);

        void showCity(String city);

        void showHotelRating(Integer rating);

        void showMealType(String name);

        void showDateFrom(String date);

        void showAdultsAmount(Integer adults);

        void showChildrenAmount(Integer children);

        void openCountriesUI();

        void openCitiesUI();

        void openHotelRatingsUI();

        void openMealTypesUI();

        void openCalendarUI();

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void openCountries();

        void openCitites();

        void openHotelRatings();

        void openMealTypes();

        void openCalendar();

        void result(int requestCode, int resultCode, Intent data);

    }
}
