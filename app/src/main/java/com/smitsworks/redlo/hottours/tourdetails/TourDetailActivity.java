package com.smitsworks.redlo.hottours.tourdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smitsworks.redlo.hottours.R;

/**
 * Created by redlongcity on 13.10.2017.
 * displays tour details screen
 */

public class TourDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TOURS_ID = "TOUR_ID";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tourdetail_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
