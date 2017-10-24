package com.smitsworks.redlo.hottours.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 24.10.2017.
 * shows calendar for choosing date of beginning tour
 */

public class CalendarActivity extends AppCompatActivity {

    public static final String REQUEST_CHOOSE_DATE="REQUEST_CHOOSE_DATE";

    private CalendarPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        CalendarFragment fragment = (CalendarFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new CalendarFragment().newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),fragment,R.id.contentFrame
            );
        }
        presenter = new CalendarPresenter(fragment);
    }
}
