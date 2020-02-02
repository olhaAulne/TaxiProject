package com.taxi.domain;

import java.util.Objects;

public class Address {
    private final String id;
    private final String address;
    private final double latitude;
    private final double longitude;

    public Address(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address1 = (Address) o;
        return Double.compare(address1.latitude, latitude) == 0 &&
                Double.compare(address1.longitude, longitude) == 0 &&
                Objects.equals(id, address1.id) &&
                Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static class Builder {
        private String id;
        private String address;
        private double latitude;
        private double longitude;

        private Builder() {
        }

        public Address build() {
            return new Address(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder withLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}
