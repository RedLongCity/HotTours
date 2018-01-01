package com.smitsworks.redlo.hottours.data.source.datasource;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;

/**
 * Created by redlongcity on 04.10.2017.
 * interface for providing access to variable data sources
 */

public interface ToursDataSource {

    interface LoadToursCallback{

        void onToursLoaded(TourResponse tourResponse);

        void onDataNotAvailable();

        void onNotAvailableConnection();

    }

    interface GetTourCallback{

        void onTourLoaded(Tour tour);

        void onDataNotAvailable();

        void onNotAvailableConnection();

    }

    void getTours(@NonNull LoadToursCallback callback);

    void getToursByRequest(@NonNull HotToursRequest request, @NonNull LoadToursCallback callback);

    void getTour(@NonNull Integer tourId,@NonNull GetTourCallback callback);

    void refreshTours();

    void deleteAllTours();
}
