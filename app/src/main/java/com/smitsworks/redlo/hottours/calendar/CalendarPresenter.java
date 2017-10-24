package com.smitsworks.redlo.hottours.calendar;

import android.support.annotation.NonNull;

import java.sql.Timestamp;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 24.10.2017.
 * listens to user actions from UI, retrieves the data and upgrade the UI
 */

public class CalendarPresenter implements CalendarContract.Presenter {

    private CalendarContract.View calendarView;

    public CalendarPresenter(@NonNull CalendarContract.View calendarView) {
        this.calendarView = checkNotNull(calendarView);

        calendarView.setPresenter(this);
    }

    @Override
    public void chooseDate(@NonNull Timestamp date) {
        calendarView.chooseDateUI(date);
    }

    @Override
    public void start() {

    }
}
