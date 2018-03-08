package com.smitsworks.redlo.hottours.data.models;

/**
 * Created by redlongcity on 08.03.2018.
 */

public class Type {

    private String id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (id != null ? !id.equals(type.id) : type.id != null) return false;
        return name != null ? name.equals(type.name) : type.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
