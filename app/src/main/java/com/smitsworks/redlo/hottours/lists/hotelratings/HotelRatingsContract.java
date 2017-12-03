package com.smitsworks.redlo.hottours.lists.hotelratings;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;

import java.util.List;

/**
 * Created by redlongcity on 20.10.2017.
 * This interface declares the relationships between the view and the presenter
 */

public interface HotelRatingsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadingHotelRatingsError();

        void showSuccessfullyLoadedMessage();

        void showHotelRatings(List<Hotel_Rating> ratings);

        void showNoHotelRatings();

        void chooseHotelRatingUI(Hotel_Rating rating);

        boolean isActive();

        void showNotAwailableConnection();
    }

    interface Presenter extends BasePresenter{

        void loadHotelRatings(boolean forceUpdate);

        void chooseHotelRating(@NonNull Hotel_Rating rating);

    }
}
