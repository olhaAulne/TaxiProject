package com.taxi.entity;

import java.util.Objects;

public class Car {
    private final String id;
    private final String description;
    private final String model;
    private final String color;
    private final boolean availability;

    public Car(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.model = builder.model;
        this.color = builder.color;
        this.availability = builder.availability;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public boolean isAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return  availability == car.availability &&
                Objects.equals(id, car.id) &&
                Objects.equals(description, car.description) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, model, color,  availability);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", availability=" + availability +
                '}';
    }

    public static class Builder {
        private String id;
        private String description;
        private String model;
        private String color;
        private boolean availability;

        private Builder() {
        }

        public Car build() {
            return new Car(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withMake(String description) {
            this.description = description;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withAvailability(boolean availability) {
            this.availability = availability;
            return this;
        }
    }
}
