package com.smitsworks.redlo.hottours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by redlongcity on 03.12.2017.
 * class for communication with User
 */

public class Dialog extends DialogFragment implements View.OnClickListener {

    private static String messageToDisplay = null;

    public static Dialog newInstance(String message){
        Dialog dialog = new Dialog();
        messageToDisplay = message;
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog,container);
        v.findViewById(R.id.dialog_button).setOnClickListener(this);
        if (messageToDisplay != null) {
            TextView textView = (TextView) v.findViewById(R.id.dialog_textView);
            textView.setText(messageToDisplay);
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
