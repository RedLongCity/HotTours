package com.smitsworks.redlo.hottours.tours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by redlongcity on 07.10.2017.
 * this activity shows list of tours
 */

public class TourActivity extends AppCompatActivity{

    private static final String CURRENT_SORTING_KEY = "CURRENT_SORTING_KEY";

    private DrawerLayout drawerLayout;

    private ToursPresenter toursPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
