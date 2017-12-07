package com.smitsworks.redlo.hottours.feedback;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.EditText;

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



    @Override
    public void showEmptyDataError() {

    }

    @Override
    public void showToursList() {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setDevice(String device) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setFeedBack(String feedBack) {

    }

    @Override
    public void showSuccessfullPosting() {

    }

    @Override
    public void showFailedPosting() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showNotAvailableConnection() {

    }

    @Override
    public void setPresenter(FeedBackContract.Presenter presenter) {

    }
}
