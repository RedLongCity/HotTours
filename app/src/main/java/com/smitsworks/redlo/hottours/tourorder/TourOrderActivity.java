package com.smitsworks.redlo.hottours.tourorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 14.10.2017.
 * Displays screen for doing order
 */

public class TourOrderActivity extends AppCompatActivity{

    private TourOrderPresenter presenter;

    private ActionBar actionBar;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_order_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        TourOrderFragment orderFragment = (TourOrderFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        Integer tourId = getIntent().getIntExtra(TourOrderFragment.ARGUMENT_ORDER_TOUR_ID,0);

        actionBar.setTitle(R.string.create_order);

        if (orderFragment == null) {
            orderFragment = TourOrderFragment.newInstance();

            if(getIntent().hasExtra(TourOrderFragment.ARGUMENT_ORDER_TOUR_ID)){
                Bundle bundle = new Bundle();
                bundle.putInt(TourOrderFragment.ARGUMENT_ORDER_TOUR_ID,tourId);
                orderFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    orderFragment,R.id.contentFrame);
        }

            presenter = new TourOrderPresenter(
                    Injection.provideOrderRepository(getApplicationContext()),
                    orderFragment,
                    tourId
            );




    }
}
