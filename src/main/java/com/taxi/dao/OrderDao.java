package com.taxi.dao;

import com.taxi.entity.OrderEntity;

import java.util.List;

public interface OrderDao extends CrudPageableDao<OrderEntity> {
    List<OrderEntity> findByPassenger(String userId);
}
