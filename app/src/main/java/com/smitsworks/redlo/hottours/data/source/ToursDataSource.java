package com.smitsworks.redlo.hottours.data.source;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;

import java.util.List;

/**
 * Created by redlo on 04.10.2017.
 */

public interface ToursDataSource {

    interface LoadToursCallback{

        void onToursLoaded(TourResponse tourResponse);

        void onDataNotAvailable();

    }

    interface GetTourCallback{

        void onTourLoaded(Tour tour);

        void onDataNotAvailable();
    }

    void getTours(@NonNull LoadToursCallback callback);

    void getTour(@NonNull Integer tourId,@NonNull GetTourCallback callback);

    void refreshTours();

    void deleteAllTours();
}
