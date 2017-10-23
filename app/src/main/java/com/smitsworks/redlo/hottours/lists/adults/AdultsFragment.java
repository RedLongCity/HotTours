package com.smitsworks.redlo.hottours.lists.adults;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.lists.adapters.AdultsAdapter;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 23.10.2017.
 * shows list of adults amounts
 */

public class AdultsFragment extends Fragment implements AdultsContract.View {

    private AdultsContract.Presenter presenter;

    private AdultsAdapter adapter;

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

        return root;
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

    public interface AdultsItemListener{

        void onItemClick(int value);

    }
}
