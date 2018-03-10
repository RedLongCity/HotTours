package com.smitsworks.redlo.hottours.utils;

import com.smitsworks.redlo.hottours.data.models.SearchingRequest;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
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

    public void processAdvance(final TourAdvancedResponse response,
                               final TourAdvancedRemoteDataSource source,
                               TourAdvancedDataSource.GetTourCallback callback) {
        new TimerTask() {
            @Override
            public void run() {
                source.refreshTours();
                source.getToursByRequest((SearchingRequest) response.getRequest(),
                        new TourAdvancedDataSource.LoadToursCallback() {
                    @Override
                    public void onToursLoaded(TourAdvancedResponse tourResponse) {
                        if(tourResponse.getComeBackDelay() > 0){

                        }
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }

                    @Override
                    public void onNotAvailableConnection() {

                    }
                });
            }
        }
    }
}
