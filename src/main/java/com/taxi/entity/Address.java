package com.taxi.entity;

import java.util.Objects;

public class Address {
    private String id;
    private String departure;
    private String arriving;
    private double distance;

    public Address(String id, String departure, String arriving, double distance) {
        this.id = id;
        this.departure = departure;
        this.arriving = arriving;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArriving() {
        return arriving;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Double.compare(address.distance, distance) == 0 &&
                Objects.equals(id, address.id) &&
                Objects.equals(departure, address.departure) &&
                Objects.equals(arriving, address.arriving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departure, arriving, distance);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", departure='" + departure + '\'' +
                ", arriving='" + arriving + '\'' +
                ", distance=" + distance +
                '}';
    }
}
