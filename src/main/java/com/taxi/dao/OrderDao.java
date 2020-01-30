package com.taxi.dao;

import com.taxi.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao extends CrudDao<Order> {
    List<Order> findByDate(Date date);
    double countAmount(Order order);
}
