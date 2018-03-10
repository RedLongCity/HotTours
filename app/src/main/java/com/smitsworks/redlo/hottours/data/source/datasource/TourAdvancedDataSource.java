package com.smitsworks.redlo.hottours.data.source.datasource;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.models.TourResponse;

/**
 * Created by redlongcity on 10.03.2018.
 */

public interface TourAdvancedDataSource {

    interface LoadToursCallback {

        void onToursLoaded(TourAdvancedResponse tourResponse);

        void onDataNotAvailable();

        void onNotAvailableConnection();
    }

    interface GetTourCallback {

        void onTourLoaded(TourAdvanced tour);

        void onDataNotAvailable();

        void onNotAvailableConnection();

    }

    void getTours(@NonNull TourAdvancedDataSource.LoadToursCallback callback);

    void getToursByRequest(@NonNull SearchingRequest request, @NonNull TourAdvancedDataSource.LoadToursCallback callback);

    void getTour(@NonNull String tourKey, @NonNull TourAdvancedDataSource.GetTourCallback callback);

    void refreshTours();
}
