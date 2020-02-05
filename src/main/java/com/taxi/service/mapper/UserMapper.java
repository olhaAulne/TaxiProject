package com.taxi.service.mapper;

import com.taxi.entity.User;
import com.taxi.service.PasswordEncryptor;

public class UserMapper {

    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public User mapUserToUserEntity(com.taxi.domain.User user) {
        return User.builder()
                .withId(user.getId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withEmail(user.getEmail())
                .withPassword(passwordEncryptor.encrypt(user.getPassword()))
                .build();
    }

    public com.taxi.domain.User mapUserEntityToUser(User userEntity) {
        return com.taxi.domain.User.builder()
                .withId(userEntity.getId())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .build();
    }
}
