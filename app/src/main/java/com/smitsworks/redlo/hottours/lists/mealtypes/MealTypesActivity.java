package com.smitsworks.redlo.hottours.lists.mealtypes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 21.10.2017.
 * this activity shows list of available meal types
 */

public class MealTypesActivity extends AppCompatActivity{

    public static final int REQUEST_CHOOSE_MEAL_TYPE = 4;

    private MealTypesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        MealTypesFragment mealTypesFragment = (MealTypesFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (mealTypesFragment == null) {
            mealTypesFragment = MealTypesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),mealTypesFragment,R.id.contentFrame);
        }

        presenter = new MealTypesPresenter(
                Injection.provideFilterRepository(getApplicationContext()),mealTypesFragment
        );
    }
}
