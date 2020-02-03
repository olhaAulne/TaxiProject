package com.taxi.entity;

import java.util.Objects;

public class AddressEntity {
    private final String id;
    private final String street;
    private final double latitude;
    private final double longitude;

    public AddressEntity(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressEntity addressEntity1 = (AddressEntity) o;
        return Double.compare(addressEntity1.latitude, latitude) == 0 &&
                Double.compare(addressEntity1.longitude, longitude) == 0 &&
                Objects.equals(id, addressEntity1.id) &&
                Objects.equals(street, addressEntity1.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", address='" + street + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static class Builder {
        private String id;
        private String street;
        private double latitude;
        private double longitude;

        private Builder() {
        }

        public AddressEntity build() {
            return new AddressEntity(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(String street) {
            this.street = street;
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
