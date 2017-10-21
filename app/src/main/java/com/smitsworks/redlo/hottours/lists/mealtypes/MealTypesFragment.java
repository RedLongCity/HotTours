package com.smitsworks.redlo.hottours.lists.mealtypes;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.lists.adapters.MealTypesAdapter;
import com.smitsworks.redlo.hottours.tours.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by redlongcity on 21.10.2017.
 * Displays list of meal types
 */

public class MealTypesFragment extends Fragment implements MealTypesContract.View {

    private MealTypesContract.Presenter presenter;

    private MealTypesAdapter adapter;

    private View noElementsView;

    private ImageView noElementsIcon;

    private TextView noElementsMainView;

    private LinearLayout elementsView;

    public MealTypesFragment() {
    }

    public static MealTypesFragment newInstance(){
        return new MealTypesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MealTypesAdapter(new ArrayList<Meal_Type>(),itemListener);
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
                presenter.loadMealTypes(false);
            }
        });
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showLoadingMealTypesError() {

    }

    @Override
    public void showMealTypes(List<Meal_Type> types) {

    }

    @Override
    public void showNoMealTypes() {

    }

    @Override
    public void chooseMealTypeUI(Meal_Type type) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(MealTypesContract.Presenter presenter) {

    }
}
