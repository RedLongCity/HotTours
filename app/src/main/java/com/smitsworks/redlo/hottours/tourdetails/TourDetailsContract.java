package com.smitsworks.redlo.hottours.tourdetails;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

/**
 * Created by redlongcity on 08.10.2017.
 * contract between presenter and fragment for showing Details of Tour
 */

public interface TourDetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMissingTour();

        void showTour(Integer tourId);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadTour(Integer tourId);

    }

}
