package com.taxi.dao;

import com.taxi.entity.DiscountEntity;
import com.taxi.entity.UserEntity;

import java.util.Optional;

public interface DiscountDao extends CrudDao<DiscountEntity> {
    Optional<DiscountEntity> findByPassenger(UserEntity passenger);

}
