package com.taxi.dao;

import com.taxi.entity.AddressEntity;

import java.util.Optional;

public interface AddressDao extends CrudPageableDao<AddressEntity> {
    Optional<AddressEntity> findByAddress(String address);

}
