package com.smitsworks.redlo.hottours.lists.cities;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.From_Cities;

import java.util.List;

/**
 * Created by redlongcity on 20.10.2017.
 * interface which declares relationship between view and presenter
 */

public interface CitiesContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showLoadingCitiesError();

        void showCitites(List<From_Cities> cities);

        void showNoCities();

        void chooseCityUI(From_Cities city);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadCities(boolean forceUpdate);

        void chooseCity(@NonNull From_Cities city);

    }

}
