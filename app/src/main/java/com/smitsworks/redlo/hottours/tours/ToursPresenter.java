package com.smitsworks.redlo.hottours.tours;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.ToursRepository;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;

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
        if(TourFilteringActivity.RESULT_REQUEST == requestCode &&
                Activity.RESULT_OK == resultCode){
            Request request =
                    data.getParcelableExtra(TourFilteringActivity.ON_REQUEST);
            loadToursByRequest(request,false);
        }
    }

    @Override
    public void loadTours(boolean forceUpdate) {
        loadTours(forceUpdate || firstLoad,true);
        firstLoad = false;
    }

    private void loadTours(boolean forceUpdate, final boolean showLoadingUI){
        if(showLoadingUI){
            toursView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            toursRepository.refreshTours();
        }

        toursRepository.getTours(new ToursDataSource.LoadToursCallback(){

            @Override
            public void onToursLoaded(TourResponse tourResponse) {
                List<Tour> tourList = tourResponse.getTourList();
                sortTours(tourList);

                if(!toursView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    toursView.setLoadingIndicator(false);
                }

                processTours(tourList);
                toursView.showSuccessfullyLoadedMessage();
            }



            @Override
            public void onDataNotAvailable() {
                if(!toursView.isActive()){
                    return;
                }
                toursView.showLoadingTourError();
            }
        });
    }

    @Override
    public void loadToursByRequest(Request request, boolean forceUpdate) {
        loadTours(request, forceUpdate || firstLoad, true);
        firstLoad = false;
    }

    private void loadTours(Request request,
                           boolean forceUpdate,
                           final boolean showLoadingUI){
        if(showLoadingUI){
            toursView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            toursRepository.refreshTours();
        }

        toursRepository.getToursByRequest(request, new ToursDataSource.LoadToursCallback() {
            @Override
            public void onToursLoaded(TourResponse tourResponse) {
                List<Tour> tourList = tourResponse.getTourList();
                sortTours(tourList);

                if(!toursView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    toursView.setLoadingIndicator(false);
                }
                processTours(tourList);
                toursView.showSuccessfullyLoadedMessage();
            }

            @Override
            public void onDataNotAvailable() {
                if(!toursView.isActive()){
                    return;
                }
                toursView.showLoadingTourError();
            }
        });
    }

    private void processTours(List<Tour> tours){
        if(tours.isEmpty()){
            processEmptyTours();
        }else{
            toursView.showTours(tours);
            showFilterLabel();
        }
    }

    private void processEmptyTours(){
        toursView.showNoTours();
    }

    @Override
    public void showFilterLabel() {
        toursView.showFilteringPopUpMenu();
    }

    @Override
    public void openTourDetails(@NonNull Tour requestedTour) {
        checkNotNull(requestedTour);
        toursView.showTourDetailsUi(requestedTour.getId());
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

    private void sortTours(List<Tour> tourList){
        switch(sortType){
            case ALL_TOURS:
                break;
            case TOURS_BY_COUNTRY:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getCountry().getName().compareTo(o2.getCountry().getName());
                    }
                });
                break;
            case TOURS_BY_CITY:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getFrom_Cities().getName().compareTo(o2.getFrom_Cities().getName());
                    }
                });
                break;
            case TOURS_BY_DURATION:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getDuration()-o2.getDuration();
                    }
                });
                break;
            case TOURS_BY_ADULT:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getAdult_Amount()-o2.getAdult_Amount();
                    }
                });
                break;
            case TOURS_BY_CHILDREN:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getChild_Amount()-o2.getChild_Amount();
                    }
                });
                break;
            case TOURS_BY_DATEFROM:
                Collections.sort(tourList,new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getDate_From().compareTo(o2.getDate_From());
                    }
                });
                break;
        }
    }

    }
