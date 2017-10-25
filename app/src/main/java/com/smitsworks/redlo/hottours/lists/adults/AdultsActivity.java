package com.smitsworks.redlo.hottours.lists.adults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 23.10.2017.
 * this activity show list of available adult amounts
 */

public class AdultsActivity extends AppCompatActivity {

    public static final int REQUEST_CHOOSE_ADULTS_AMOUNT=6;

    private AdultsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        AdultsFragment fragment = (AdultsFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new AdultsFragment().newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),fragment,R.id.contentFrame
            );
        }

        presenter = new AdultsPresenter(fragment);
    }
}
