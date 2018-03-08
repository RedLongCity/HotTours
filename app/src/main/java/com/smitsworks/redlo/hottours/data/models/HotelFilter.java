package com.smitsworks.redlo.hottours.data.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class HotelFilter {

    private String id;

    private String name;

    private Hotel_Rating rating;

    private Region region;

    private Set<Type> typeSet = new HashSet<>();

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

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Type> getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(Set<Type> typeSet) {
        this.typeSet = typeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelFilter that = (HotelFilter) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HotelFilter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", region=" + region +
                ", typeSet=" + typeSet +
                '}';
    }
}
