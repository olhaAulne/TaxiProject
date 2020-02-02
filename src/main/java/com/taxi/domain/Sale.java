package com.taxi.domain;

import java.util.Objects;

public class Sale {
    private String id;
    private String saleName;
    private double amount;

    public Sale(String id, String saleName, double amount) {
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
        Sale sale = (Sale) o;
        return Double.compare(sale.amount, amount) == 0 &&
                Objects.equals(id, sale.id) &&
                Objects.equals(saleName, sale.saleName);
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
