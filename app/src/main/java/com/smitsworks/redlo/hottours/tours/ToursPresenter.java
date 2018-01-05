package com.smitsworks.redlo.hottours.tours;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.ToursRepository;
import com.smitsworks.redlo.hottours.feedback.FeedBackActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.utils.ComeBackUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by redlongcity on 04.10.2017.
 * Listens to user actions from the UI, retrieves the data and uprades the UI
 */

public class ToursPresenter implements ToursContract.Presenter {

    private final ToursRepository toursRepository;

    private final ToursContract.View toursView;

    private HotToursRequest request;

    private ToursSortType sortType = ToursSortType.ALL_TOURS;

    private TourCurrencyType currencyType = TourCurrencyType.DOLLAR;

    private boolean firstLoad = true;

    public ToursPresenter(@NonNull ToursRepository toursRepository,
                          @NonNull ToursContract.View toursView) {
        this.toursRepository = checkNotNull(toursRepository);
        this.toursView = checkNotNull(toursView);

        toursView.setPresenter(this);
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
                    HotToursRequest entity =
                            data.getParcelableExtra(TourFilteringActivity.ON_REQUEST);
                    if (entity != null) {
                        request = entity;
                        toursRepository.refreshTours();
                    }
                    break;
                case FeedBackActivity.REQUEST_SEND_FEEDBACK:
                    toursView.showSuccesfullyPostedFeedBackMessage();
                    break;
            }
        }
    }

    @Override
    public void loadTours(boolean forceUpdate) {
        if (firstLoad) {
            request = new HotToursRequest();
            request.setHotel_Rating("3:78");
            request.setNight_From(2);
            request.setNight_Till(7);
        }
        loadToursByRequest(request, forceUpdate || firstLoad);
        firstLoad = false;
    }


    @Override
    public void loadToursByRequest(HotToursRequest request, boolean forceUpdate) {
        loadTours(request, forceUpdate || firstLoad, true);
    }

    private void loadTours(final HotToursRequest request,
                           boolean forceUpdate,
                           final boolean showLoadingUI) {
        if (showLoadingUI) {
            toursView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            toursRepository.refreshTours();
        }
        toursRepository.getToursByRequest(request, new ToursDataSource.LoadToursCallback() {
            @Override
            public void onToursLoaded(TourResponse tourResponse) {
                if (!toursView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    toursView.setLoadingIndicator(false);
                }
                processTours(tourResponse, true);
            }

            @Override
            public void onDataNotAvailable() {
                if (!toursView.isActive()) {
                    return;
                }
                toursView.showLoadingTourError();
            }

            @Override
            public void onNotAvailableConnection() {
                if (!toursView.isActive()) {
                    return;
                }
                toursView.showNotAvailableConnection();
            }
        });
    }

    private void processTours(TourResponse tourResponse, boolean firstProcess) {
        if (tourResponse.getComeBackDelay() > 0) {
            if (firstProcess) {
                toursView.setLoadingIndicator(true);
            }
            showTours(tourResponse.getTourList(), true, firstProcess);
            ComeBackUtils utils = ComeBackUtils.getInstance(toursRepository);
            utils.start(tourResponse.getComeBackDelay(),
                    request,
                    toursRepository,
                    new ComeBackUtils.ComeBackCallBack() {
                        @Override
                        public void onToursLoaded(TourResponse tourResponse) {
                            processTours(tourResponse, false);
                        }

                        @Override
                        public void onDataNotAvailable() {
                            if (!toursView.isActive()) {
                                return;
                            }
                            toursView.showLoadingTourError();
                        }

                        @Override
                        public void onNotAvailableConnection() {
                            if (!toursView.isActive()) {
                                return;
                            }
                            toursView.showNotAvailableConnection();
                        }
                    });
        } else {
            if (!firstProcess) {
                toursView.setLoadingIndicator(false);
            }
            showTours(tourResponse.getTourList(), false, firstProcess);
        }
    }

    private void showTours(List<Tour> tourList, boolean hasComeBackDelay, boolean firstLoad) {
        if (hasComeBackDelay) {
            if (firstLoad) {
                if (tourList == null || tourList.isEmpty()) {
                    toursView.showUpdatingTours();
                    toursView.showUpdatingMessage();
                    return;
                }
                sortTours(tourList);
                toursView.showUpdatingMessage();
                toursView.showTours(tourList);
                return;
            }
        } else {
            if (tourList == null || tourList.isEmpty()) {
                processEmptyTours();
                return;
            }
            if (!firstLoad) {
                toursView.showSuccessfullyUpdatedMessage();
                sortTours(tourList);
                toursView.showTours(tourList);
                return;
            } else {
                toursView.showSuccessfullyLoadedMessage();
                sortTours(tourList);
                toursView.showTours(tourList);
                return;
            }
        }
    }

    private void processEmptyTours() {
        toursView.showNoTours();
    }

    @Override
    public void showFilterLabel() {
        toursView.showFilteringPopUpMenu();
    }

    @Override
    public void openTourDetails(@NonNull Tour requestedTour) {
        checkNotNull(requestedTour);
        stopBackgroundLoading();
        toursView.showTourDetailsUi(requestedTour.getKey());
    }

    @Override
    public void setSotring(ToursSortType requestType) {
        sortType = requestType;
    }

    @Override
    public ToursSortType getSorting() {
        return sortType;
    }

    @Override
    public void findTours() {
        stopBackgroundLoading();
        toursView.findToursUI();
    }

    @Override
    public TourCurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public void setCurrencyType(TourCurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public void stopBackgroundLoading() {
        ComeBackUtils utils = ComeBackUtils.getInstance(toursRepository);
        toursView.setLoadingIndicator(false);
        utils.stop();
    }

    private void sortTours(List<Tour> tourList) {
        switch (sortType) {
            case ALL_TOURS:
                break;
            case TOURS_BY_COUNTRY:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getCountry().getName().compareTo(o2.getCountry().getName());
                    }
                });
                break;
            case TOURS_BY_CITY:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getFrom_Cities().getName().compareTo(o2.getFrom_Cities().getName());
                    }
                });
                break;
            case TOURS_BY_DURATION:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getDuration() - o2.getDuration();
                    }
                });
                break;
            case TOURS_BY_ADULT:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getAdult_Amount() - o2.getAdult_Amount();
                    }
                });
                break;
            case TOURS_BY_CHILDREN:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getChild_Amount() - o2.getChild_Amount();
                    }
                });
                break;
            case TOURS_BY_DATEFROM:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getDate_From().compareTo(o2.getDate_From());
                    }
                });
                break;
            case TOURS_BY_PRICE:
                Collections.sort(tourList, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        String currencyId = "1";
                        switch(currencyType){
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
                            if(price != null && price.getCurrency() != null){
                                if(price.getCurrency().getId().equals(currencyId)){
                                    o1Cost = price.getCost();
                                }
                            }
                        }
                        for (Price price : o2.getPrices()) {
                            if(price.getCurrency().getId().equals(currencyId)){
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
