package com.smitsworks.redlo.hottours.lists.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Hotel_Rating;
import com.smitsworks.redlo.hottours.lists.hotelratings.HotelRatingsFragment;

import java.util.List;

/**
 * Created by redlongcity on 21.10.2017.
 * adapter for convertion Hotel_Rating object ot list element
 */

public class HotelRatingsAdapter extends BaseAdapter {

    private List<Hotel_Rating> ratings;
    private HotelRatingsFragment.HotelRatingsItemListener listener;

    public HotelRatingsAdapter(List<Hotel_Rating> ratings,
                               HotelRatingsFragment.HotelRatingsItemListener listener) {
        this.ratings = ratings;
        this.listener = listener;
    }

    public void replaceData(List<Hotel_Rating> ratings){
        setRatings(ratings);
        notifyDataSetChanged();
    }

    public void setRatings(List<Hotel_Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int position) {
        return ratings.get(position);
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

            final Hotel_Rating rating = ratings.get(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(rating.getName());
        if(position%2==0) {
            textView.setBackgroundColor(Color.LTGRAY);
        }else{
            textView.setBackgroundColor(Color.WHITE);
        }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHotelRatingClick(rating);
                }
            });
            return rowView;

    }
}
