package com.taxi.dao;

import com.taxi.entity.Address;
import com.taxi.entity.Order;

public interface OrderDao extends CrudDao<Order> {
    double countAmount(Order order);
    double countDistance(AddressDao from, Address to);
}
