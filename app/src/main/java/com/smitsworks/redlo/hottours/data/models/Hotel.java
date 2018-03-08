package com.smitsworks.redlo.hottours.data.models;

import android.graphics.Region;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class Hotel {

    private Integer id;

    private Country country;

    private Region region;

    private String hotelName;

    private String hotelReviewRate;

    private String hotelReviewCount;

    private Set<Facility> facilities = new HashSet<>();

    private String lat;

    private String lng;

    private Boolean wifiFree;

    private Set<Hotel_Image> images = new HashSet<>();

    private Hotel_Rating rating;

    private Integer adultAmount;

    private Integer childAmount;

    private String accomodation;

    private Set<TourCasual> tours = new HashSet<>();

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelReviewRate() {
        return hotelReviewRate;
    }

    public void setHotelReviewRate(String hotelReviewRate) {
        this.hotelReviewRate = hotelReviewRate;
    }

    public String getHotelReviewCount() {
        return hotelReviewCount;
    }

    public void setHotelReviewCount(String hotelReviewCount) {
        this.hotelReviewCount = hotelReviewCount;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Boolean getWifiFree() {
        return wifiFree;
    }

    public void setWifiFree(Boolean wifiFree) {
        this.wifiFree = wifiFree;
    }

    public Set<Hotel_Image> getImages() {
        return images;
    }

    public void setImages(Set<Hotel_Image> images) {
        this.images = images;
    }

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
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

    public Set<TourCasual> getTours() {
        return tours;
    }

    public void setTours(Set<TourCasual> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return hotelName != null ? hotelName.equals(hotel.hotelName) : hotel.hotelName == null;
    }

    @Override
    public int hashCode() {
        return hotelName != null ? hotelName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", country=" + country +
                ", region=" + region +
                ", hotelName='" + hotelName + '\'' +
                ", hotelReviewRate='" + hotelReviewRate + '\'' +
                ", hotelReviewCount='" + hotelReviewCount + '\'' +
                ", facilities=" + facilities +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", wifiFree=" + wifiFree +
                ", images=" + images +
                ", rating=" + rating +
                ", adultAmount=" + adultAmount +
                ", childAmount=" + childAmount +
                ", accomodation='" + accomodation + '\'' +
                ", tours=" + tours +
                '}';
    }
}
