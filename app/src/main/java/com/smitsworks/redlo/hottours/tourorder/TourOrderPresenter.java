package com.smitsworks.redlo.hottours.tourorder;

import android.support.annotation.NonNull;
import android.util.Log;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.models.UserData;
import com.smitsworks.redlo.hottours.data.source.OrderDataPoster;
import com.smitsworks.redlo.hottours.data.source.OrderRepository;

/**
 * Created by redlongcity on 14.10.2017.
 * class listeners to user actions from the UI, retrieves the data and update the UI as required
 */

public class TourOrderPresenter implements TourOrderContract.Presenter {

    private final OrderRepository orderRepository;

    private final TourOrderContract.View tourOrderView;

    private Integer tourId;

    private boolean isDataMissing;

    public TourOrderPresenter(@NonNull OrderRepository orderRepository,
                              @NonNull TourOrderContract.View tourOrderView,
                              @NonNull Integer tourId) {
        this.orderRepository = orderRepository;
        this.tourOrderView = tourOrderView;
        this.tourId = tourId;

        tourOrderView.setPresenter(this);
    }

    @Override
    public void start() {
        if(isDataMissing){
            populateData();
        }
    }

    @Override
    public void createOrder(String name, String phoneNumber, String email, String city) {
        Order order = new Order();
        UserData data = new UserData();
        data.setName(name);
        data.setEmail(email);
        data.setCity(city);
        data.setMobileNumber(phoneNumber);
        order.setData(data);
        order.setTourId(tourId);
        if(order.isEmpty()){
            tourOrderView.showEmptyDataError();
        }else{
            orderRepository.postOrder(order, new OrderDataPoster.PostOrderCallback() {
                @Override
                public void onOrderPosted() {
                    if(!tourOrderView.isActive()){
                        return;
                    }
                    tourOrderView.showSuccessfullPosting();
                    tourOrderView.showToursList();
                }

                @Override
                public void onPostFailed() {
                    if(!tourOrderView.isActive()){
                        return;
                    }
                    tourOrderView.showFailedPosting();
                }

                @Override
                public void onNotAvailableConnection() {
                    if(!tourOrderView.isActive()){
                        return;
                    }
                    tourOrderView.showNotAvailableConnection();
                }
            });
        }
    }

    @Override
    public boolean isDataMissing() {
        return isDataMissing;
    }

    @Override
    public void populateData() {

    }
}
