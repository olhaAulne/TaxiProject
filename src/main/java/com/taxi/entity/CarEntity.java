package com.taxi.entity;

import java.util.Objects;

public class CarEntity {
    private final String id;
    private final String description;
    private final String number;
    private final String driverNumber;
    private final int seat;
    private final CarType type;
    private final boolean availability;

    public CarEntity(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.number = builder.number;
        this.driverNumber = builder.driverNumber;
        this.seat = builder.seat;
        this.type = builder.type;
        this.availability = builder.availability;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNumber() {
        return number;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public int getSeat() {
        return seat;
    }

    public CarType getType() {
        return type;
    }

    public boolean isAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarEntity carEntity = (CarEntity) o;
        return seat == carEntity.seat &&
                availability == carEntity.availability &&
                Objects.equals(id, carEntity.id) &&
                Objects.equals(description, carEntity.description) &&
                Objects.equals(number, carEntity.number) &&
                Objects.equals(type, carEntity.type) &&
                Objects.equals(driverNumber, carEntity.driverNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, number, driverNumber, seat, type, availability);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", number='" + number + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", seat=" + seat + '\'' +
                ", type=" + type + '\'' +
                ", availability=" + availability +
                '}';
    }

    public static class Builder {
        private String id;
        private String description;
        private String number;
        private String driverNumber;
        private int seat;
        private CarType type;
        private boolean availability;

        private Builder() {
        }

        public CarEntity build() {
            return new CarEntity(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withDriverNumber(String driverNumber) {
            this.driverNumber = driverNumber;
            return this;
        }

        public Builder withSeat(int seat) {
            this.seat = seat;
            return this;
        }

        public Builder withSeat(CarType type) {
            this.type = type;
            return this;
        }

        public Builder withAvailability(boolean availability) {
            this.availability = availability;
            return this;
        }
    }
}
