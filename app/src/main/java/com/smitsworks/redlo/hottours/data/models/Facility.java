package com.smitsworks.redlo.hottours.data.models;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class Facility {

    private String id;

    private String name;

    private String categoryId;

    private String category;

    private Boolean main;

    private Boolean paid;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        if (name != null ? !name.equals(facility.name) : facility.name != null) return false;
        if (categoryId != null ? !categoryId.equals(facility.categoryId) : facility.categoryId != null)
            return false;
        if (category != null ? !category.equals(facility.category) : facility.category != null)
            return false;
        if (main != null ? !main.equals(facility.main) : facility.main != null) return false;
        return paid != null ? paid.equals(facility.paid) : facility.paid == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", category='" + category + '\'' +
                ", main=" + main +
                ", paid=" + paid +
                '}';
    }
}
