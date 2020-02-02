package com.taxi.entity;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class OrderEntity {
    private final String id;
    private final UserEntity passenger;
    private final CarEntity car;
    private final SaleEntity sale;
    private final DiscountEntity discount;
    private final OrderStatus status;
    private final LocalDateTime dateTime;
    private final AddressEntity addressFrom;
    private final AddressEntity addressTo;
    private final TariffEntity tariff;

    public OrderEntity(Builder builder) {
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

    public UserEntity getPassenger() {
        return passenger;
    }

    public CarEntity getCar() {
        return car;
    }

    public SaleEntity getSale() {
        return sale;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DiscountEntity getDiscount() {
        return discount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AddressEntity getAddressFrom() {
        return addressFrom;
    }

    public AddressEntity getAddressTo() {
        return addressTo;
    }

    public TariffEntity getTariff() {
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
        OrderEntity orderEntity = (OrderEntity) o;
        return Objects.equals(id, orderEntity.id) &&
                Objects.equals(passenger, orderEntity.passenger) &&
                Objects.equals(car, orderEntity.car) &&
                Objects.equals(sale, orderEntity.sale) &&
                Objects.equals(discount, orderEntity.discount) &&
                status == orderEntity.status &&
                Objects.equals(dateTime, orderEntity.dateTime) &&
                Objects.equals(addressFrom, orderEntity.addressFrom) &&
                Objects.equals(addressTo, orderEntity.addressTo) &&
                Objects.equals(tariff, orderEntity.tariff);
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
        private UserEntity passenger;
        private CarEntity car;
        private SaleEntity sale;
        private DiscountEntity discount;
        private OrderStatus status;
        private LocalDateTime dateTime;
        private AddressEntity addressFrom;
        private AddressEntity addressTo;
        private TariffEntity tariff;

        private Builder() {
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }

        public OrderEntity.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withPassenger(UserEntity passenger) {
            this.passenger = passenger;
            return this;
        }

        public Builder withCar(CarEntity car) {
            this.car = car;
            return this;
        }

        public Builder withSale(SaleEntity sale) {
            this.sale = sale;
            return this;
        }

        public Builder withDiscount(DiscountEntity discount) {
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

        public Builder withAddressFrom(AddressEntity addressFrom) {
            this.addressFrom = addressFrom;
            return this;
        }

        public Builder withAddressTo(AddressEntity addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public Builder withTariff(TariffEntity tariffEntity) {
            this.tariff = tariffEntity;
            return this;
        }
    }
}

