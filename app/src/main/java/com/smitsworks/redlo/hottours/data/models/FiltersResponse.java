package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 15/09/2017
 * class for incapsulating ItTours Hot Filters response to client
 */
public class FiltersResponse {
    
    private Long delay;
    
    private List<Country> countriesList;
    
    private List<From_Cities> from_CititesList;

    private List<Hotel_Rating> hotel_RatingList;

    private List<Meal_Type> meal_TypeList;

    private List<Currency> currencyList;

    public List<Country> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(List<Country> countriesList) {
        this.countriesList = countriesList;
    }

    public List<From_Cities> getFrom_CititesList() {
        return from_CititesList;
    }

    public void setFrom_CititesList(List<From_Cities> from_CititesList) {
        this.from_CititesList = from_CititesList;
    }

    public List<Hotel_Rating> getHotel_RatingList() {
        return hotel_RatingList;
    }

    public void setHotel_RatingList(List<Hotel_Rating> hotel_RatingList) {
        this.hotel_RatingList = hotel_RatingList;
    }

    public List<Meal_Type> getMeal_TypeList() {
        return meal_TypeList;
    }

    public void setMeal_TypeList(List<Meal_Type> meal_TypeList) {
        this.meal_TypeList = meal_TypeList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiltersResponse that = (FiltersResponse) o;

        if (delay != null ? !delay.equals(that.delay) : that.delay != null) return false;
        if (countriesList != null ? !countriesList.equals(that.countriesList) : that.countriesList != null)
            return false;
        if (from_CititesList != null ? !from_CititesList.equals(that.from_CititesList) : that.from_CititesList != null)
            return false;
        if (hotel_RatingList != null ? !hotel_RatingList.equals(that.hotel_RatingList) : that.hotel_RatingList != null)
            return false;
        if (meal_TypeList != null ? !meal_TypeList.equals(that.meal_TypeList) : that.meal_TypeList != null)
            return false;
        return currencyList != null ? currencyList.equals(that.currencyList) : that.currencyList == null;
    }

    @Override
    public int hashCode() {
        int result = delay != null ? delay.hashCode() : 0;
        result = 31 * result + (countriesList != null ? countriesList.hashCode() : 0);
        result = 31 * result + (from_CititesList != null ? from_CititesList.hashCode() : 0);
        result = 31 * result + (hotel_RatingList != null ? hotel_RatingList.hashCode() : 0);
        result = 31 * result + (meal_TypeList != null ? meal_TypeList.hashCode() : 0);
        result = 31 * result + (currencyList != null ? currencyList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FiltersResponse{" + "delay=" + delay + ", countriesList=" + countriesList + ", from_CititesList=" + from_CititesList + ", hotel_RatingList=" + hotel_RatingList + ", meal_TypeList=" + meal_TypeList + ", currencyList=" + currencyList + '}';
    }
    
}
