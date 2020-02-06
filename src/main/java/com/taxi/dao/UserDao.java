package com.taxi.dao;

import com.taxi.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudPageableDao<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
