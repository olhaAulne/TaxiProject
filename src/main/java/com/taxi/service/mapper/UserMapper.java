package com.taxi.service.mapper;

import com.taxi.domain.User;
import com.taxi.entity.UserEntity;
import com.taxi.service.PasswordEncryptor;

import java.util.Optional;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class UserMapper {
    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withPassword(passwordEncryptor.encrypt(user.getPassword()))
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withPhone(user.getTelephoneNumber())
                .withBirthday(user.getBirthday())
                .withGender(user.getGender())
                .build();
    }
}
