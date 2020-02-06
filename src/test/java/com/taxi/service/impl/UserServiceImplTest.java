package com.taxi.service.impl;

import com.taxi.dao.UserDao;
import com.taxi.domain.User;
import com.taxi.entity.UserEntity;
import com.taxi.service.PasswordEncryptor;
import com.taxi.service.mapper.UserMapper;
import com.taxi.service.validator.Validator;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String USER_EMAIL = "email@gmail.com";
    private static final String INCORRECT_USER_EMAIL = "incorrrect@email.com";
    private static final String USER_PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encoded_password";
    private static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    private static final String ENCODE_INCORRECT_PASSWORD = "encode_incorrect_password";
    private static final User USER =
            User.builder()
                    .withId("111")
                    .withEmail(USER_EMAIL)
                    .withPassword(ENCODED_PASSWORD)
                    .build();

    private static final UserEntity USER_ENTITY = UserEntity.builder()
            .withId("111")
            .withEmail(USER_EMAIL)
            .withPassword(ENCODED_PASSWORD)
            .build();
    @Mock
    private UserDao userDao;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncryptor passwordEncryptor;
    @Mock
    private Validator<User> userValidator;
    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMocks() {
        reset(userDao, userMapper, passwordEncryptor, userValidator);
    }

    @Test
    public void userShouldLoginSuccessfully() {
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));
        when(passwordEncryptor.encrypt(eq(USER_PASSWORD))).thenReturn(ENCODED_PASSWORD);
        Optional<User> loginUser = userService.login(USER_EMAIL, USER_PASSWORD);
        assertThat(USER, is(loginUser));

        verify(passwordEncryptor, times(1)).encrypt(eq(USER_PASSWORD));
        verifyZeroInteractions(userValidator);
    }

    @Test
    public void userShouldNotLoginNotUserWithSuchEmail() {
        when(userDao.findByEmail(eq(INCORRECT_USER_EMAIL))).thenReturn(Optional.empty());
        when(passwordEncryptor.encrypt(eq(USER_PASSWORD))).thenReturn(ENCODED_PASSWORD);
        Optional<User> user = userService.login(INCORRECT_USER_EMAIL, USER_PASSWORD);

        assertFalse(user.isPresent());
        verifyZeroInteractions(passwordEncryptor);
        verifyZeroInteractions(userValidator);
    }

    @Test
    public void userShouldNotLoginNotUserWithSuchPassword() {
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));
        when(passwordEncryptor.encrypt(eq(INCORRECT_PASSWORD))).thenReturn(ENCODE_INCORRECT_PASSWORD);
        Optional<User> user = userService.login(USER_EMAIL, ENCODE_INCORRECT_PASSWORD);

        assertFalse(user.isPresent());
        verify(passwordEncryptor, times(1)).encrypt(eq(INCORRECT_PASSWORD));
        verifyZeroInteractions(userValidator);
    }

    @Test
    public void userShouldRegisterSuccessfully() {
        doNothing().when(userValidator).validate(any(User.class));
        when(userDao.findByEmail(eq(USER_EMAIL))).thenReturn(Optional.empty());
        doNothing().when(userDao).save(any(UserEntity.class));

        userService.register(USER);

        verify(userValidator).validate(any(User.class));
        verify(userDao).findByEmail(anyString());
        verify(userDao).save(any(UserEntity.class));
    }


}