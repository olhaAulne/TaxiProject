package com.taxi.dao;

import com.taxi.entity.CarEntity;

import java.util.Optional;

public interface CarDao extends CrudPageableDao<CarEntity> {
    Optional<CarEntity> findByNumber(String number);

}
