package com.smitsworks.redlo.hottours.lists.adults;

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
import android.widget.ListView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.lists.adapters.AdultsAdapter;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;
import com.smitsworks.redlo.hottours.tours.ScrollChildSwipeRefreshLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 23.10.2017.
 * shows list of adults amounts
 */

public class AdultsFragment extends Fragment implements AdultsContract.View {

    private AdultsContract.Presenter presenter;

    private AdultsAdapter adapter;

    private View noElementsView;

    private ImageView noElementsIcon;

    private TextView noElementsMainView;

    public AdultsFragment() {
    }

    public AdultsFragment newInstance(){
        return new AdultsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AdultsAdapter(10,itemListener);
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
                presenter.loadAdults();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noElementsView.setVisibility(View.GONE);
        showSuccessfullyLoadedMessage();
    }

    AdultsItemListener itemListener = new AdultsItemListener() {
        @Override
        public void onItemClick(int value) {
            presenter.chooseAdultsAmount(value);
        }
    };

    @Override
    public void chooseAdultsAmountUI(Integer amount) {
        Intent intent = new Intent();
        intent.putExtra(TourFilteringPresenter.ADULTS_EXTRA,amount);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(AdultsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
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
    public void showAdults() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingAdultsError() {
        showMessage(getString(R.string.load_adults_error));
    }

    @Override
    public void showSuccessfullyLoadedMessage() {
        showMessage(getString(R.string.load_adults_success));
    }

    private void showMessage(String message){
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    public interface AdultsItemListener{

        void onItemClick(int value);

    }
}
