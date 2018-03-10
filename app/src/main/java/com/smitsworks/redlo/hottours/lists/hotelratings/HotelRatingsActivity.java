package com.smitsworks.redlo.hottours.lists.hotelratings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 21.10.2017.
 * this activity shows list of hotel ratings
 */

public class HotelRatingsActivity extends AppCompatActivity {

    public static final int REQUEST_CHOOSE_RATING = 3;

    private HotelRatingsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        HotelRatingsFragment hotelRatingsFragment = (HotelRatingsFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (hotelRatingsFragment == null) {
            hotelRatingsFragment = HotelRatingsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), hotelRatingsFragment, R.id.contentFrame
            );
        }

        presenter = new HotelRatingsPresenter(
                Injection.provideFilterRepository(), hotelRatingsFragment
        );
    }
}
