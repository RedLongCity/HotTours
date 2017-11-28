package com.smitsworks.redlo.hottours.lists.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.lists.children.ChildrenFragment;

/**
 * Created by redlongcity on 23.10.2017.
 * this adapter create list of available children amounts
 */

public class ChildrenAdapter extends BaseAdapter {

    private int number;
    private ChildrenFragment.ChildrenItemListener listener;

    public ChildrenAdapter(int number, ChildrenFragment.ChildrenItemListener itemListener) {
        this.number = number;
        this.listener = itemListener;
    }

    public void replaceData(int number){
        setNumber(number);
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
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }
            final int value = (int) getItem(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(String.valueOf(value));
        if(position%2==0) {
            textView.setBackgroundColor(Color.LTGRAY);
        }else{
            textView.setBackgroundColor(Color.WHITE);
        }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(value);
                }
            });

        return rowView;
    }
}
