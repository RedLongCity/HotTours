package com.smitsworks.redlo.hottours.data.models;

/**
 * Created by redlongcity on 14.10.2017.
 * class for storing data about order
 */

public class Order {

    private UserData data;

    private String key;

    public Order() {
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public boolean isEmpty(){
        if (data != null) {
            return data.isEmpty();
        }
        return false;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (data != null ? !data.equals(order.data) : order.data != null) return false;
        return key != null ? key.equals(order.key) : order.key == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "data=" + data +
                ", key='" + key + '\'' +
                '}';
    }
}
