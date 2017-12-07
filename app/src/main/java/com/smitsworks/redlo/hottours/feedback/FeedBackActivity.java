package com.smitsworks.redlo.hottours.feedback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;


import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;


/**
 * Created by redlongcity on 07.12.2017.
 * Displays screen for sending feedback
 */

public class FeedBackActivity extends AppCompatActivity{

    private FeedBackPresenter presenter;

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
        setContentView(R.layout.feedback_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        FeedBackFragment feedBackFragment = (FeedBackFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (feedBackFragment == null) {
            feedBackFragment = FeedBackFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    feedBackFragment,R.id.contentFrame);
        }

        presenter = new FeedBackPresenter(
                Injection.provideFeedBackRepository(getApplicationContext()),
                feedBackFragment
        );


    }
}
