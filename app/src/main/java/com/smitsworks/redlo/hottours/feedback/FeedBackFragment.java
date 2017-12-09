package com.smitsworks.redlo.hottours.feedback;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.smitsworks.redlo.hottours.Dialog;
import com.smitsworks.redlo.hottours.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 07.12.2017.
 * Main UI of the feed back screen. User can fill the forms and send feedback;
 */

public class FeedBackFragment extends Fragment implements FeedBackContract.View {

    private FeedBackContract.Presenter presenter;

    private EditText name;

    private EditText email;

    private EditText device;

    private EditText feedBack;

    private DialogFragment dialogFragment;

    public static FeedBackFragment newInstance(){
        return new FeedBackFragment();
    }

    public FeedBackFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        cacheData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_do_feedback);
        fab.setImageResource(R.drawable.ic_email_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createCallback(name.getText().toString(),
                        device.getText().toString(),
                        email.getText().toString(),
                        feedBack.getText().toString());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.feedback_frag,container,false);
        name = (EditText) root.findViewById(R.id.user_name);
        email = (EditText) root.findViewById(R.id.user_email);
        device = (EditText) root.findViewById(R.id.user_device);
        feedBack = (EditText) root.findViewById(R.id.user_feedback);

        dialogFragment = new Dialog();

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void showEmptyDataError() {
        Snackbar.make(name,getString(R.string.empty_data_message),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showToursList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setDevice(String device) {
        this.device.setText(device);
    }

    @Override
    public void setEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public void setFeedBack(String feedBack) {
        this.feedBack.setText(feedBack);
    }

    @Override
    public void showSuccessfullPosting() {
        Snackbar.make(name,getString(R.string.posting_feedback_success),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFailedPosting() {
        Snackbar.make(name,getString(R.string.posting_feedback_fail),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNotAvailableConnection() {
        dialogFragment.show(getFragmentManager(),getString(R.string.no_connection_dialog_message));
        showFailedPosting();
    }

    @Override
    public void setPresenter(FeedBackContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    private void cacheData(){
        String cachedName =  name.getText().toString();
        if (cachedName != null) {
            presenter.cachedName(cachedName);
        }

        String cachedEmail = email.getText().toString();
        if (cachedEmail != null) {
            presenter.cachedEmail(cachedEmail);
        }

        String cachedDevice = device.getText().toString();
        if (cachedDevice != null) {
            presenter.cachedDevice(cachedDevice);
        }

        String cachedFeedBack = feedBack.getText().toString();
        if (cachedFeedBack != null) {
            presenter.cacheFeedBack(cachedFeedBack);
        }
    }
}
