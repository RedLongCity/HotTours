package com.smitsworks.redlo.hottours.lists.countries;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.source.FilterDataSource;
import com.smitsworks.redlo.hottours.data.source.FiltersRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 19.10.2017.
 * Listens to user actions from the UI, retrieves the data and upgrade the UI
 */

public class CountriesPresenter implements CountriesContract.Presenter {

    private final FiltersRepository filtersRepository;

    private final CountriesContract.View countriesView;

    private boolean firstLoad = true;

    public CountriesPresenter(@NonNull FiltersRepository filtersRepository,
                              @NonNull CountriesContract.View countriesView) {
        this.filtersRepository = checkNotNull(filtersRepository);
        this.countriesView = checkNotNull(countriesView);

        countriesView.setPresenter(this);
    }

    @Override
    public void loadCountries(boolean forceUpdate) {
        loadCountries(forceUpdate || firstLoad,true);
        firstLoad = false;
    }

    private void loadCountries(boolean forceUpdate, final boolean showLoadingUI){
        if(showLoadingUI){
            countriesView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            filtersRepository.refreshFilters();
        }

        filtersRepository.getCountries(new FilterDataSource.LoadCountriesCallback() {
            @Override
            public void onCountriesLoaded(List<Country> countries) {
                if(!countriesView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    countriesView.setLoadingIndicator(false);
                }
                processCountries(countries);
            }

            @Override
            public void onDataNotAvailable() {
                if(!countriesView.isActive()){
                    return;
                }
                countriesView.showLoadingCountriesError();
            }
        });
    }

    private void processCountries(List<Country> countries){
        if(countries.isEmpty()){
            processEmptyCountries();
        }else{
            countriesView.showCountries(countries);
        }
    }

    private void processEmptyCountries(){
        countriesView.showNoCountries();
    }

    @Override
    public void chooseCountry(@NonNull Country country) {
        countriesView.chooseCountryUI(country);
    }

    @Override
    public void start() {
        loadCountries(false);
    }
}
