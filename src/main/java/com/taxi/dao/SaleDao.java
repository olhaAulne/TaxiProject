package com.taxi.dao;

import com.taxi.entity.SaleEntity;

import java.util.Optional;

public interface SaleDao extends CrudDao<SaleEntity> {
    Optional<SaleEntity> findBySaleName(String name);

}
