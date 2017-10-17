package com.smitsworks.redlo.hottours.tourfiltering;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import java.util.Date;
import java.util.List;

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

        void showDateFrom(Date date);

        void showAdultsAmount(Integer adults);

        void showChildrenAmount(Integer children);

        void openCountries();

        void openCities();

        void openHotelRatings();

        void openMealTypes();

        void openCalendar();

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void openCountries();

        void openCitites();

        void openHotelRatings();

        void openMealTypes();

    }
}
