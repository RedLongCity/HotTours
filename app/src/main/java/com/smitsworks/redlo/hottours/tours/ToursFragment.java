package com.smitsworks.redlo.hottours.tours;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.Dialog;
import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.tourdetails.TourDetailActivity;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringActivity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by redlongcity on 05.10.2017.
 * Display grid of tours
 */

public class ToursFragment extends Fragment implements ToursContract.View {

    public static final int REQUEST_FIND_TOURS = 1;

    private ToursContract.Presenter presenter;

    private ToursAdapter adapter;

    private View noToursView;

    private ImageView noToursIcon;

    private TextView noToursMainView;

    private LinearLayout toursView;

    private FloatingActionButton button;

    private DialogFragment dialogFragment;

    private TourCurrencyType currencyType = TourCurrencyType.DOLLAR;

    private PopupMenu popUp;

    public ToursFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ToursAdapter(new ArrayList<Tour>(), itemListener, currencyType);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopBackgroundLoading();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button = (FloatingActionButton) getActivity().findViewById(R.id.fab_search_tours);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findTours();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tours_frag, container, false);

        ListView listView = (ListView) root.findViewById(R.id.tours_list);
        listView.setAdapter(adapter);
        toursView = (LinearLayout) root.findViewById(R.id.toursLL);

        noToursView = root.findViewById(R.id.noTours);
        noToursIcon = (ImageView) root.findViewById(R.id.noToursIcon);
        noToursMainView = (TextView) root.findViewById(R.id.noToursMain);

        dialogFragment = new Dialog();

        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        swipeRefreshLayout.setScrollUpChild(listView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                presenter.loadTours(true);
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sorting:
                showFilteringPopUpMenu();
                break;
            case R.id.menu_refresh:
                presenter.loadTours(true);
                break;
            case R.id.menu_currency:
                showCurrencyTypePopUpMenu();
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tours_fragment_menu, menu);
    }

    /**
     * Listener for clicks on tour in the ListView.
     */
    ToursItemListener itemListener = new ToursItemListener() {
        @Override
        public void onTourClick(Tour clickedTour) {
            presenter.openTourDetails(clickedTour);
        }
    };

    @Override
    public void setCurrencyType(TourCurrencyType requestType) {
        currencyType = requestType;
    }

    @Override
    public TourCurrencyType getTourCurrencyType() {
        return currencyType;
    }


    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
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
    public void showTours(List<Tour> tours) {
        adapter.replaceData(tours);

        toursView.setVisibility(View.VISIBLE);
        noToursView.setVisibility(View.GONE);
    }

    @Override
    public void showTourDetailsUi(String key) {
        Intent intent = new Intent(getContext(), TourDetailActivity.class);
        intent.putExtra(TourDetailActivity.EXTRA_TOUR_KEY, key);
        intent.putExtra(TourDetailActivity.EXTRA_CURRENCY_TYPE, currencyType);
        startActivity(intent);
    }

    @Override
    public void showLoadingTourError() {
        setLoadingIndicator(false);
        showNoTours();
        showMessage(getString(R.string.tours_load_fail));
    }

    @Override
    public void showNoTours() {
        showNoToursViews(
                getString(R.string.tours_not_founded),
                R.drawable.ic_cancel_white_24dp
        );
        showMessage(getString(R.string.tours_not_founded));
    }

    @Override
    public void showUpdatingTours() {
        showNoToursViews(
                getString(R.string.tours_updating),
                R.drawable.ic_refresh_white_24dp
        );
        showMessage(getString(R.string.tours_updating));
    }

    @Override
    public void showSuccessfullyLoadedMessage() {
        showMessage(getString(R.string.tours_succesfully_loaded));
    }

    @Override
    public void showSuccessfullyUpdatedMessage() {
        showMessage(getString(R.string.tours_succesfully_updated));
    }

    @Override
    public void showSuccessfullyPostedOrderMessage() {
        showMessage(getString(R.string.posting_success));
    }

    @Override
    public void showSuccesfullyPostedFeedBackMessage() {
        showMessage(getString(R.string.posting_feedback_success));
    }

    @Override
    public void showUpdatingMessage() {
        showMessage(getString(R.string.started_updating));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showFilteringPopUpMenu() {
        if (popUp == null) {
            popUp = new PopupMenu(getContext(),
                    getActivity().findViewById(R.id.menu_sorting));
            popUp.getMenuInflater().inflate(R.menu.sorting_tours, popUp.getMenu());

            popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.by_date:
                            presenter.setSotring(ToursSortType.TOURS_BY_DATEFROM);
                            break;
                        case R.id.by_price:
                            presenter.setSotring(ToursSortType.TOURS_BY_PRICE);
                            break;
                    }
                    presenter.loadTours(false);
                    return true;
                }
            });
        }

        popUp.show();
    }

    @Override
    public void showCurrencyTypePopUpMenu() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.menu_sorting));
        popup.getMenuInflater().inflate(R.menu.currency_tours, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hryvna:
                        setCurrencyType(TourCurrencyType.HRYVNA);
                        adapter.setCurrencyType(currencyType);
                        break;
                    case R.id.euro:
                        setCurrencyType(TourCurrencyType.EURO);
                        adapter.setCurrencyType(currencyType);
                        break;
                    case R.id.dollar:
                        setCurrencyType(TourCurrencyType.DOLLAR);
                        adapter.setCurrencyType(currencyType);
                        break;
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        popup.show();
    }

    @Override
    public void setPresenter(ToursContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void findToursUI() {
        Intent intent = new Intent(getContext(), TourFilteringActivity.class);
        startActivityForResult(intent, REQUEST_FIND_TOURS);
    }

    @Override
    public void showNotAvailableConnection() {
        dialogFragment.show(getFragmentManager(), getString(R.string.no_connection_dialog_message));
        showNoTours();
        setLoadingIndicator(false);
    }

    private void showNoToursViews(String mainText, int iconRes) {
        toursView.setVisibility(View.GONE);
        noToursView.setVisibility(View.VISIBLE);

        noToursMainView.setText(mainText);
        noToursIcon.setImageDrawable(getResources().getDrawable(iconRes));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    public interface ToursItemListener {

        void onTourClick(Tour clickedTour);

    }
}
