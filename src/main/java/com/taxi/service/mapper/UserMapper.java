package com.taxi.service.mapper;

import com.taxi.domain.User;
import com.taxi.entity.Role;
import com.taxi.entity.UserEntity;

public class UserMapper {

    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withRole(Role.valueOf(user.getRole().name()))
                .build();
    }

    public User mapUserEntityToUser(UserEntity userEntity) {
        return User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withRole(com.taxi.domain.Role.valueOf(userEntity.getRole().name()))
                .build();
    }
}
