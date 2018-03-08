package com.smitsworks.redlo.hottours.data.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class SearchingRequest {

    private Integer id;

    private Integer type;//TODO TYPE

    private Integer kind;

    private Country country;

    private From_Cities city;

    private Region region;

    private String hotel;

    private Set<Hotel_Rating> ratingSet = new HashSet<>();

    private Integer adultAmount;

    private Integer childAmount;

    private String childAge;

    private Integer nightFrom;

    private Integer nightTill;

    private Date dateFrom;

    private Date dateTill;

    private Meal_Type mealType;

    private Integer priceFrom;

    private Integer priceTill;

    private Currency currency;

    private Integer onlyStandart;//TODO Boolean

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public From_Cities getCity() {
        return city;
    }

    public void setCity(From_Cities city) {
        this.city = city;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Set<Hotel_Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Hotel_Rating> ratingSet) {
        this.ratingSet = ratingSet;
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

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public Integer getNightFrom() {
        return nightFrom;
    }

    public void setNightFrom(Integer nightFrom) {
        this.nightFrom = nightFrom;
    }

    public Integer getNightTill() {
        return nightTill;
    }

    public void setNightTill(Integer nightTill) {
        this.nightTill = nightTill;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTill() {
        return priceTill;
    }

    public void setPriceTill(Integer priceTill) {
        this.priceTill = priceTill;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getOnlyStandart() {
        return onlyStandart;
    }

    public void setOnlyStandart(Integer onlyStandart) {
        this.onlyStandart = onlyStandart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchingRequest that = (SearchingRequest) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null) return false;
        if (ratingSet != null ? !ratingSet.equals(that.ratingSet) : that.ratingSet != null)
            return false;
        if (adultAmount != null ? !adultAmount.equals(that.adultAmount) : that.adultAmount != null)
            return false;
        if (childAmount != null ? !childAmount.equals(that.childAmount) : that.childAmount != null)
            return false;
        if (childAge != null ? !childAge.equals(that.childAge) : that.childAge != null)
            return false;
        if (nightFrom != null ? !nightFrom.equals(that.nightFrom) : that.nightFrom != null)
            return false;
        if (nightTill != null ? !nightTill.equals(that.nightTill) : that.nightTill != null)
            return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null)
            return false;
        if (dateTill != null ? !dateTill.equals(that.dateTill) : that.dateTill != null)
            return false;
        if (mealType != null ? !mealType.equals(that.mealType) : that.mealType != null)
            return false;
        if (priceFrom != null ? !priceFrom.equals(that.priceFrom) : that.priceFrom != null)
            return false;
        if (priceTill != null ? !priceTill.equals(that.priceTill) : that.priceTill != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null)
            return false;
        return onlyStandart != null ? onlyStandart.equals(that.onlyStandart) : that.onlyStandart == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (ratingSet != null ? ratingSet.hashCode() : 0);
        result = 31 * result + (adultAmount != null ? adultAmount.hashCode() : 0);
        result = 31 * result + (childAmount != null ? childAmount.hashCode() : 0);
        result = 31 * result + (childAge != null ? childAge.hashCode() : 0);
        result = 31 * result + (nightFrom != null ? nightFrom.hashCode() : 0);
        result = 31 * result + (nightTill != null ? nightTill.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTill != null ? dateTill.hashCode() : 0);
        result = 31 * result + (mealType != null ? mealType.hashCode() : 0);
        result = 31 * result + (priceFrom != null ? priceFrom.hashCode() : 0);
        result = 31 * result + (priceTill != null ? priceTill.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (onlyStandart != null ? onlyStandart.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchingRequest{" +
                "id=" + id +
                ", type=" + type +
                ", kind=" + kind +
                ", country=" + country +
                ", city=" + city +
                ", region=" + region +
                ", hotel='" + hotel + '\'' +
                ", ratingSet=" + ratingSet +
                ", adultAmount=" + adultAmount +
                ", childAmount=" + childAmount +
                ", childAge='" + childAge + '\'' +
                ", nightFrom=" + nightFrom +
                ", nightTill=" + nightTill +
                ", dateFrom=" + dateFrom +
                ", dateTill=" + dateTill +
                ", mealType=" + mealType +
                ", priceFrom=" + priceFrom +
                ", priceTill=" + priceTill +
                ", currency=" + currency +
                ", onlyStandart=" + onlyStandart +
                '}';
    }
}
