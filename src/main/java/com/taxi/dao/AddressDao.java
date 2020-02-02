package com.taxi.dao;

import com.taxi.entity.AddressEntity;
import com.taxi.entity.OrderEntity;

import java.util.List;

public interface AddressDao extends CrudDao<AddressEntity>  {
    List<OrderEntity> findByAddress(String address);

}
