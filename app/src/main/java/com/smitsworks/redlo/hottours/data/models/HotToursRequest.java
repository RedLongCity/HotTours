package com.smitsworks.redlo.hottours.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;
import java.util.logging.Logger;

/**
 * Created by redlongcity on 01.01.2018.
 */

public class HotToursRequest implements Parcelable {

    private static final Logger LOG = Logger.getLogger(HotToursRequest.class.getName());

    private Integer id;

    private Country country;

    private From_Cities from_Cities;

    private String hotel_Rating;

    private Integer night_From;

    private Integer night_Till;

    private Meal_Type meal_Type;

    private Timestamp requestTime;

    public HotToursRequest() {
    }

    public HotToursRequest(Parcel in){
        String[] data = new String[4];
        in.readStringArray(data);
        if(!data[0].equals("null")){
            country = new Country();
            country.setId(data[0]);
        }
        if(!data[1].equals("null")) {
            from_Cities = new From_Cities();
            from_Cities.setId(data[1]);
        }
        hotel_Rating = data[2];
        if(!data[3].equals("null")) {
            meal_Type = new Meal_Type();
            meal_Type.setId(data[3]);
        }

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

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotToursRequest that = (HotToursRequest) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HotToursRequest{" +
                "id=" + id +
                ", country=" + country +
                ", from_Cities=" + from_Cities +
                ", hotel_Rating='" + hotel_Rating + '\'' +
                ", night_From=" + night_From +
                ", night_Till=" + night_Till +
                ", meal_Type=" + meal_Type +
                ", requestTime=" + requestTime +
                '}';
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

    public static final Parcelable.Creator<HotToursRequest> CREATOR =
            new Creator<HotToursRequest>() {
                @Override
                public HotToursRequest createFromParcel(Parcel source) {
                    return new HotToursRequest(source);
                }

                @Override
                public HotToursRequest[] newArray(int size) {
                    return new HotToursRequest[size];
                }
            };
}
