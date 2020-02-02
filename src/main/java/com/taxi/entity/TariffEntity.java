package com.taxi.entity;

import java.util.Objects;

public class TariffEntity {
    private String id;
    private String name;
    private int price;

    public TariffEntity(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TariffEntity tariffEntity = (TariffEntity) o;
        return price == tariffEntity.price &&
                Objects.equals(id, tariffEntity.id) &&
                Objects.equals(name, tariffEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
