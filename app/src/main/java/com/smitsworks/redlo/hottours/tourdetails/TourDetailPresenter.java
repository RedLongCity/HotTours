package com.smitsworks.redlo.hottours.tourdetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smitsworks.redlo.hottours.data.models.Hotel_Image;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.ToursRepository;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.smitsworks.redlo.hottours.tours.TourCurrencyType.*;

/**
 * Created by redlongcity on 08.10.2017.
 * Listen to  user actions from the UI retrieves the data and updates the
 * UI as required
 */

public class TourDetailPresenter implements TourDetailsContract.Presenter {

    private final ToursRepository toursRepository;

    private final TourDetailsContract.View tourDetailsView;

    private TourCurrencyType currencyType;

    @Nullable
    private Integer tourId;

    public TourDetailPresenter(@NonNull Integer tourId,
                               @NonNull TourCurrencyType requestType,
                               @NonNull ToursRepository toursRepository,
                               @NonNull TourDetailsContract.View tourView) {
        this.currencyType = checkNotNull(requestType);
        this.tourId=tourId;
        this.toursRepository = checkNotNull(toursRepository);
        this.tourDetailsView = checkNotNull(tourView);

        tourView.setPresenter(this);
    }

    @Override
    public void orderTour() {
        if (tourId == null) {
            tourDetailsView.showMissingTour();
            return;
        }
        tourDetailsView.orderTour(tourId);
    }

    @Override
    public void start() {
        openTour();
    }

    private void openTour(){
        if(tourId==null){
            tourDetailsView.showMissingTour();
            return;
        }

        tourDetailsView.setLoadingIndicator(true);
        toursRepository.getTour(tourId,new ToursDataSource.GetTourCallback(){

            @Override
            public void onTourLoaded(Tour tour) {
                if(!tourDetailsView.isActive()){
                    return;
                }
                tourDetailsView.setLoadingIndicator(false);
                if (tour == null) {
                    tourDetailsView.showMissingTour();
                }else{
                    showTour(tour);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(!tourDetailsView.isActive()){
                    return;
                }
                tourDetailsView.showMissingTour();
            }
        });
    }

    @Override
    public void setCurrencyType(TourCurrencyType requestType) {
        currencyType=requestType;
    }

    @Override
    public TourCurrencyType getCurrencyType() {
        return currencyType;
    }

    private void showTour(@NonNull Tour tour){
        String countryName=null;

        if (tour.getCountry() != null) {
            countryName = tour.getCountry().getName();
        }

        String region = tour.getRegion();
        String hotelName = tour.getHotel();
        String hotelRating=null;

        if (tour.getHotel_Rating() != null) {
            hotelRating = tour.getHotel_Rating().getName();
        }

        String mealType=null;

        if (tour.getMeal_Type() != null) {
            mealType=tour.getMeal_Type().getName_full();
        }

        Integer adultAmount = tour.getAdult_Amount();
        Integer childrenAmount = tour.getChild_Amount();
        Integer duration = tour.getDuration();
        Date dateFrom = tour.getDate_From();
        Integer priceValue=null;
        String currencySymbol = null;
        String currencyId=null;
        switch (getCurrencyType()){
            case DOLLAR:
                currencyId = "1";
                currencySymbol = "$";
                break;
            case HRYVNA:
                currencyId="2";
                currencySymbol="грн";
                break;
            case EURO:
                currencyId = "10";
                currencySymbol="€";
                break;
        }
        HashSet<Price> priceHashSet = (HashSet<Price>) tour.getPrices();
        Iterator<Price> it = priceHashSet.iterator();
        while(it.hasNext()){
            Price price = it.next();
            if(price.getCurrency().getId().equals(currencyId)){
                priceValue=price.getCost();
            }
        }
        String fromCity = tour.getFrom_Cities().getName();
        String transportType = tour.getTransport_Type();
        String hotelImageURL = null;
        HashSet<Hotel_Image> imageSet = (HashSet<Hotel_Image>) tour.getHotel_ImageSet();
        if(imageSet!=null){
            Iterator<Hotel_Image> iterator = imageSet.iterator();
            while (iterator.hasNext()){
                Hotel_Image image = iterator.next();
                hotelImageURL = image.getFull();
            }
        }

        if (countryName != null) {
            tourDetailsView.showCountryName(countryName);
        }else{
            tourDetailsView.hideCountryName();
        }

        if (region != null) {
            tourDetailsView.showRegion(region);
        }else{
            tourDetailsView.hideRegion();
        }

        if(hotelName!=null){
            tourDetailsView.showHotelName(hotelName);
        }else{
            tourDetailsView.hideHotelName();
        }

        if (hotelRating != null) {
            tourDetailsView.showHotelRating(hotelRating);
        }else{
            tourDetailsView.hideHotelRating();
        }

        if (mealType != null) {
            tourDetailsView.showMealType(mealType);
        }else{
            tourDetailsView.hideMealType();
        }

        if (adultAmount != null) {
            tourDetailsView.showAdultAmount(adultAmount);
        }else{
            tourDetailsView.hideAdultAmount();
        }

        if (childrenAmount != null) {
            tourDetailsView.showChildrenAmount(childrenAmount);
        }else{
            tourDetailsView.hideChildrenAmount();
        }

        if (duration != null) {
            tourDetailsView.showDuration(duration);
        }else{
            tourDetailsView.hideDuration();
        }

        if (dateFrom != null) {
            tourDetailsView.showDateFrom(dateFrom);
        }else{
            tourDetailsView.hideDateFrom();
        }

        if (priceValue != null) {
            tourDetailsView.showPriceValue(priceValue);
        }else{
            tourDetailsView.hidePriceValue();
        }

        if (currencySymbol != null) {
            tourDetailsView.showCurrencySymbol(currencySymbol);
        }else{
            tourDetailsView.hideCurrencySymbol();
        }

        if (fromCity != null) {
            tourDetailsView.showFromCity(fromCity);
        }else{
            tourDetailsView.hideFromCity();
        }

        if (transportType != null) {
            tourDetailsView.showTransportType(transportType);
        }else{
            tourDetailsView.hideTransportType();
        }

        if (hotelImageURL != null) {
            tourDetailsView.showImage(hotelImageURL);
        }else{
            tourDetailsView.hideImage();
        }

    }
}
