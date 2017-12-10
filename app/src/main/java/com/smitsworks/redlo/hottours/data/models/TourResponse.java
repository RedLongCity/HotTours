package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for incapsulating ItTours Hot Search response to client
 */
public class TourResponse {
    
    
    private Long comeBackDelay;

    private List<Tour> tourList;

    private Request request;

    public TourResponse() {
    }

    public TourResponse(Long comeBackDelay, List<Tour> tourList) {
        this.comeBackDelay = comeBackDelay;
        this.tourList = tourList;
    }

    public Long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(Long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourResponse that = (TourResponse) o;

        if (comeBackDelay != null ? !comeBackDelay.equals(that.comeBackDelay) : that.comeBackDelay != null)
            return false;
        if (tourList != null ? !tourList.equals(that.tourList) : that.tourList != null)
            return false;
        return request != null ? request.equals(that.request) : that.request == null;
    }

    @Override
    public int hashCode() {
        int result = comeBackDelay != null ? comeBackDelay.hashCode() : 0;
        result = 31 * result + (tourList != null ? tourList.hashCode() : 0);
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TourResponse{" + "comeBackDelay=" + comeBackDelay + ", tourList=" + tourList + ", request=" + request + '}';
    }
    
    
    
}
