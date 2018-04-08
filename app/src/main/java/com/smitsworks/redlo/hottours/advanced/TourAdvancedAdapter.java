package com.smitsworks.redlo.hottours.advanced;

import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Hotel_Image;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.TourAdvanced;
import com.smitsworks.redlo.hottours.tours.TourCurrencyType;
import com.smitsworks.redlo.hottours.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedAdapter extends BaseAdapter {

    private List<TourAdvanced> list;

    private TourAdvancedFragment.TourAdvancedItemListener itemListener;

    private TourCurrencyType currencyType;

    private String currencyId;

    public TourAdvancedAdapter(List<TourAdvanced> list,
                               TourAdvancedFragment.TourAdvancedItemListener itemListener,
                               TourCurrencyType currencyType) {
        this.list = list;
        this.itemListener = itemListener;
        this.currencyType = currencyType;
    }

    public void replaceData(List<TourAdvanced> tours) {
        setList(tours);
        notifyDataSetChanged();
    }

    public void setList(List<TourAdvanced> list) {
        if (list != null) {
            this.list = list;
        }
    }

    public TourCurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(TourCurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public TourAdvanced getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            result = inflater.inflate(R.layout.tour_item, parent, false);
        }

        final TourAdvanced tour = getItem(position);

        final ConstraintLayout mainLayout = (ConstraintLayout) result.findViewById(R.id.tours_item_main);
        TextView countryCity = (TextView) result.findViewById(R.id.country_city);
        TextView price = (TextView) result.findViewById(R.id.price);
        TextView hotelName = (TextView) result.findViewById(R.id.hotel_name);
        TextView currency = (TextView) result.findViewById(R.id.currency);
        TextView dateFrom = (TextView) result.findViewById(R.id.date_from);
        TextView duration = (TextView) result.findViewById(R.id.duration);
        final ImageView imageView = (ImageView) result.findViewById(R.id.tours_item_image);
        ImageView star_1 = (ImageView) result.findViewById(R.id.star_1);
        ImageView star_2 = (ImageView) result.findViewById(R.id.star_2);
        ImageView star_3 = (ImageView) result.findViewById(R.id.star_3);
        ImageView star_4 = (ImageView) result.findViewById(R.id.star_4);
        ImageView star_5 = (ImageView) result.findViewById(R.id.star_5);

        if (tour.getImages() != null && !tour.getImages().isEmpty()) {
            for (Hotel_Image image : tour.getImages()) {
                Picasso.with(parent.getContext()).
                        load(image.getFull()).
                        placeholder(R.drawable.tour_placeholder).
                        into(imageView);
                break;
            }
        } else {
            Picasso.with(parent.getContext()).
                    load(R.drawable.tour_placeholder).
                    into(imageView);
        }

        countryCity.setText(tour.getCountry().getName() + ", " + tour.getRegion());

        switch (getCurrencyType()) {
            case EURO:
                currencyId = "10";
                currency.setText("€");
                break;
            case DOLLAR:
                currencyId = "1";
                currency.setText("$");
                break;
            case HRYVNA:
                currencyId = "2";
                currency.setText("грн");
                break;
        }

        HashSet<Price> pricesSet = (HashSet<Price>) tour.getPrices();
        if (pricesSet != null && !pricesSet.isEmpty()) {
            Iterator<Price> iterator = pricesSet.iterator();
            while (iterator.hasNext()) {
                Price priceIt = iterator.next();
                if (priceIt.getCurrency().getId().equals(currencyId)) {
                    price.setText(priceIt.getCost().toString());
                }
            }
        }

        hotelName.setText(tour.getHotelName());
        dateFrom.setText(DateUtils.formatDate(tour.getDateFrom()));

        if (tour.getDuration() != null) {
            duration.setText(tour.getDuration().toString());
        }

        switch (tour.getRating().getId()) {
            case "78":
                star_5.setVisibility(View.VISIBLE);
                star_4.setVisibility(View.VISIBLE);
                star_3.setVisibility(View.VISIBLE);
                star_2.setVisibility(View.VISIBLE);
                star_1.setVisibility(View.VISIBLE);
                break;
            case "4":
                star_5.setVisibility(View.GONE);
                star_4.setVisibility(View.VISIBLE);
                star_3.setVisibility(View.VISIBLE);
                star_2.setVisibility(View.VISIBLE);
                star_1.setVisibility(View.VISIBLE);
                break;
            case "3":
                star_5.setVisibility(View.GONE);
                star_4.setVisibility(View.GONE);
                star_3.setVisibility(View.VISIBLE);
                star_2.setVisibility(View.VISIBLE);
                star_1.setVisibility(View.VISIBLE);
                break;
            case "7":
                star_5.setVisibility(View.GONE);
                star_4.setVisibility(View.GONE);
                star_3.setVisibility(View.GONE);
                star_2.setVisibility(View.VISIBLE);
                star_1.setVisibility(View.VISIBLE);
                break;
            case "1":
                star_5.setVisibility(View.GONE);
                star_4.setVisibility(View.GONE);
                star_3.setVisibility(View.GONE);
                star_2.setVisibility(View.GONE);
                star_1.setVisibility(View.VISIBLE);
                break;
        }

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onTourClick(getItem(position));
            }
        });

        return result;
    }
}
