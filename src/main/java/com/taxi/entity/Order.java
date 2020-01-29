package com.taxi.entity;

import java.util.Date;
import java.util.Objects;

public class Order {
    private final String id;
    private final User passenger;
    private final Car car;
    private final Sale sale;
    private final OrderStatus status;
    private final Date date;
    private final Address address;

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.passenger = builder.passenger;
        this.car = builder.car;
        this.sale = builder.sale;
        this.status = builder.status;
        this.date = builder.date;
        this.address = builder.address;
    }

    public String getId() {
        return id;
    }

    public User getPassenger() {
        return passenger;
    }

    public Car getCar() {
        return car;
    }

    public Sale getSale() {
        return sale;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(passenger, order.passenger) &&
                Objects.equals(car, order.car) &&
                Objects.equals(sale, order.sale) &&
                status == order.status &&
                Objects.equals(date, order.date) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, car, sale, status, date, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", passenger=" + passenger +
                ", car=" + car +
                ", sale=" + sale +
                ", status=" + status +
                ", date=" + date +
                ", address=" + address +
                '}';
    }

    public static class OrderBuilder {
        private  String id;
        private  User passenger;
        private  Car car;
        private  Sale sale;
        private  OrderStatus status;
        private  Date date;
        private  Address address;

        private OrderBuilder() {
        }

        public Order build() {
            return new Order(this);
        }

        public Order.OrderBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public Order.OrderBuilder withPassenger(User passenger) {
            this.passenger = passenger;
            return this;
        }

        public Order.OrderBuilder withCar(Car car) {
            this.car = car;
            return this;
        }

        public Order.OrderBuilder withSale(Sale sale) {
            this.sale = sale;
            return this;
        }

        public Order.OrderBuilder withOrderStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order.OrderBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Order.OrderBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }
    }
}
