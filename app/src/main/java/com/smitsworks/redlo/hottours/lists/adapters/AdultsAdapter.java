package com.smitsworks.redlo.hottours.lists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.lists.adults.AdultsFragment;

/**
 * Created by redlongcity on 23.10.2017.
 * this adapter serve for creating list with amounts of something
 */

public class AdultsAdapter extends BaseAdapter {

    private int number;
    private AdultsFragment.AdultsItemListener listener;

    public AdultsAdapter(int count, AdultsFragment.AdultsItemListener listener) {
        this.number = count;
        this.listener = listener;
    }

    public void replaceData(int number){
        setNumber(number);
        notifyDataSetChanged();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getCount() {
        return number;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            inflater.inflate(R.layout.list_item,parent,false);

            final int value = (int) getItem(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(value);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(value);
                }
            });
        }

        return rowView;
    }
}
