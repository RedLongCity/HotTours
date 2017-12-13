package com.smitsworks.redlo.hottours.lists.cities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
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

import com.smitsworks.redlo.hottours.Dialog;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.lists.adapters.CitiesAdapter;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;
import com.smitsworks.redlo.hottours.tours.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 20.10.2017.
 * Displays list of cities
 */

public class CitiesFragment extends Fragment implements CitiesContract.View {

    private CitiesContract.Presenter presenter;

    private CitiesAdapter adapter;

    private View noElementsView;

    private ImageView noElementsIcon;

    private TextView noElementsMainView;

    private LinearLayout elementsView;

    private DialogFragment dialogFragment;

    public CitiesFragment() {
    }

    public static CitiesFragment newInstance(){
        return new CitiesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CitiesAdapter(new ArrayList<From_Cities>(),itemListener);
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

        dialogFragment = new Dialog();

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
                presenter.loadCities(false);
            }
        });
        setHasOptionsMenu(true);

        return root;
    }

    CitiesItemListener itemListener = new CitiesItemListener() {
        @Override
        public void onCityClick(From_Cities clickedCity) {
            presenter.chooseCity(clickedCity);
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
    public void showLoadingCitiesError() {
        showMessage(getString(R.string.posting_failed));
        setLoadingIndicator(false);
        showNoCities();
    }

    @Override
    public void showCitites(List<From_Cities> cities) {
        if (cities != null) {
            cities.add(0,getAnyCity());
        }
        adapter.replaceData(cities);

        elementsView.setVisibility(View.VISIBLE);
        noElementsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoCities() {
        showNoCitiesViews(
                getString(R.string.no_cities_founded),
                R.drawable.ic_cancel_black_24dp
        );
    }

    @Override
    public void chooseCityUI(From_Cities city) {
        Intent intent = new Intent();
        intent.putExtra(
                TourFilteringPresenter.CITY_EXTRA,city.getId());
        intent.putExtra(
                TourFilteringPresenter.CITY_NAME_EXTRA,city.getName());
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(CitiesContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showSuccessfullyLoadedMessage() {
        showMessage(getString(R.string.load_cities_success));
    }

    @Override
    public void showNotAwailableConnection() {
        dialogFragment.show(getFragmentManager(),getString(R.string.no_connection_dialog_message));
        setLoadingIndicator(false);
        showNoCities();
        showLoadingCitiesError();
    }

    private void showMessage(String message){
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    private void showNoCitiesViews(String mainText, int iconRes){
        elementsView.setVisibility(View.GONE);
        noElementsView.setVisibility(View.VISIBLE);

        noElementsMainView.setText(mainText);
        noElementsIcon.setImageDrawable(getResources().getDrawable(iconRes));
    }

    private From_Cities getAnyCity(){
        From_Cities city = new From_Cities();
        city.setId("0");
        city.setName(getString(R.string.anyCity));
        return city;
    }

    public interface CitiesItemListener{

        void onCityClick(From_Cities clickedCity);

    }
}
