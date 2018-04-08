package com.smitsworks.redlo.hottours.tourdetails.convertor;

import android.content.Context;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Price;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.tourdetails.TourDetailItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by redlongcity on 08.04.2018.
 */

public class HotToursConvertor extends AbstractConvertor implements Convertor {

    private static HotToursConvertor instance;

    private String currencyId;

    public static HotToursConvertor getInstance(Context context, String currencyId) {
        if (instance == null) {
            instance = new HotToursConvertor(context, currencyId);
        }
        return instance;
    }

    private HotToursConvertor(Context context, String currencyId) {
        this.currencyId = currencyId;
        initContext(context);
    }

    @Override
    public List<TourDetailItem> convert(Object object) {
        List<TourDetailItem> result = null;
        if (object != null && object instanceof Tour) {
            result = new LinkedList<>();

            addItem(result, createItem(getString(R.string.from_city),
                    ((Tour) object).getFrom_Cities().getName()));

            addItem(result, createItem(getString(R.string.hotel_name),
                    ((Tour) object).getHotel()));

            addItem(result, createItem(getString(R.string.hotel_rating),
                    ((Tour) object).getHotel_Rating().getName()));

            addItem(result, createItem(getString(R.string.meal_type),
                    ((Tour) object).getMeal_Type().getName_full()));

            addItem(result, createItem(getString(R.string.adult_amount),
                    ((Tour) object).getAdult_Amount()));

            addItem(result, createItem(getString(R.string.children_amount),
                    ((Tour) object).getChild_Amount()));

            addItem(result, createItem(getString(R.string.duration),
                    ((Tour) object).getDuration()));

            addItem(result, createItem(getString(R.string.date_from),
                    ((Tour) object).getDate_From()));

            if (((Tour) object).getPrices() != null) {
                for (Price price : ((Tour) object).getPrices()) {
                    if (price.getCurrency().getId().equals(currencyId)) {
                        addItem(result, createItem(getString(R.string.price_value),
                                price.getCost()));
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void addItem(List<TourDetailItem> list, TourDetailItem item) {
        if (list != null && item != null) {
            list.add(item);
        }
    }


}
