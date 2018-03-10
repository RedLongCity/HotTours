package com.smitsworks.redlo.hottours.tours;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.smitsworks.redlo.hottours.Injection;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.feedback.FeedBackActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 07.10.2017.
 * this activity shows list of tours
 */

public class ToursActivity extends AppCompatActivity{

    private static final String CURRENT_SORTING_KEY = "CURRENT_SORTING_KEY";

    private DrawerLayout drawerLayout;

    private ToursPresenter toursPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tours_act);

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

        ToursFragment toursFragment = (ToursFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (toursFragment == null) {
            toursFragment = ToursFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),toursFragment,R.id.contentFrame);
        }

        toursPresenter = new ToursPresenter(
                Injection.provideToursRepository(),toursFragment);

        if (savedInstanceState != null) {
            ToursSortType currentSortType = (ToursSortType) savedInstanceState.
                    getSerializable("CURRENT_SORTING_KEY");
            toursPresenter.setSotring(currentSortType);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("CURRENT_SORTING_KEY",toursPresenter.getSorting());
        super.onSaveInstanceState(outState);
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
                                break;//we're already on that screen
                            case R.id.settings_navigation_menu_item:
                                toursPresenter.findTours();
                                break;
                            case R.id.feedback_navigation_menu_item:
                                Intent feddBackIntent = new Intent(ToursActivity.this,FeedBackActivity.class);
                                startActivityForResult(feddBackIntent,FeedBackActivity.REQUEST_SEND_FEEDBACK);
                                break;
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
