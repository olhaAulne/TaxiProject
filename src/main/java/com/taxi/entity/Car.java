package com.taxi.entity;

import java.util.Objects;

public class Car {
    private final String id;
    private final String make;
    private final String model;
    private final String color;
    private final int yearOfIssue;
    private final boolean availability;

    public Car(CarBuilder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.color = builder.color;
        this.yearOfIssue = builder.yearOfIssue;
        this.availability = builder.availability;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public boolean isAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfIssue == car.yearOfIssue &&
                availability == car.availability &&
                Objects.equals(id, car.id) &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, color, yearOfIssue, availability);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", yearOfIssue=" + yearOfIssue + '\'' +
                ", availability=" + availability +
                '}';
    }

    public static class CarBuilder {
        private String id;
        private String make;
        private String model;
        private String color;
        private int yearOfIssue;
        private boolean availability;

        private CarBuilder() {
        }

        public Car build() {
            return new Car(this);
        }

        public Car.CarBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public Car.CarBuilder withMake(String make) {
            this.make = make;
            return this;
        }

        public Car.CarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public Car.CarBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public Car.CarBuilder withYearOfIssue(String yearOfIssue) {
            this.color = color;
            return this;
        }

        public Car.CarBuilder withAvailability(boolean availability) {
            this.availability = availability;
            return this;
        }
    }
}
