package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by redlongcity on 02.10.2017.
 * Country model
 */

public class Country {

    private String id;

    private String name;

    private Set<From_Cities> from_CitiesSet = new HashSet<From_Cities>();

    private Set<Tour> tours = new HashSet<Tour>();

    private Set<Request> requests = new HashSet<Request>();

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

    public Set<From_Cities> getFrom_CitiesSet() {
        return from_CitiesSet;
    }

    public void setFrom_CitiesSet(Set<From_Cities> from_CitiesSet) {
        this.from_CitiesSet = from_CitiesSet;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return id != null ? id.equals(country.id) : country.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", from_CitiesSet=" + from_CitiesSet + ", tours=" + tours + ", requests=" + requests + '}';
    }


}
