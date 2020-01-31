package com.taxi.entity;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Order {
    private final String id;
    private final User passenger;
    private final Car car;
    private final Sale sale;
    private final OrderStatus status;
    private final LocalDateTime dateTime;
    private final Address address_from;
    private final Address address_to;
    private final Tariff tariff;

    public Order(Builder builder) {
        this.id = builder.id;
        this.passenger = builder.passenger;
        this.car = builder.car;
        this.sale = builder.sale;
        this.status = builder.status;
        this.dateTime = LocalDateTime.from(Clock.system(ZoneId.of("Europe/Kiev")).instant());
        this.address_from = builder.address_from;
        this.address_to = builder.address_to;
        this.tariff = builder.tariff;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Address getAddress_from() {
        return address_from;
    }

    public Address getAddress_to() {
        return address_to;
    }

    public Tariff getTariff() {
        return tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(passenger, order.passenger) &&
                Objects.equals(car, order.car) &&
                Objects.equals(sale, order.sale) &&
                status == order.status &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(address_from, order.address_from) &&
                Objects.equals(address_to, order.address_to) &&
                Objects.equals(tariff, order.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, car, sale, status, dateTime, address_from, address_to, tariff);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", passenger=" + passenger +
                ", car=" + car +
                ", sale=" + sale +
                ", status=" + status +
                ", dateTime=" + dateTime +
                ", address_from=" + address_from +
                ", address_to=" + address_to +
                ", tariff=" + tariff +
                '}';
    }

    public static class Builder {
        private String id;
        private User passenger;
        private Car car;
        private Sale sale;
        private OrderStatus status;
        private LocalDateTime dateTime;
        private Address address_from;
        private Address address_to;
        private Tariff tariff;

        private Builder() {
        }

        public Order build() {
            return new Order(this);
        }

        public Order.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPassenger(User passenger) {
            this.passenger = passenger;
            return this;
        }

        public Builder withCar(Car car) {
            this.car = car;
            return this;
        }

        public Builder withSale(Sale sale) {
            this.sale = sale;
            return this;
        }

        public Builder withOrderStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withAddress_from(Address address_from) {
            this.address_from = address_from;
            return this;
        }

        public Builder withAddress_to(Address address_to) {
            this.address_to = address_to;
            return this;
        }

        public Builder withTariff(Tariff tariff) {
            this.tariff = tariff;
            return this;
        }
    }
}

