package com.smitsworks.redlo.hottours.tourorder;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Order;
import com.smitsworks.redlo.hottours.data.models.Tour;
import com.smitsworks.redlo.hottours.data.models.UserData;
import com.smitsworks.redlo.hottours.data.source.datasource.OrderDataPoster;
import com.smitsworks.redlo.hottours.data.source.datasource.ToursDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.OrderRepository;
import com.smitsworks.redlo.hottours.data.source.repositories.ToursRepository;

/**
 * Created by redlongcity on 14.10.2017.
 * class listeners to user actions from the UI, retrieves the data and update the UI as required
 */

public class TourOrderPresenter implements TourOrderContract.Presenter {

    private final OrderRepository orderRepository;

    private final TourOrderContract.View tourOrderView;

    private String tourKey;

    public TourOrderPresenter(@NonNull OrderRepository orderRepository,
                              @NonNull TourOrderContract.View tourOrderView,
                              @NonNull String tourKey) {
        this.orderRepository = orderRepository;
        this.tourOrderView = tourOrderView;
        this.tourKey = tourKey;

        tourOrderView.setPresenter(this);
    }

    @Override
    public void start() {
        populateData();
    }

    @Override
    public void createOrder(String name, String phoneNumber, String email, String city) {
        final Order order = new Order();
        UserData data = new UserData();
        data.setName(name);
        data.setEmail(email);
        data.setCity(city);
        data.setMobileNumber(phoneNumber);
        order.setData(data);
        order.setKey(tourKey);

        if (order.isEmpty()) {
            tourOrderView.showEmptyDataError();
        } else {
            orderRepository.postOrder(order, new OrderDataPoster.PostOrderCallback() {
                @Override
                public void onOrderPosted() {
                    if (!tourOrderView.isActive()) {
                        return;
                    }
                    tourOrderView.showSuccessfullPosting();
                    tourOrderView.showToursList();
                }

                @Override
                public void onPostFailed() {
                    if (!tourOrderView.isActive()) {
                        return;
                    }
                    tourOrderView.showFailedPosting();
                }

                @Override
                public void onNotAvailableConnection() {
                    if (!tourOrderView.isActive()) {
                        return;
                    }
                    tourOrderView.showNotAvailableConnection();
                }
            });
        }
    }

    @Override
    public void populateData() {
        if (orderRepository == null) {
            return;
        }

        String name = orderRepository.getCachedName();
        if (name != null) {
            tourOrderView.setName(name);
        }

        String phone = orderRepository.getCachedPhone();
        if (phone != null) {
            tourOrderView.setMobileNumber(phone);
        }

        String email = orderRepository.getCachedEmail();
        if (email != null) {
            tourOrderView.setEmail(email);
        }

        String city = orderRepository.getCachedCity();
        if (city != null) {
            tourOrderView.setCity(city);
        }
    }

    @Override
    public void cachedName(String name) {
        if(orderRepository==null || name==null){
            return;
        }
        orderRepository.cacheName(name);
    }

    @Override
    public void cachedEmail(String email) {
        if (orderRepository==null || email == null) {
            return;
        }
        orderRepository.cacheEmail(email);
    }

    @Override
    public void cachedCity(String city) {
        if (orderRepository==null || city == null) {
            return;
        }
        orderRepository.cacheCity(city);
    }

    @Override
    public void cachedPhone(String phone) {
        if (orderRepository==null || phone == null) {
            return;
        }
        orderRepository.cachePhone(phone);
    }

}
