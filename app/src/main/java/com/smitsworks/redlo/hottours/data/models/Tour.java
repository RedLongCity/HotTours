package com.smitsworks.redlo.hottours.data.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 *
 * @author redlongcity
 */
public class Tour {

    private String key;

    private Integer type;

    private Country country;

    private String region;

    private Integer hotel_id;

    private String hotel;

    private Hotel_Rating hotel_Rating;

    private Meal_Type meal_Type;

    private String room_Type;

    private Integer adult_Amount;

    private Integer child_Amount;

    private String accomodation;

    private Integer duration;

    private Date date_From;

    private Integer date_From_Unix;

    private Integer currency_id;

    private String currency_Symbol;

    private Set<Price> prices = new HashSet<Price>();

    private Integer price_Old;

    private Float price_Change_Percent;

    private From_Cities from_Cities;

    private String from_City_Gen;

    private String transport_Type;

    private Set<Hotel_Image> hotel_ImageSet = new HashSet<Hotel_Image>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRoom_Type() {
        return room_Type;
    }

    public void setRoom_Type(String room_Type) {
        this.room_Type = room_Type;
    }

    public Integer getAdult_Amount() {
        return adult_Amount;
    }

    public void setAdult_Amount(Integer adult_Amount) {
        this.adult_Amount = adult_Amount;
    }

    public Integer getChild_Amount() {
        return child_Amount;
    }

    public void setChild_Amount(Integer child_Amount) {
        this.child_Amount = child_Amount;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate_From() {
        return date_From;
    }

    public void setDate_From(Date date_From) {
        this.date_From = date_From;
    }

    public Integer getDate_From_Unix() {
        return date_From_Unix;
    }

    public void setDate_From_Unix(Integer date_From_Unix) {
        this.date_From_Unix = date_From_Unix;
    }

    public Integer getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Integer currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_Symbol() {
        return currency_Symbol;
    }

    public void setCurrency_Symbol(String currency_Symbol) {
        this.currency_Symbol = currency_Symbol;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public Integer getPrice_Old() {
        return price_Old;
    }

    public void setPrice_Old(Integer price_Old) {
        this.price_Old = price_Old;
    }

    public Float getPrice_Change_Percent() {
        return price_Change_Percent;
    }

    public void setPrice_Change_Percent(Float price_Change_Percent) {
        this.price_Change_Percent = price_Change_Percent;
    }

    public String getFrom_City_Gen() {
        return from_City_Gen;
    }

    public void setFrom_City_Gen(String from_City_Gen) {
        this.from_City_Gen = from_City_Gen;
    }

    public String getTransport_Type() {
        return transport_Type;
    }

    public void setTransport_Type(String transport_Type) {
        this.transport_Type = transport_Type;
    }

    public Set<Hotel_Image> getHotel_ImageSet() {
        return hotel_ImageSet;
    }

    public void setHotel_ImageSet(Set<Hotel_Image> hotel_ImageSet) {
        this.hotel_ImageSet = hotel_ImageSet;
    }

    public Hotel_Rating getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(Hotel_Rating hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    public Meal_Type getMeal_Type() {
        return meal_Type;
    }

    public void setMeal_Type(Meal_Type meal_Type) {
        this.meal_Type = meal_Type;
    }

    public From_Cities getFrom_Cities() {
        return from_Cities;
    }

    public void setFrom_Cities(From_Cities from_Cities) {
        this.from_Cities = from_Cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        return key != null ? key.equals(tour.key) : tour.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "key='" + key + '\'' +
                ", type=" + type +
                ", country=" + country +
                ", region='" + region + '\'' +
                ", hotel_id=" + hotel_id +
                ", hotel='" + hotel + '\'' +
                ", hotel_Rating=" + hotel_Rating +
                ", meal_Type=" + meal_Type +
                ", room_Type='" + room_Type + '\'' +
                ", adult_Amount=" + adult_Amount +
                ", child_Amount=" + child_Amount +
                ", accomodation='" + accomodation + '\'' +
                ", duration=" + duration +
                ", date_From=" + date_From +
                ", date_From_Unix=" + date_From_Unix +
                ", currency_id=" + currency_id +
                ", currency_Symbol='" + currency_Symbol + '\'' +
                ", prices=" + prices +
                ", price_Old=" + price_Old +
                ", price_Change_Percent=" + price_Change_Percent +
                ", from_Cities=" + from_Cities +
                ", from_City_Gen='" + from_City_Gen + '\'' +
                ", transport_Type='" + transport_Type + '\'' +
                ", hotel_ImageSet=" + hotel_ImageSet +
                '}';
    }

}
