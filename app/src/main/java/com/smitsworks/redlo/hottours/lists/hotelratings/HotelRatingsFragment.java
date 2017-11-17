package com.smitsworks.redlo.hottours.lists.hotelratings;

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
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.lists.adapters.HotelRatingsAdapter;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;
import com.smitsworks.redlo.hottours.tours.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 21.10.2017.
 * Displays list of hotel ratings
 */

public class HotelRatingsFragment extends Fragment implements HotelRatingsContract.View {

    private HotelRatingsContract.Presenter presenter;

    private HotelRatingsAdapter adapter;

    private View noElementsView;

    private ImageView noElementsIcon;

    private TextView noElementsMainView;

    private LinearLayout elementsView;

    public HotelRatingsFragment() {
    }

    public static HotelRatingsFragment newInstance(){
        return new HotelRatingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HotelRatingsAdapter(new ArrayList<Hotel_Rating>(),itemListener);
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
                presenter.loadHotelRatings(false);
            }
        });
        setHasOptionsMenu(true);

        return root;
    }

    HotelRatingsItemListener itemListener = new HotelRatingsItemListener() {
        @Override
        public void onHotelRatingClick(Hotel_Rating clickedRating) {
            presenter.chooseHotelRating(clickedRating);
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
    public void showLoadingHotelRatingsError() {
        showMessage(getString(R.string.load_hotel_ratings_error));
    }

    @Override
    public void showHotelRatings(List<Hotel_Rating> ratings) {
        adapter.replaceData(ratings);

        elementsView.setVisibility(View.VISIBLE);
        noElementsView.setVisibility(View.GONE);
    }

    @Override
    public void showNoHotelRatings() {
        showNoHotelRatingsViews(
                getString(R.string.no_hotel_ratings_found),
                R.drawable.ic_assignment_turned_in_24dp
        );
    }

    @Override
    public void chooseHotelRatingUI(Hotel_Rating rating) {
        Intent intent = new Intent();
        intent.putExtra(
                TourFilteringPresenter.HOTEL_RATING_EXTRA,rating.getId());
        intent.putExtra(
                TourFilteringPresenter.HOTEL_RATING_NAME_EXTRA,rating.getName());
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(HotelRatingsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    private void showMessage(String message){
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    private void showNoHotelRatingsViews(String mainText, int iconRes){
        elementsView.setVisibility(View.GONE);
        noElementsView.setVisibility(View.VISIBLE);

        noElementsMainView.setText(mainText);
        noElementsIcon.setImageDrawable(getResources().getDrawable(iconRes));
    }

    public interface HotelRatingsItemListener{

        void onHotelRatingClick(Hotel_Rating clickedRating);

    }
}
