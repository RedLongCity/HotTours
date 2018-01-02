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

        void showHotelRating(String rating);

        void hideHotelRating();

        void showMealType(String meal);

        void hideMealType();

        void showAdultAmount(Integer adults);

        void hideAdultAmount();

        void showChildrenAmount(Integer children);

        void hideChildrenAmount();

        void showDuration(Integer durationValue);

        void hideDuration();

        void showDateFrom(Date dateFrom);

        void hideDateFrom();

        void showPriceValue(Integer priceValue);

        void hidePriceValue();

        void showCurrencySymbol(String symbol);

        void hideCurrencySymbol();

        void showFromCity(String city);

        void hideFromCity();

        void showTransportType(String transport);

        void hideTransportType();

        void showImage(String imageUrl);

        void hideImage();

        void orderTour(String tourKey);

        boolean isActive();

        void showNotAwailableConnection();

    }

    interface Presenter extends BasePresenter{

        void orderTour();

        void setCurrencyType(TourCurrencyType requestType);

        TourCurrencyType getCurrencyType();

    }

}
