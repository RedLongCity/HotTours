package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
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

public class Request implements Parcelable {

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

    public Request() {
    }

    public Request(Parcel in){
        String[] data = new String[4];
        in.readStringArray(data);
        country = new Country();
        country.setId(data[0]);
        from_Cities = new From_Cities();
        from_Cities.setId(data[1]);
        hotel_Rating = data[2];
        meal_Type = new Meal_Type();
        meal_Type.setId(data[3]);

        int[] intData = new int[2];
        in.readIntArray(intData);
        night_From = intData[0];
        night_Till = intData[1];
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                country!=null?country.getId():"null",
                from_Cities!=null?from_Cities.getId():"null",
                hotel_Rating!=null?hotel_Rating:"3:78",
                meal_Type!=null?meal_Type.getId():"null"});

        dest.writeIntArray(new int[]{
                night_From!=null?night_From:2,
                night_Till!=null?night_Till:7,});
    }

    public static final Parcelable.Creator<Request> CREATOR =
            new Parcelable.Creator<Request>(){

                @Override
                public Request createFromParcel(Parcel source) {
                    return new Request(source);
                }

                @Override
                public Request[] newArray(int size) {
                    return new Request[size];
                }
            };

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
