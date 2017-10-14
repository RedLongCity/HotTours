package com.smitsworks.redlo.hottours.tourorder;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.models.UserData;
import com.smitsworks.redlo.hottours.data.source.OrderDataPoster;
import com.smitsworks.redlo.hottours.data.source.OrderRepository;
import com.smitsworks.redlo.hottours.tourorder.TourOrderContract.Presenter;

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
        Order order = new Order(new UserData(name,phoneNumber,email,city),tourId);
        if(order.isEmpty()){
            tourOrderView.showEmptyDataError();
        }else{
            orderRepository.postOrder(order, new OrderDataPoster.PostOrderCallback() {
                @Override
                public void onOrderPosted() {
                    tourOrderView.showSuccessfullPosting();
                    tourOrderView.showToursList();
                }

                @Override
                public void onPostFailed() {
                    tourOrderView.showFailedPosting();
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
