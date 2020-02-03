package com.taxi.entity;

import java.util.Objects;

public class OrderEntity {
    private final String id;
    private final String passenger;
    private final String car;
    private final String sale;
    private final OrderStatus status;
    private final String dateTime;
    private final String addressFrom;
    private final String addressTo;
    private final String tariff;

    public OrderEntity(Builder builder) {
        this.id = builder.id;
        this.passenger = builder.passenger;
        this.car = builder.car;
        this.sale = builder.sale;
        this.status = builder.status;
        this.dateTime = builder.dateTime;
        this.addressFrom = builder.addressFrom;
        this.addressTo = builder.addressTo;
        this.tariff = builder.tariff;
    }

    public String getId() {
        return id;
    }

    public String getPassenger() {
        return passenger;
    }

    public String getCar() {
        return car;
    }

    public String getSale() {
        return sale;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public String getTariff() {
        return tariff;
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
        OrderEntity orderEntity = (OrderEntity) o;
        return Objects.equals(id, orderEntity.id) &&
                Objects.equals(passenger, orderEntity.passenger) &&
                Objects.equals(car, orderEntity.car) &&
                Objects.equals(sale, orderEntity.sale) &&
                status == orderEntity.status &&
                Objects.equals(dateTime, orderEntity.dateTime) &&
                Objects.equals(addressFrom, orderEntity.addressFrom) &&
                Objects.equals(addressTo, orderEntity.addressTo) &&
                Objects.equals(tariff, orderEntity.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, car, sale, status, dateTime, addressFrom, addressTo, tariff);
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
                ", addressFrom=" + addressFrom +
                ", addressTo=" + addressTo +
                ", tariff=" + tariff +
                '}';
    }

    public static class Builder {
        private String id;
        private String passenger;
        private String car;
        private String sale;
        private OrderStatus status;
        private String dateTime;
        private String addressFrom;
        private String addressTo;
        private String tariff;

        private Builder() {
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }

        public OrderEntity.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPassenger(String passenger) {
            this.passenger = passenger;
            return this;
        }

        public Builder withCar(String car) {
            this.car = car;
            return this;
        }

        public Builder withSale(String sale) {
            this.sale = sale;
            return this;
        }

        public Builder withOrderStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withDateTime(String dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withAddressFrom(String addressFrom) {
            this.addressFrom = addressFrom;
            return this;
        }

        public Builder withAddressTo(String addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public Builder withTariff(String tariffEntity) {
            this.tariff = tariffEntity;
            return this;
        }
    }
}

