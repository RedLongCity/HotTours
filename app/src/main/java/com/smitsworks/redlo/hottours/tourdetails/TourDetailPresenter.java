package com.smitsworks.redlo.hottours.tourdetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.ToursRepository;

import java.sql.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 08.10.2017.
 * Listen to  user actions from the UI retrieves the data and updates the
 * UI as required
 */

public class TourDetailPresenter implements TourDetailsContract.Presenter {

    private final ToursRepository toursRepository;

    private final TourDetailsContract.View tourDetailsView;

    @Nullable
    private Integer tourId;

    public TourDetailPresenter(@NonNull Integer tourId,
                               @NonNull ToursRepository toursRepository,
                               @NonNull TourDetailsContract.View tourView) {
        this.tourId=tourId;
        this.toursRepository = checkNotNull(toursRepository);
        this.tourDetailsView = checkNotNull(tourView);

        tourView.setPresenter(this);
    }

    @Override
    public void loadTour(Integer tourId) {

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

    private void showTour(@NonNull Tour tour){
        String countryName;

        if (tour.getCountry() != null) {
            countryName = tour.getCountry().getName();
        }

        String region = tour.getRegion();
        String hotelName = tour.getHotel();
        String hotelRating;

        if (tour.getHotel_Rating() != null) {
            hotelRating = tour.getHotel_Rating().getName();
        }

        String mealType;

        if (tour.getMeal_Type() != null) {
            mealType=tour.getMeal_Type().getName_full();
        }

        Integer adultAmount = tour.getAdult_Amount();
        Integer childrenAmount = tour.getChild_Amount();
        Integer duration = tour.getDuration();
        Date dateFrom = tour.getDate_From();


    }
}
