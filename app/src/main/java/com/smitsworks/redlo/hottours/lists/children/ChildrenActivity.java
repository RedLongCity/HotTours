package com.smitsworks.redlo.hottours.lists.children;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 23.10.2017.
 * this activity shows list of available children amounts
 */

public class ChildrenActivity extends AppCompatActivity {

    public static String REQUEST_CHOOSE_CHILDREN_AMOUNT="REQUEST_CHOOSE_CHILDREN_AMOUNT";

    private ChildrenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_act);

        ChildrenFragment fragment = (ChildrenFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new ChildrenFragment().newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),fragment,R.id.contentFrame
            );
        }

        presenter = new ChildrenPresenter(fragment);
    }
}
