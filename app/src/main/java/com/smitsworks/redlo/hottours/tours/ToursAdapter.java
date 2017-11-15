package com.smitsworks.redlo.hottours.tours;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Hotel_Image;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 06.10.2017.
 * adapter for creating list items
 */

public class ToursAdapter extends BaseAdapter {

    private List<Tour> tourList;
    private ToursFragment.ToursItemListener itemListener;
    private TourCurrencyType currencyType;
    private String currencyId;

    public ToursAdapter(List<Tour> tourList,
                        ToursFragment.ToursItemListener itemListener,
                        TourCurrencyType requestType) {
        setTourList(tourList);
        this.itemListener = itemListener;
        setCurrencyType(requestType);
    }

    public void replaceData(List<Tour> tours){
        setTourList(tours);
        notifyDataSetChanged();
    }

    private void setTourList(List<Tour> tourList) {
        this.tourList = checkNotNull(tourList);
    }

    public TourCurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(TourCurrencyType currencyType) {
        this.currencyType = checkNotNull(currencyType);
    }

    @Override
    public int getCount() {
        return tourList.size();
    }

    @Override
    public Tour getItem(int position) {
        return tourList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.tour_item,parent,false);
        }

        final Tour tour = getItem(position);

        final ConstraintLayout mainLayout = (ConstraintLayout) rowView.findViewById(R.id.tours_item_main);
        TextView countryCity = (TextView) rowView.findViewById(R.id.country_city);
        TextView price = (TextView) rowView.findViewById(R.id.price);
        TextView hotelName = (TextView) rowView.findViewById(R.id.hotel_name);
        TextView currency = (TextView) rowView.findViewById(R.id.currency);
        TextView dateFrom = (TextView) rowView.findViewById(R.id.date_from);
        ImageView star_1=(ImageView) rowView.findViewById(R.id.star_1);
        ImageView star_2=(ImageView) rowView.findViewById(R.id.star_2);
        ImageView star_3=(ImageView) rowView.findViewById(R.id.star_3);
        ImageView star_4=(ImageView) rowView.findViewById(R.id.star_4);
        ImageView star_5=(ImageView) rowView.findViewById(R.id.star_5);

        HashSet<Hotel_Image> hotelImageSet = (HashSet<Hotel_Image>) tour.getHotel_ImageSet();
        if (hotelImageSet != null&&!hotelImageSet.isEmpty()) {
            Hotel_Image hotelImage=null;
            for(Hotel_Image image:hotelImageSet){
                hotelImage=image;
            }
            if (hotelImage != null) {
                Picasso.with(parent.getContext()).
                        load(hotelImage.getFull()).
                        placeholder(R.drawable.tour_placeholder).
                        into(new Target() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                mainLayout.setBackground(new BitmapDrawable(
                                        parent.getContext().getResources(),bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {
                                Log.d("TAG","Loading picture finished by fail");
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {
                            }
                        });
            }
        }else{
            Picasso.with(parent.getContext()).load(R.drawable.tour_placeholder).into(new Target() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    mainLayout.setBackground(new BitmapDrawable(
                            parent.getContext().getResources(),bitmap));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Log.d("TAG","Loading picture finished by fail");
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }

        countryCity.setText(tour.getCountry().getName()+", "+tour.getRegion());

        switch(getCurrencyType()){
            case EURO:
                currencyId="10";
                currency.setText("€");
                break;
            case DOLLAR:
                currencyId="1";
                currency.setText("$");
                break;
            case HRYVNA:
                currencyId="2";
                currency.setText("грн");
                break;
        }

        HashSet<Price> pricesSet = (HashSet<Price>) tour.getPrices();
        Iterator<Price> iterator = pricesSet.iterator();
        while(iterator.hasNext()){
            Price priceIt = iterator.next();
            if(priceIt.getCurrency().getId().equals(currencyId)){
                price.setText(priceIt.getCost().toString());
            }
        }

        hotelName.setText(tour.getHotel());
        dateFrom.setText(tour.getDate_From().toString());

        switch(tour.getHotel_Rating().getId()){
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
            case "2":
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

        return rowView;
    }
}
