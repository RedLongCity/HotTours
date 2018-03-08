package com.smitsworks.redlo.hottours.data.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class TourCasual {

    private String key;

    private Meal_Type mealType;

    private String roomType;

    private Integer duration;

    private Date dateFrom;

    private Boolean combined;

    private Currency currency;

    private Set<Price> prices = new HashSet<>();

    private From_Cities city;

    private String transportType;

    private Hotel hotel;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Boolean getCombined() {
        return combined;
    }

    public void setCombined(Boolean combined) {
        this.combined = combined;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public From_Cities getCity() {
        return city;
    }

    public void setCity(From_Cities city) {
        this.city = city;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourCasual that = (TourCasual) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourCasual{" +
                "key='" + key + '\'' +
                ", mealType=" + mealType +
                ", roomType='" + roomType + '\'' +
                ", duration=" + duration +
                ", dateFrom=" + dateFrom +
                ", combined=" + combined +
                ", currency=" + currency +
                ", prices=" + prices +
                ", city=" + city +
                ", transportType='" + transportType + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}
