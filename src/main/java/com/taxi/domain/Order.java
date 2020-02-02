package com.taxi.domain;



import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Order {
    private final String id;
    private final User passenger;
    private final Car car;
    private final Sale sale;
    private final Discount discount;
    private final OrderStatus status;
    private final LocalDateTime dateTime;
    private final Address addressFrom;
    private final Address addressTo;
    private final Tariff tariff;

    public Order(Builder builder) {
        this.id = builder.id;
        this.passenger = builder.passenger;
        this.car = builder.car;
        this.sale = builder.sale;
        this.discount = builder.discount;
        this.status = builder.status;
        this.dateTime = LocalDateTime.from(Clock.system(ZoneId.of("Europe/Kiev")).instant());
        this.addressFrom = builder.addressFrom;
        this.addressTo = builder.addressTo;
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

    public Discount getDiscount() {
        return discount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
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
                Objects.equals(discount, order.discount) &&
                status == order.status &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(addressFrom, order.addressFrom) &&
                Objects.equals(addressTo, order.addressTo) &&
                Objects.equals(tariff, order.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, car, sale, discount, status, dateTime, addressFrom, addressTo, tariff);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", passenger=" + passenger +
                ", car=" + car +
                ", sale=" + sale +
                ", discount=" + discount +
                ", status=" + status +
                ", dateTime=" + dateTime +
                ", addressFrom=" + addressFrom +
                ", addressTo=" + addressTo +
                ", tariff=" + tariff +
                '}';
    }

    public static class Builder {
        private String id;
        private User passenger;
        private Car car;
        private Sale sale;
        private Discount discount;
        private OrderStatus status;
        private LocalDateTime dateTime;
        private Address addressFrom;
        private Address addressTo;
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

        public Builder withDiscount(Discount discount) {
            this.discount = discount;
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

        public Builder withAddressFrom(Address addressFrom) {
            this.addressFrom = addressFrom;
            return this;
        }

        public Builder withAddressTo(Address addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public Builder withTariff(Tariff tariffEntity) {
            this.tariff = tariffEntity;
            return this;
        }
    }
}

