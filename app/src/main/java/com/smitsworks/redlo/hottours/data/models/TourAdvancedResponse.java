package com.smitsworks.redlo.hottours.data.models;

import java.util.List;

/**
 * Created by redlongcity on 10.03.2018.
 */

public class TourAdvancedResponse {

    private Long comeBackDelay;

    private List<TourAdvanced> tourList;

    private Object request;

    public TourAdvancedResponse() {
    }

    public Long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(Long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public List<TourAdvanced> getTourList() {
        return tourList;
    }

    public void setTourList(List<TourAdvanced> tourList) {
        this.tourList = tourList;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourAdvancedResponse that = (TourAdvancedResponse) o;

        return request != null ? request.equals(that.request) : that.request == null;
    }

    @Override
    public int hashCode() {
        return request != null ? request.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourAdvancedResponse{" +
                "comeBackDelay=" + comeBackDelay +
                ", tourList=" + tourList +
                ", request=" + request +
                '}';
    }
}
