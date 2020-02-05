package com.taxi.entity;

import java.util.Objects;

public class DiscountEntity {
    private String id;
    private User passenger;
    private double percent;

    public DiscountEntity(String id, User passenger, double percent) {
        this.id = id;
        this.passenger = passenger;
        this.percent = percent;
    }

    public String getId() {
        return id;
    }

    public User getPassenger() {
        return passenger;
    }

    public double getPercent() {
        return percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountEntity discountEntity = (DiscountEntity) o;
        return Double.compare(discountEntity.percent, percent) == 0 &&
                Objects.equals(id, discountEntity.id) &&
                Objects.equals(passenger, discountEntity.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, percent);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id='" + id + '\'' +
                ", passenger=" + passenger +
                ", percent=" + percent +
                '}';
    }
}
