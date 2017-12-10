package com.smitsworks.redlo.hottours.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.FeedBack;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.tours.ToursActivity;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;


/**
 * Created by redlongcity on 07.12.2017.
 * Displays screen for sending feedback
 */

public class FeedBackActivity extends AppCompatActivity {

    public static final int REQUEST_SEND_FEEDBACK = 3;

    private FeedBackPresenter presenter;

    private DrawerLayout drawerLayout;

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
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.list_navigation_menu_item:
                                Intent intent = new Intent(FeedBackActivity.this,ToursActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.settings_navigation_menu_item:
                                Intent feddBackIntent = new Intent(FeedBackActivity.this,TourFilteringActivity.class);
                                startActivity(feddBackIntent);
                                break;
                            case R.id.feedback_navigation_menu_item:
                                break;//we're already on that screen
                            default:
                                break;
                        }
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }
}
