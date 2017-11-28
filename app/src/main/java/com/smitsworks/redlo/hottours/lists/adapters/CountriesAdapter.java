package com.smitsworks.redlo.hottours.lists.adapters;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Country;
import com.smitsworks.redlo.hottours.lists.countries.CountriesFragment;

import java.util.List;

/**
 * Created by redlongcity on 19.10.2017.
 * adapter for creating list from Countries
 */

public class CountriesAdapter extends BaseAdapter {

    private List<Country> countries;
    private CountriesFragment.CountriesItemListener listener;

    public CountriesAdapter(List<Country> countries,
                            CountriesFragment.CountriesItemListener listener) {
        this.countries = countries;
        this.listener = listener;
    }

    public void replaceData(List<Country> countries){
        setCountries(countries);
        notifyDataSetChanged();
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

            final Country country = (Country) getItem(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(country.getName());

            if(position%2==0) {
                textView.setBackgroundColor(Color.LTGRAY);
            }else{
                textView.setBackgroundColor(Color.WHITE);
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCountryClick(country);
                }
            });
            return rowView;

    }
}
