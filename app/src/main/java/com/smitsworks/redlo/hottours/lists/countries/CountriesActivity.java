package com.smitsworks.redlo.hottours.lists.countries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 20.10.2017.
 * this activity shows list of available countries
 */

public class CountriesActivity extends AppCompatActivity {

    private CountriesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        CountriesFragment countriesFragment = (CountriesFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (countriesFragment == null) {
            countriesFragment = CountriesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),countriesFragment,R.id.contentFrame
            );
        }

        presenter = new CountriesPresenter(
                Injection.provideFilterRepository(getApplicationContext()),countriesFragment
        );
    }
}
