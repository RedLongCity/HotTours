package com.smitsworks.redlo.hottours.data.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class TourAdvanced {

    private String key;

    private Country country;

    private Type type;

    private Region region;

    private Integer hotelId;

    private String hotelName;

    private Hotel_Rating rating;

    private Meal_Type mealType;

    private Integer adultAmount;

    private Integer childAmount;

    private String accomodation;

    private String roomType;

    private Integer duration;

    private Date dateFrom;

    private Boolean combined;

    private Currency currency;

    private Set<Price> prices = new HashSet<>();

    private From_Cities city;

    private String transportType;

    private Set<Hotel_Image> images = new HashSet<>();

    private String rate;

    private String reviewCount;

    private Set<Facility> facilities = new HashSet<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
    }

    public Integer getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(Integer adultAmount) {
        this.adultAmount = adultAmount;
    }

    public Integer getChildAmount() {
        return childAmount;
    }

    public void setChildAmount(Integer childAmount) {
        this.childAmount = childAmount;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Boolean getCombined() {
        return combined;
    }

    public void setCombined(Boolean combined) {
        this.combined = combined;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public From_Cities getCity() {
        return city;
    }

    public void setCity(From_Cities city) {
        this.city = city;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Set<Hotel_Image> getImages() {
        return images;
    }

    public void setImages(Set<Hotel_Image> images) {
        this.images = images;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourAdvanced that = (TourAdvanced) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourAdvanced{" +
                "key='" + key + '\'' +
                ", country=" + country +
                ", type=" + type +
                ", region=" + region +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", rating=" + rating +
                ", mealType=" + mealType +
                ", adultAmount=" + adultAmount +
                ", childAmount=" + childAmount +
                ", accomodation='" + accomodation + '\'' +
                ", roomType='" + roomType + '\'' +
                ", duration=" + duration +
                ", dateFrom=" + dateFrom +
                ", combined=" + combined +
                ", currency=" + currency +
                ", prices=" + prices +
                ", city=" + city +
                ", transportType='" + transportType + '\'' +
                ", images=" + images +
                ", rate='" + rate + '\'' +
                ", reviewCount='" + reviewCount + '\'' +
                ", facilities=" + facilities +
                '}';
    }
}
