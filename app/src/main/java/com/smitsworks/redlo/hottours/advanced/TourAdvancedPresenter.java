package com.smitsworks.redlo.hottours.advanced;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.TourAdvancedRepository;
import com.smitsworks.redlo.hottours.feedback.FeedBackActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;
import com.smitsworks.redlo.hottours.tours.ToursSortType;
import com.smitsworks.redlo.hottours.utils.DateUtils;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedPresenter implements ToursAdvancedContract.Presenter {

    private final TourAdvancedRepository repository;

    private final ToursAdvancedContract.View view;

    private SearchingRequest request;

    private ToursSortType sortType = ToursSortType.TOURS_BY_PRICE;

    private TourCurrencyType currencyType = TourCurrencyType.DOLLAR;

    private boolean firstLoad = true;

    public TourAdvancedPresenter(TourAdvancedRepository repository,
                                 ToursAdvancedContract.View view) {
        this.repository = repository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadTours(false);
    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TourFilteringActivity.RESULT_REQUEST:
                    SearchingRequest entity =
                            data.getParcelableExtra(TourFilteringActivity.ON_REQUEST);
                    if (entity != null) {
                        request = entity;
                        repository.refreshTours();
                    }
                    break;
                case FeedBackActivity.REQUEST_SEND_FEEDBACK:
                    view.showSuccesfullyPostedFeedBackMessage();
                    break;
            }
        }
    }

    @Override
    public void loadTours(boolean forceUpdate) {
        if (firstLoad) {
            request = new SearchingRequest();
            request.setCountry(new Country("id"));//TODO populate
            request.setCity(new From_Cities("id"));//TODO populate
            request.getRatingSet().add(new Hotel_Rating("3"));
            request.getRatingSet().add(new Hotel_Rating("78"));
            request.setAdultAmount(2);
            request.setNightFrom(2);
            request.setNightTill(7);
            request.setDateFrom(DateUtils.getCurrentDate());
            request.setDateTill(DateUtils.getDateAfterWeek());
        }
        loadToursByRequest(request, forceUpdate || firstLoad);
        firstLoad = false;
    }

    @Override
    public void loadToursByRequest(SearchingRequest request, boolean forceUpdate) {

    }

    @Override
    public void showFilterLabel() {

    }

    @Override
    public void openTourDetails(@NonNull TourAdvanced requestedTour) {

    }

    @Override
    public void setSotring(ToursSortType requestType) {

    }

    @Override
    public void setCurrencyType(TourCurrencyType requestType) {

    }

    @Override
    public ToursSortType getSorting() {
        return null;
    }

    @Override
    public TourCurrencyType getCurrencyType() {
        return null;
    }

    @Override
    public void findTours() {

    }

    @Override
    public void stopBackgroundLoading() {

    }

    private void loadTours(final SearchingRequest request,
                           boolean forceUpdate,
                           final boolean showLoadingUI) {
        if (showLoadingUI) {
            view.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            repository.refreshTours();
        }

        repository.getToursByRequest(request, new TourAdvancedDataSource.LoadToursCallback() {
            @Override
            public void onToursLoaded(TourAdvancedResponse tourResponse) {
                if (!view.isActive()) {
                    return;
                }

                if(showLoadingUI){
                    view.setLoadingIndicator(false);
                }
                processTours(tourResponse, true);
            }

            @Override
            public void onDataNotAvailable() {

            }

            @Override
            public void onNotAvailableConnection() {

            }
        });
    }

    private void processTours(TourAdvancedResponse response, boolean firstProcess){

    }
}
