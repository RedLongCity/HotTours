package com.smitsworks.redlo.hottours.tourorder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
 * Created by redlongcity on 14.10.2017.
 * Main UI of the tour order screen. User can fill the forms and send order.
 */

public class TourOrderFragment extends Fragment implements TourOrderContract.View {

    public static final String ARGUMENT_ORDER_TOUR_ID = "ORDER_TOUR_ID";

    private TourOrderContract.Presenter presenter;

    private EditText name;

    private EditText phoneNumber;

    private EditText email;

    private EditText city;

    private DialogFragment dialogFragment;

    public static TourOrderFragment newInstance(){
        return new TourOrderFragment();
    }

    public TourOrderFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        cachedData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_do_order);
        fab.setImageResource(R.drawable.ic_done_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createOrder(name.getText().toString(),
                        phoneNumber.getText().toString(),
                        email.getText().toString(),
                        city.getText().toString());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tour_order_frag,container,false);
        name = (EditText) root.findViewById(R.id.user_name);
        phoneNumber = (EditText) root.findViewById(R.id.user_phone_number);
        email = (EditText) root.findViewById(R.id.user_email);
        city = (EditText) root.findViewById(R.id.user_city);

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
    public void setName(String nameValue) {
        name.setText(nameValue);
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        phoneNumber.setText(mobileNumber);
    }

    @Override
    public void setEmail(String emailValue) {
        email.setText(emailValue);
    }

    @Override
    public void setCity(String cityValue) {
        city.setText(cityValue);
    }

    @Override
    public void showSuccessfullPosting() {
        Snackbar.make(name,getString(R.string.posting_success),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showFailedPosting() {
        Snackbar.make(name,getString(R.string.posting_failed),Snackbar.LENGTH_LONG).show();
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
    public void setPresenter(@NonNull TourOrderContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    private void cachedData(){
        String cachedCity = city.getText().toString();
        if (cachedCity != null) {
            presenter.cachedCity(cachedCity);
        }

        String cachedName = name.getText().toString();
        if (cachedName != null) {
            presenter.cachedName(cachedName);
        }

        String cachedEmail = email.getText().toString();
        if (cachedEmail != null) {
            presenter.cachedEmail(cachedEmail);
        }

        String cachedPhone = phoneNumber.getText().toString();
        if (cachedPhone != null) {
            presenter.cachedPhone(cachedPhone);
        }
    }
}
