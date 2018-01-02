package com.smitsworks.redlo.hottours.tourdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 13.10.2017.
 * displays tour details screen
 */

public class TourDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TOUR_KEY = "TOUR_KEY";

    public static final String EXTRA_CURRENCY_TYPE = "CURRENCY_TYPE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tour_detail_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        String tourKey = getIntent().getStringExtra(EXTRA_TOUR_KEY);

        TourCurrencyType currencyType = (TourCurrencyType) getIntent().getSerializableExtra(EXTRA_CURRENCY_TYPE);

        TourDetailFragment tourDetailFragment = (TourDetailFragment) getSupportFragmentManager().
                findFragmentById(R.id.content_frame);

        if (tourDetailFragment == null) {
            tourDetailFragment = TourDetailFragment.newInstance(tourKey);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tourDetailFragment,R.id.content_frame);


            new TourDetailPresenter(tourKey,
                    currencyType,
                    Injection.provideToursRepository(getApplicationContext()),
                    tourDetailFragment);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
