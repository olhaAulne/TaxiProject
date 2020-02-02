package com.taxi.dao;

import com.taxi.entity.AddressEntity;

import java.util.Optional;

public interface AddressDao extends CrudDao<AddressEntity> {
    Optional<AddressEntity> findByAddress(String address);

}
