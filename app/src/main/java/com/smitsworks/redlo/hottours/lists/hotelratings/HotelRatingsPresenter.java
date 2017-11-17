package com.smitsworks.redlo.hottours.lists.hotelratings;


import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.data.source.FiltersRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 20.10.2017.
 * Listens to user actions from the UI, retrieves the data and upgrade the UI
 */

public class HotelRatingsPresenter implements HotelRatingsContract.Presenter {

    private final FiltersRepository filtersRepository;

    private final HotelRatingsContract.View hotelRatingsView;

    private boolean firstLoad = true;

    public HotelRatingsPresenter(@NonNull FiltersRepository filtersRepository,
                                 @NonNull HotelRatingsContract.View hotelRatingsView) {
        this.filtersRepository = checkNotNull(filtersRepository);
        this.hotelRatingsView = checkNotNull(hotelRatingsView);

        hotelRatingsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadHotelRatings(false);
    }

    @Override
    public void loadHotelRatings(boolean forceUpdate) {
        loadHotelRatings(forceUpdate || firstLoad,true);
        firstLoad = false;
    }

    private void loadHotelRatings(boolean forceUpdate,final boolean showLoadingUI){
        if(showLoadingUI){
            hotelRatingsView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            filtersRepository.refreshFilters();
        }

        filtersRepository.getHotelRatings(new FiltersRepository.LoadHotelRatingsCallback(){

            @Override
            public void onHotelRatingsLoaded(List<Hotel_Rating> ratings) {
                if(!hotelRatingsView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    hotelRatingsView.setLoadingIndicator(false);
                }
                processHotelRatings(ratings);
                hotelRatingsView.showSuccessfullyLoadedMessage();
            }

            @Override
            public void onDataNotAvailable() {
                if(!hotelRatingsView.isActive()){
                    return;
                }
                hotelRatingsView.showLoadingHotelRatingsError();
            }
        });
    }

    private void processHotelRatings(List<Hotel_Rating> ratings){
        if(ratings.isEmpty()){
            processEmptyRatings();
        }else{
            hotelRatingsView.showHotelRatings(ratings);
        }
    }

    private void processEmptyRatings(){
        hotelRatingsView.showNoHotelRatings();
    }

    @Override
    public void chooseHotelRating(@NonNull Hotel_Rating rating) {
        hotelRatingsView.chooseHotelRatingUI(rating);
    }
}
