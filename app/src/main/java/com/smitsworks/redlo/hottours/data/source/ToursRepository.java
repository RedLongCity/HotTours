package com.smitsworks.redlo.hottours.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.TourResponse;

import java.util.List;

/**
 * Created by redlongcity on 04.10.2017.
 * Concrete implementation for load tours from the data sourde into a cache
 */

public class ToursRepository implements ToursDataSource {

    private static ToursRepository INSTANCE = null;

    private final ToursDataSource remoteDataSource;

    private final ToursDataSource localDataSource;

    TourResponse cachedTours;
    Tour cachedTour;

    boolean cacheIsDirty = false;

    private ToursRepository(ToursDataSource remoteDataSource, ToursDataSource localDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
        this.localDataSource = checkNotNull(localDataSource);
    }

    public static ToursRepository getInstance(ToursDataSource remoteDataSource,
                                              ToursDataSource localDataSource){
        if (INSTANCE == null) {
            INSTANCE = new ToursRepository(remoteDataSource,localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTours(@NonNull LoadToursCallback callback) {
        checkNotNull(callback);

        if(cachedTours !=null && !cacheIsDirty){
            callback.onToursLoaded(cachedTours);
            return;
        }

        if(cacheIsDirty){
            getToursFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getTour(@NonNull Integer tourId, @NonNull GetTourCallback callback) {
        checkNotNull(tourId);
        checkNotNull(callback);

        getTourWithId(tourId,callback);
    }

    @Override
    public void refreshTours() {
        cacheIsDirty = true;
    }

    @Override
    public void deleteAllTours() {
        cachedTours=null;
    }

    private void getToursFromRemoteDataSource(@NonNull final LoadToursCallback callback){
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
        });
    }

    private void refreshCache(TourResponse tourResponse){
        cachedTours = tourResponse;
        cacheIsDirty = false;
    }

    @Nullable
    private void getTourWithId(@NonNull Integer id,@NonNull final GetTourCallback callback){
        checkNotNull(id);
       remoteDataSource.getTour(id, new GetTourCallback() {
            @Override
            public void onTourLoaded(Tour tour){
                callback.onTourLoaded(tour);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

        });
    }
}
