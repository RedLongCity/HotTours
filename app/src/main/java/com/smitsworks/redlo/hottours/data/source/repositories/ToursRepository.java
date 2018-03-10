package com.smitsworks.redlo.hottours.data.source.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

import com.smitsworks.redlo.hottours.data.models.HotToursRequest;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.ToursDataSource;

/**
 * Created by redlongcity on 04.10.2017.
 * Concrete implementation for load tours from the data sourde into a cache
 */

public class ToursRepository implements ToursDataSource {

    private static ToursRepository INSTANCE = null;

    private final ToursDataSource remoteDataSource;

    TourResponse cachedTours;

    boolean cacheIsDirty = false;

    private ToursRepository(ToursDataSource remoteDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }

    public static ToursRepository getInstance(ToursDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ToursRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTours(@NonNull LoadToursCallback callback) {
        checkNotNull(callback);

        if (cachedTours != null && !cacheIsDirty) {
            callback.onToursLoaded(cachedTours);
            return;
        }

        if (cacheIsDirty) {
            getToursFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getToursByRequest(@NonNull HotToursRequest request, @NonNull LoadToursCallback callback) {
        checkNotNull(callback);
        checkNotNull(request);

        if (cachedTours != null && !cacheIsDirty) {
            callback.onToursLoaded(cachedTours);
            return;
        }

        if (cacheIsDirty) {
            getToursByRequestFromRemoteDataSource(request, callback);
        }
    }

    @Override
    public void getTour(@NonNull String tourKey, @NonNull GetTourCallback callback) {
        checkNotNull(tourKey);
        checkNotNull(callback);

        getTourByKey(tourKey, callback);
    }

    @Override
    public void refreshTours() {
        cacheIsDirty = true;
    }

    @Override
    public void deleteAllTours() {
        cachedTours = null;
    }

    private void getToursFromRemoteDataSource(@NonNull final LoadToursCallback callback) {
        remoteDataSource.getTours(new LoadToursCallback() {
            @Override
            public void onToursLoaded(TourResponse tourResponse) {
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

    private void getToursByRequestFromRemoteDataSource(@NonNull HotToursRequest request,
                                                       @NonNull final LoadToursCallback callback) {
        remoteDataSource.getToursByRequest(request, new LoadToursCallback() {
            @Override
            public void onToursLoaded(TourResponse tourResponse) {
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

    private void refreshCache(TourResponse tourResponse) {
        if (cachedTours != null) {
            cachedTours = null;
        }
        cachedTours = tourResponse;
        cacheIsDirty = false;
    }

    @Nullable
    private void getTourByKey(@NonNull String key, @NonNull final GetTourCallback callback) {
        checkNotNull(key);
        checkNotNull(cachedTours);
        for (Tour tour : cachedTours.getTourList()) {
            if (tour.getKey().equals(key)) {
                callback.onTourLoaded(tour);
                return;
            }
        }
        callback.onDataNotAvailable();
    }
}
