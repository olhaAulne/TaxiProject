package com.taxi.entity;

import java.util.Objects;

public class SaleEntity {
    private String id;
    private String saleName;
    private double amount;

    public SaleEntity(String id, String saleName, double amount) {
        this.id = id;
        this.saleName = saleName;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getSaleName() {
        return saleName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaleEntity saleEntity = (SaleEntity) o;
        return Double.compare(saleEntity.amount, amount) == 0 &&
                Objects.equals(id, saleEntity.id) &&
                Objects.equals(saleName, saleEntity.saleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleName, amount);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id='" + id + '\'' +
                ", saleName='" + saleName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
