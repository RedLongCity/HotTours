package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

/**
 *
 * @author redlongcity
 * model for storaging prices
 */

public class Price {
    
    private Integer id;

    private Currency currency;
    
    private Integer cost;
    
    private Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (id != null ? !id.equals(price.id) : price.id != null) return false;
        if (currency != null ? !currency.equals(price.currency) : price.currency != null)
            return false;
        return cost != null ? cost.equals(price.cost) : price.cost == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + id + ", currency=" + currency + ", cost=" + cost + ", tour=" + tour + '}';
    }

}
