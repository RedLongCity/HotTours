package com.smitsworks.redlo.hottours.lists.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.From_Cities;
import com.smitsworks.redlo.hottours.lists.cities.CitiesFragment;

import java.util.List;

/**
 * Created by redlongcity on 20.10.2017.
 * adapter for creating list of cities
 */

public class CitiesAdapter extends BaseAdapter {

    private List<From_Cities> cities;
    private CitiesFragment.CitiesItemListener listener;

    public CitiesAdapter(List<From_Cities> cities,
                         CitiesFragment.CitiesItemListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    public void replaceData(List<From_Cities> cities){
        setCities(cities);
        notifyDataSetChanged();
    }

    public void setCities(List<From_Cities> cities) {
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
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
            final From_Cities city = (From_Cities) getItem(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(city.getName());
        if(position%2==0) {
            textView.setBackgroundColor(Color.LTGRAY);
        }else{
            textView.setBackgroundColor(Color.WHITE);
        }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCityClick(city);
                }
            });
            return rowView;

    }
}
