package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

/**
 *
 * @author redlongcity
 * model like storage for hotel images
 */

public class Hotel_Image {
    
    private Integer id;
    
    private String full;
    
    private String thumb;
    
    private Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel_Image that = (Hotel_Image) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hotel_Image{" + "id=" + id + ", full=" + full + ", thumb=" + thumb + ", tour=" + tour + '}';
    }
    
    
}
