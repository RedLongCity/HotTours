package com.smitsworks.redlo.hottours.tourfiltering;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import java.util.List;

/**
 * Created by redlongcity on 17.10.2017.
 * contract between presenter and fragment for showing Filters
 */

public interface TourFilteringContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void loadCountries(boolean forceUpdate);

        void loadCities(boolean forceUpdate);

        void loadHotelRatings(boolean forceUpdate);

        void loadMealTypes(boolean forceUpdate);

        void openCountries(@NonNull List<Country> countries);

        void openCitites(@NonNull List<From_Cities> cities);

        void openHotelRatings(@NonNull List<Hotel_Rating> ratings);

        void openMealTypes(@NonNull List<Meal_Type> types);

    }
}
