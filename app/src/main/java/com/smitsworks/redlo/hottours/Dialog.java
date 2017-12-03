package com.smitsworks.redlo.hottours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by redlongcity on 03.12.2017.
 * class for communication with User
 */

public class Dialog extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog,container);
        v.findViewById(R.id.dialog_button).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
