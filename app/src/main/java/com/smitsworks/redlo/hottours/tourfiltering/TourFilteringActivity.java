package com.smitsworks.redlo.hottours.tourfiltering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 22.10.2017.
 * shows fields for creating request to server
 */

public class TourFilteringActivity extends AppCompatActivity{

    public static final String ON_REQUEST="ON_REQUEST";

    public static final int RESULT_REQUEST=1;

    TourFilteringPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tour_filtering_act);

        TourFilteringFragment fragment = (TourFilteringFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = TourFilteringFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,R.id.contentFrame);

            presenter = new TourFilteringPresenter(fragment);

        }
    }


}
