package com.taxi.domain;

import com.taxi.entity.UserEntity;

import java.util.Objects;

public class Car {
    private final String id;
    private final String description;
    private final String number;
    private final String driverNumber;
    private final int seat;
    private final CarType type;
    private final int availability;

    public Car(Builder builder) {
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

    public int getAvailability() {
        return availability;
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
        Car car = (Car) o;
        return seat == car.seat &&
                availability == car.availability &&
                Objects.equals(id, car.id) &&
                Objects.equals(description, car.description) &&
                Objects.equals(number, car.number) &&
                Objects.equals(type, car.type) &&
                Objects.equals(driverNumber, car.driverNumber);
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
        private int availability;

        private Builder() {
        }

        public Car build() {
            return new Car(this);
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

        public Builder withType(CarType type) {
            this.type = type;
            return this;
        }

        public Builder withAvailability(int availability) {
            this.availability = availability;
            return this;
        }
    }
}
