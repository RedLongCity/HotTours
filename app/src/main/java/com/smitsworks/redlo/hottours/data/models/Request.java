package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
/**
 *
 * @author redlongcity
 * model for storaging requests
 */

public class Request {

    private static final Logger LOG = Logger.getLogger(Request.class.getName());
    
    private Integer id;
    
    private Country country;
    
    private From_Cities from_Cities;
    
    private String hotel_Rating;
    
    private Integer night_From;
    
    private Integer night_Till;
    
    private Meal_Type meal_Type;
    
    private Set<Tour> tourSet = new HashSet<Tour>();
    
    private Long requestDelay;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public From_Cities getFrom_Cities() {
        return from_Cities;
    }

    public void setFrom_Cities(From_Cities from_Cities) {
        this.from_Cities = from_Cities;
    }

    public String getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(String hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    public Integer getNight_From() {
        return night_From;
    }

    public void setNight_From(Integer night_From) {
        this.night_From = night_From;
    }

    public Integer getNight_Till() {
        return night_Till;
    }

    public void setNight_Till(Integer night_Till) {
        this.night_Till = night_Till;
    }

    public Meal_Type getMeal_Type() {
        return meal_Type;
    }

    public void setMeal_Type(Meal_Type meal_Type) {
        this.meal_Type = meal_Type;
    }

    public Set<Tour> getTourSet() {
        return tourSet;
    }

    public void setTourSet(Set<Tour> tourSet) {
        this.tourSet = tourSet;
    }

    public Long getRequestDelay() {
        return requestDelay;
    }

    public void setRequestDelay(Long requestDelay) {
        this.requestDelay = requestDelay;
    }
    
    
    
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Request other = (Request) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", country=" + country +
                ", from_Cities=" + from_Cities +
                ", hotel_Rating='" + hotel_Rating + '\'' +
                ", night_From=" + night_From +
                ", night_Till=" + night_Till +
                ", meal_Type=" + meal_Type +
                ", tourSet=" + tourSet +
                ", requestDelay=" + requestDelay +
                '}';
    }
}
