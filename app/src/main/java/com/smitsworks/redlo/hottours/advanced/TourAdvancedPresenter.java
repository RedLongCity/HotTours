package com.smitsworks.redlo.hottours.advanced;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.TourAdvancedRepository;
import com.smitsworks.redlo.hottours.feedback.FeedBackActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;
import com.smitsworks.redlo.hottours.tours.ToursSortType;
import com.smitsworks.redlo.hottours.utils.ComeBackLite;
import com.smitsworks.redlo.hottours.utils.DateUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        loadTours(request, forceUpdate, true);
    }

    @Override
    public void showFilterLabel() {
        view.showFilteringPopUpMenu();
    }

    @Override
    public void openTourDetails(@NonNull TourAdvanced requestedTour) {
        if (requestedTour != null) {
            stopBackgroundLoading();
            view.showTourDetailsUi(requestedTour.getKey());
        }
    }

    @Override
    public void setSotring(ToursSortType requestType) {
        this.sortType = requestType;
    }

    @Override
    public void setCurrencyType(TourCurrencyType requestType) {
        this.currencyType = requestType;
    }

    @Override
    public ToursSortType getSorting() {
        return sortType;
    }

    @Override
    public TourCurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public void findTours() {
        stopBackgroundLoading();
        view.findToursUI();
    }

    @Override
    public void stopBackgroundLoading() {
        ComeBackLite.getINSTANCE().stop();
        view.setLoadingIndicator(false);
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

        ComeBackLite.getINSTANCE().processSearchingRequest(
                request,
                repository,
                new TourAdvancedDataSource.LoadToursCallback() {
                    @Override
                    public void onToursLoaded(TourAdvancedResponse tourResponse) {
                        if (!view.isActive()) {
                            return;
                        }
                        showTours(tourResponse.getTourList());
                    }

                    @Override
                    public void onDataNotAvailable() {
                        if (!view.isActive()) {
                            return;
                        }
                        view.setLoadingIndicator(false);
                        view.showLoadingTourError();
                    }

                    @Override
                    public void onNotAvailableConnection() {
                        if (!view.isActive()) {
                            return;
                        }
                        view.setLoadingIndicator(false);
                        view.showNotAvailableConnection();
                    }
                });
    }

    private void showTours(List<TourAdvanced> tours) {
        sortTours(tours);
        view.setLoadingIndicator(false);
        view.showTours(tours);
    }

    private void sortTours(List<TourAdvanced> tours) {
        switch (sortType) {
            case TOURS_BY_DATEFROM:
                Collections.sort(tours, new Comparator<TourAdvanced>() {
                    @Override
                    public int compare(TourAdvanced o1, TourAdvanced o2) {
                        return o1.getDateFrom().compareTo(o2.getDateFrom());
                    }
                });
                break;
            case TOURS_BY_PRICE:
                Collections.sort(tours, new Comparator<TourAdvanced>() {
                    @Override
                    public int compare(TourAdvanced o1, TourAdvanced o2) {
                        String currencyId = "1";
                        switch (currencyType) {
                            case EURO:
                                currencyId = "10";
                                break;
                            case DOLLAR:
                                currencyId = "1";
                                break;
                            case HRYVNA:
                                currencyId = "2";
                                break;
                        }
                        int o1Cost = 0;
                        int o2Cost = 0;
                        for (Price price : o1.getPrices()) {
                            if (price != null && price.getCurrency() != null) {
                                if (price.getCurrency().getId().equals(currencyId)) {
                                    o1Cost = price.getCost();
                                }
                            }
                        }
                        for (Price price : o2.getPrices()) {
                            if (price.getCurrency().getId().equals(currencyId)) {
                                o2Cost = price.getCost();
                            }
                        }
                        return o1Cost - o2Cost;
                    }
                });
                break;
        }
    }


}
