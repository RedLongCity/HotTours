package com.smitsworks.redlo.hottours.utils;

import com.smitsworks.redlo.hottours.data.models.Request;
import com.smitsworks.redlo.hottours.data.models.TourResponse;
import com.smitsworks.redlo.hottours.data.source.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.ToursRepository;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 30.11.2017.
 * class for come back after server updating time
 */

public class ComeBackUtils {

    private static ComeBackUtils INSTANCE = null;

    private Timer timer;

    private ToursDataSource toursRepository;

    private ComeBackUtils(ToursDataSource toursRepository) {
        this.toursRepository = checkNotNull(toursRepository);
    }

    public static ComeBackUtils getInstance(ToursDataSource toursRepository){
        if (INSTANCE == null) {
            INSTANCE = new ComeBackUtils(toursRepository);
        }
        return INSTANCE;
    }

    public void start(final long delay,
                      final Request request,
                      final ToursDataSource remoteDataSource,
                      final ComeBackCallBack callBack){
        checkNotNull(request);
        checkNotNull(callBack);
        checkNotNull(delay);

        timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        remoteDataSource.refreshTours();
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
                },delay);

    }

    public void stop(){
        if (timer != null) {
            timer.cancel();
        }
    }


    public interface ComeBackCallBack{

        void onToursLoaded(TourResponse tourResponse);

        void onDataNotAvailable();

    }
}
