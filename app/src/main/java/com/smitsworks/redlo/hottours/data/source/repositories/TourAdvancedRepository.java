package com.smitsworks.redlo.hottours.data.source.repositories;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedRepository implements TourAdvancedDataSource{
    @Override
    public void getTours(@NonNull LoadToursCallback callback) {

    }

    @Override
    public void getToursByRequest(@NonNull SearchingRequest request, @NonNull LoadToursCallback callback) {

    }

    @Override
    public void getTour(@NonNull String tourKey, @NonNull GetTourCallback callback) {

    }

    @Override
    public void refreshTours() {

    }
}
