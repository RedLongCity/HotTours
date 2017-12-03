package com.smitsworks.redlo.hottours.lists.countries;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Country;

import java.util.List;

/**
 * Created by redlongcity on 19.10.2017.
 * This interface declares the relationships between the view and the presenter
 */

public interface CountriesContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showLoadingCountriesError();

        void showSuccessfullyLoadedMessage();

        void showCountries(List<Country> countries);

        void showNoCountries();

        void chooseCountryUI(Country country);

        boolean isActive();

        void showNotAwailableConnection();

    }

    interface Presenter extends BasePresenter{

        void loadCountries(boolean forceUpdate);

        void chooseCountry(@NonNull Country country);

    }
}
