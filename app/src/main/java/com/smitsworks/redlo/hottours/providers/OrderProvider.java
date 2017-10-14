package com.smitsworks.redlo.hottours.providers;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.utils.HttpUtils;
import com.smitsworks.redlo.hottours.utils.Urls;

import okhttp3.Response;

/**
 * Created by redlongcity on 14.10.2017.
 * posts order to server
 */

public class OrderProvider implements Provider,Urls {
    @Override
    public Response provide() {
        return null;
    }

    public Response provide(Order order){
        return HttpUtils.postData(ORDER_URL,order);
    }
}
