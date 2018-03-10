package com.smitsworks.redlo.hottours.data.source.repositories;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedRepository implements TourAdvancedDataSource {

    private static TourAdvancedRepository INSTANCE = null;

    private final TourAdvancedDataSource remoteDataSource;

    private TourAdvancedResponse cachedTours;

    boolean cacheIsDirty = false;

    public TourAdvancedRepository(TourAdvancedDataSource remoteDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }

    public static TourAdvancedRepository getINSTANCE(TourAdvancedDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TourAdvancedRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTours(@NonNull LoadToursCallback callback) {
        if (cachedTours != null && !cacheIsDirty) {
            callback.onToursLoaded(cachedTours);
            return;
        }

        if (cacheIsDirty) {
            getToursFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getToursByRequest(@NonNull SearchingRequest request, @NonNull LoadToursCallback callback) {
        if (request != null && !cacheIsDirty) {
            callback.onToursLoaded(cachedTours);
            return;
        }

        if (cacheIsDirty) {
            getToursFromRemoteDataSource(request, callback);
        }
    }

    @Override
    public void getTour(@NonNull String tourKey, @NonNull GetTourCallback callback) {
        if (tourKey != null && cachedTours != null) {
            for (TourAdvanced tour : cachedTours.getTourList()
                    ) {
                if (tour.getKey().equalsIgnoreCase(tourKey)) {
                    callback.onTourLoaded(tour);
                    return;
                }
            }
            callback.onDataNotAvailable();
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void refreshTours() {
        cacheIsDirty = true;
    }

    private void getToursFromRemoteDataSource(@NonNull final LoadToursCallback callback) {
        remoteDataSource.getTours(new LoadToursCallback() {
            @Override
            public void onToursLoaded(TourAdvancedResponse tourResponse) {
                refreshCache(tourResponse);
                callback.onToursLoaded(tourResponse);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onNotAvailableConnection() {
                callback.onNotAvailableConnection();
            }
        });
    }

    private void getToursFromRemoteDataSource(@NonNull SearchingRequest request,
                                              @NonNull final LoadToursCallback callback) {
        remoteDataSource.getToursByRequest(request, new LoadToursCallback() {
            @Override
            public void onToursLoaded(TourAdvancedResponse tourResponse) {
                refreshCache(tourResponse);
                callback.onToursLoaded(tourResponse);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onNotAvailableConnection() {
                callback.onNotAvailableConnection();
            }
        });
    }

    private void refreshCache(TourAdvancedResponse response) {
        if (cachedTours != null) {
            cachedTours = null;
        }
        cachedTours = response;
        cacheIsDirty = false;
    }

}
