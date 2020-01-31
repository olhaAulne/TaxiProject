package com.taxi.dao;

import com.taxi.entity.Address;
import com.taxi.entity.Order;

import java.util.Date;
import java.util.List;

public interface AddressDao extends CrudDao<Address>  {
    List<Order> findByAddress(String address);

}
