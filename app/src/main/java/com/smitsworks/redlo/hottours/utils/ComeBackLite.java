package com.smitsworks.redlo.hottours.utils;

import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvancedResponse;
import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.data.source.remote.TourAdvancedRemoteDataSource;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class ComeBackLite {

    private static ComeBackLite INSTANCE = null;

    private Timer timer;

    private TimerTask task;

    private ComeBackLite() {

    }

    public static ComeBackLite getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ComeBackLite();
        }
        return INSTANCE;
    }

    public void start(TimerTask task, long delay) {
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(task, delay);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private TimerTask getAdvancedTask(final SearchingRequest request,
                                      final TourAdvancedDataSource source,
                                      final TourAdvancedDataSource.LoadToursCallback callback) {
        if (task != null) {
            return task;
        } else {
            task = new TimerTask() {
                @Override
                public void run() {
                    source.refreshTours();
                    source.getToursByRequest(request,
                            new TourAdvancedDataSource.LoadToursCallback() {
                                @Override
                                public void onToursLoaded(TourAdvancedResponse tourResponse) {
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
            };
            return task;
        }
    }

    public void processSearchingRequest(final SearchingRequest request,
                                        final TourAdvancedDataSource source,
                                        final TourAdvancedDataSource.LoadToursCallback callback) {

        TourAdvancedDataSource.LoadToursCallback inside = new TourAdvancedDataSource.LoadToursCallback() {
            @Override
            public void onToursLoaded(TourAdvancedResponse tourResponse) {
                if (tourResponse.getComeBackDelay() > 0) {
                    start(getAdvancedTask(request,
                            source,
                            this
                    ), tourResponse.getComeBackDelay());
                } else {
                    callback.onToursLoaded(tourResponse);
                }
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onNotAvailableConnection() {
                callback.onNotAvailableConnection();
            }
        };
        start(getAdvancedTask(request, source, inside), 0);
    }
}
