package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 *
 * @author redlongcity
 */

public class From_Cities {
    
    private String id;
    
    private String name;
    
    private Set<Country> countrySet = new HashSet<Country>();
    
    private Set<Tour> tours = new HashSet<Tour>();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountrySet() {
        return countrySet;
    }

    public void setCountrySet(Set<Country> countrySet) {
        this.countrySet = countrySet;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        From_Cities that = (From_Cities) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "From_Cities{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", countrySet=" + countrySet +
                ", tours=" + tours +
                '}';
    }

}
