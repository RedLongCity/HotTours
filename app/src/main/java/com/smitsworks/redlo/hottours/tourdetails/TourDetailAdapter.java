package com.smitsworks.redlo.hottours.tourdetails;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;

import java.util.List;

/**
 * Created by redlongcity on 08.04.2018.
 */

public class TourDetailAdapter extends BaseAdapter {

    private List<TourDetailItem> items;

    public TourDetailAdapter(List<TourDetailItem> items) {
        this.items = items;
    }

    public void replaceData(List<TourDetailItem> items) {
        if (items != null) {
            setItems(items);
            notifyDataSetChanged();
        }
    }

    public void setItems(List<TourDetailItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    @Override
    public TourDetailItem getItem(int position) {
        if (items != null) {
            return items.get(position);
        }
        return null;
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
            rowView = inflater.inflate(R.layout.tour_detail_item, parent, false);
        }
        final TourDetailItem item = getItem(position);
        if (item != null) {
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView value = (TextView) rowView.findViewById(R.id.value);
            LinearLayout main = (LinearLayout) rowView.findViewById(R.id.main);
            ImageView image = (ImageView) rowView.findViewById(R.id.image);

            if (item.getTitle() != null) {
                title.setText(item.getTitle());
            }

            if (value != null) {
                value.setText(item.getValue());
            }

            main.setBackgroundColor(ContextCompat.getColor(
                    parent.getContext(),
                    position % 2 == 0 ?
                            R.color.background_white :
                            R.color.background_grey
            ));

            image.setBackgroundColor(ContextCompat.getColor(
                    parent.getContext(),
                    position % 2 == 0 ?
                            R.color.background_grey :
                            R.color.background_white
            ));
        }

        return rowView;
    }
}
