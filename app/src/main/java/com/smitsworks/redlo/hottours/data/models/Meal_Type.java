package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 *
 * @author redlongcity
 * model of meal_type storage
 */
public class Meal_Type {
    
    private String id;
    
    private String name;
    
    private String name_full;
    
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

    public String getName_full() {
        return name_full;
    }

    public void setName_full(String name_full) {
        this.name_full = name_full;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal_Type meal_type = (Meal_Type) o;

        return id != null ? id.equals(meal_type.id) : meal_type.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Meal_Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", name_full='" + name_full + '\'' +
                '}';
    }
}
