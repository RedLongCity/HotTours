package com.smitsworks.redlo.hottours.lists.Cities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 20.10.2017.
 * this activity shows list of available cities
 */

public class CitiesActivity extends AppCompatActivity {

    private CitiesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        CitiesFragment citiesFragment = (CitiesFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (citiesFragment != null) {
            citiesFragment = new CitiesFragment().newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),citiesFragment,R.id.contentFrame
            );
        }

        presenter = new CitiesPresenter(
                Injection.provideFilterRepository(getApplicationContext()),citiesFragment
        );
    }
}
