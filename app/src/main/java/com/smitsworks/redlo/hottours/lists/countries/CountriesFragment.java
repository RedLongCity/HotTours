package com.smitsworks.redlo.hottours.lists.countries;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.lists.adapters.CountryAdapter;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringContract;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;
import com.smitsworks.redlo.hottours.tours.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 19.10.2017.
 * Displays list of countries
 */

public class CountriesFragment extends Fragment implements CountriesContract.View{

    private CountriesContract.Presenter presenter;

    private CountryAdapter adapter;

    private View noElementsView;

    private ImageView noElementsIcon;

    private TextView noElementsMainView;

    private LinearLayout elementsView;

    public CountriesFragment() {
    }

    public static CountriesFragment newInstance(){
        return new CountriesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CountryAdapter(new ArrayList<Country>(),itemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_frag,container,false);

        ListView listView = (ListView) root.findViewById(R.id.elements_list);
        listView.setAdapter(adapter);
        elementsView = (LinearLayout) root.findViewById(R.id.elementsLL);

        noElementsView = root.findViewById(R.id.noElements);
        noElementsIcon = (ImageView) root.findViewById(R.id.noElementsIcon);
        noElementsMainView = (TextView) root.findViewById(R.id.noElementsMain);

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(),R.color.colorPrimary),
                ContextCompat.getColor(getActivity(),R.color.colorAccent),
                ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                presenter.loadCountries(false);
            }
        });
        setHasOptionsMenu(true);

        return root;
    }

    CountriesItemListener itemListener = new CountriesItemListener() {
        @Override
        public void onCountryClick(Country clickedCountry) {
            presenter.chooseCountry(clickedCountry);
        }
    };

    @Override
    public void setLoadingIndicator(final boolean active) {
        if(getView()==null){
            return;
        }

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showLoadingCountriesError() {
        showMessage(getString(R.string.load_countries_error));
    }

    @Override
    public void showCountries(List<Country> countries) {
        adapter.replaceData(countries);

        elementsView.setVisibility(View.VISIBLE);
        noElementsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoCountries() {
        showNoCountriesViews(
                getString(R.string.no_countries_found),
                R.drawable.ic_assignment_turned_in_24dp
        );
    }

    @Override
    public void chooseCountryUI(Country country) {
        Intent intent = new Intent();
        intent.putExtra(
                TourFilteringPresenter.COUNTRY_EXTRA,country.getId());
        intent.putExtra(
                TourFilteringPresenter.COUNTRY_NAME_EXTRA,country.getName());
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(CountriesContract.Presenter presenter) {
        presenter = checkNotNull(presenter);
    }

    private void showMessage(String message){
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    private void showNoCountriesViews(String mainText, int iconRes){
        elementsView.setVisibility(View.GONE);
        noElementsView.setVisibility(View.VISIBLE);

        noElementsMainView.setText(mainText);
        noElementsIcon.setImageDrawable(getResources().getDrawable(iconRes));
    }

    public interface CountriesItemListener{

        void onCountryClick(Country clickedCountry);

    }
}