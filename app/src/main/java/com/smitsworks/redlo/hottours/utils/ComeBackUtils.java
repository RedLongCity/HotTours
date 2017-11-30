package com.smitsworks.redlo.hottours.utils;

import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 30.11.2017.
 * class for come back after server updating time
 */

public class ComeBackUtils {

    private static Timer timer;

    public static void start(long delay,
                             final Request request,
                             final ToursDataSource remoteDataSource,
                             final ComeBackCallBack callBack){
        checkNotNull(request);
        checkNotNull(callBack);
        checkNotNull(delay);

        final Handler handler = new Handler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                         remoteDataSource.getToursByRequest(request,
                                 new ToursDataSource.LoadToursCallback() {
                                     @Override
                                     public void onToursLoaded(TourResponse tourResponse) {
                                         callBack.onToursLoaded(tourResponse);
                                     }

                                     @Override
                                     public void onDataNotAvailable() {
                                         callBack.onDataNotAvailable();
                                     }
                                 });
                    }
                });
            }
        },delay);
    }

    public static void stop(){
        if (timer != null) {
            timer.cancel();
        }
    }


    public interface ComeBackCallBack{

        void onToursLoaded(TourResponse tourResponse);

        void onDataNotAvailable();

    }
}
