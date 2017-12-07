package com.smitsworks.redlo.hottours.lists.cities;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.source.datasource.FilterDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.FiltersRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 20.10.2017.
 * Listens to user actions from the UI, retrieves the data and upgrade the UI
 */

public class CitiesPresenter implements CitiesContract.Presenter {

    private final FiltersRepository filtersRepository;

    private final CitiesContract.View citiesView;

    private boolean firstload = true;

    public CitiesPresenter(@NonNull FiltersRepository filtersRepository,
                           @NonNull CitiesContract.View citiesView) {
        this.filtersRepository = checkNotNull(filtersRepository);
        this.citiesView = checkNotNull(citiesView);

        citiesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCities(false);
    }

    @Override
    public void loadCities(boolean forceUpdate) {
        loadCities(forceUpdate || firstload,true);
        firstload = false;
    }

    private void loadCities(boolean forceUpdate, final boolean showLoadingUI){

        if(showLoadingUI){
            citiesView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            filtersRepository.refreshFilters();
        }

        filtersRepository.getCities(new FilterDataSource.LoadCititesCallback(){

            @Override
            public void onCitiesLoaded(List<From_Cities> cities) {
                if(!citiesView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    citiesView.setLoadingIndicator(false);
                }
                processCities(cities);
                citiesView.showSuccessfullyLoadedMessage();
            }

            @Override
            public void onDataNotAvailable() {
                if(!citiesView.isActive()){
                    return;
                }
                citiesView.showLoadingCitiesError();
            }

            @Override
            public void onNotAvailableConnection() {
                if(!citiesView.isActive()){
                    return;
                }
                citiesView.showNotAwailableConnection();
            }
        });

    }

    private void processCities(List<From_Cities> cities){
        if(cities.isEmpty()){
            processEmptyCities();
        }else{
            citiesView.showCitites(cities);
        }
    }

    private void processEmptyCities(){
        citiesView.showNoCities();
    }

    @Override
    public void chooseCity(@NonNull From_Cities city) {
        citiesView.chooseCityUI(city);
    }
}
