package com.smitsworks.redlo.hottours.lists.countries;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import com.smitsworks.redlo.hottours.data.models.Country;

import java.util.List;

/**
 * Created by redlongcity on 19.10.2017.
 * Displays list of countries
 */

public class CountriesFragment extends Fragment implements CountriesContract.View{

    private CountriesContract.Presenter presenter;

    private ArrayAdapter<String> adapter;



    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showLoadingCountriesError() {

    }

    @Override
    public void showCountries(List<Country> countries) {

    }

    @Override
    public void showNoCountries() {

    }

    @Override
    public void chooseCountryUI(Country country) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(CountriesContract.Presenter presenter) {

    }
}
