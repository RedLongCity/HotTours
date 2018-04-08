package com.smitsworks.redlo.hottours.tourdetails.convertor;

import com.smitsworks.redlo.hottours.tourdetails.TourDetailItem;

import java.util.List;

/**
 * Created by redlongcity on 08.04.2018.
 */

public interface Convertor {

    List<TourDetailItem> convert(Object object);

    void addItem(List<TourDetailItem> list, TourDetailItem item);

}
