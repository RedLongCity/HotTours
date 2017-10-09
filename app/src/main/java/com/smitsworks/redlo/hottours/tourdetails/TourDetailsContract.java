package com.smitsworks.redlo.hottours.tourdetails;

import com.google.common.base.Strings;
import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;

import java.sql.Date;

/**
 * Created by redlongcity on 08.10.2017.
 * contract between presenter and fragment for showing Details of Tour
 */

public interface TourDetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMissingTour();

        void showCountryName(String countryName);

        void hideCountryName();

        void showRegion(String region);

        void hideRegion();

        void showHotelName(String hotelName);

        void hideHotelName();

        void showHotelRating(String hotelRating);

        void hideHotelRating();

        void showMealType(String mealType);

        void hideMealType();

        void showAdultAmount(Integer adultsAmount);

        void hideAdultAmount();

        void showChildrenAmount(Integer childAmount);

        void hideChildrenAmount();

        void showDuration(Integer duration);

        void hideDuration();

        void showDateFrom(Date dateFrom);

        void hideDateFrom();

        void showPriceValue(Integer priceValue);

        void hidePriceValue();

        void showCurrencySymbol(String currencySymbol);

        void hideCurrencySymbol();

        void showFromCity(String city);

        void hideFromCity();

        void showTransportType(String transportType);

        void hideTransportType();

        void showImage(String imageUrl);

        void hideImage();

        void showTour(Integer tourId);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadTour(Integer tourId);

        void setCurrencyType(TourCurrencyType requestType);

        TourCurrencyType getCurrencyType();

    }

}
