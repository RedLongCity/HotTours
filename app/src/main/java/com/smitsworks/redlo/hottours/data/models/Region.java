package com.smitsworks.redlo.hottours.data.models;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class Region {

    private String Id;

    private String name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return Id != null ? Id.equals(region.Id) : region.Id == null;
    }

    @Override
    public int hashCode() {
        return Id != null ? Id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Region{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
