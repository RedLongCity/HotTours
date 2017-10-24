package com.smitsworks.redlo.hottours.calendar;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

import java.sql.Timestamp;

/**
 * Created by redlongcity on 24.10.2017.
 * This interface describe relationships between presenter and view
 */

public interface CalendarContract {

    interface View extends BaseView<Presenter> {

        void chooseDateUI(Timestamp date);

        void showYear(Integer yearValue);

        void showDate(String date);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void chooseDate(@NonNull Timestamp date);

    }
}
