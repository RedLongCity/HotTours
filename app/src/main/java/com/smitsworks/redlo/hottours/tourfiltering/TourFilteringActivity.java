package com.smitsworks.redlo.hottours.tourfiltering;

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
import com.smitsworks.redlo.hottours.tours.ToursActivity;
import com.smitsworks.redlo.hottours.utils.ActivityUtils;

/**
 * Created by redlongcity on 22.10.2017.
 * shows fields for creating request to server
 */

public class TourFilteringActivity extends AppCompatActivity{

    public static final String ON_REQUEST="ON_REQUEST";

    public static final int RESULT_REQUEST=1;

    private DrawerLayout drawerLayout;

    TourFilteringPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_filtering_act);
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

        TourFilteringFragment fragment = (TourFilteringFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = TourFilteringFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment,R.id.contentFrame);

            presenter = new TourFilteringPresenter(
                    Injection.provideFilterRepository(),fragment);

        }
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
                                Intent intent = new Intent(TourFilteringActivity.this,ToursActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.settings_navigation_menu_item:
                                break;//we're already on that screen
                            case R.id.feedback_navigation_menu_item:
                                Intent feddBackIntent = new Intent(TourFilteringActivity.this,FeedBackActivity.class);
                                startActivity(feddBackIntent);
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
