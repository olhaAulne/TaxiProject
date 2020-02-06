package com.taxi.service.mapper;

import com.taxi.entity.UserEntity;

public class UserMapper {

    public UserEntity mapUserToUserEntity(com.taxi.domain.User user) {
        return UserEntity.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .build();
    }

    public com.taxi.domain.User mapUserEntityToUser(UserEntity userEntity) {
        return com.taxi.domain.User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .build();
    }
}
