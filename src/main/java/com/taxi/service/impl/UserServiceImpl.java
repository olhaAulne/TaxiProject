package com.taxi.service.impl;

import com.taxi.dao.UserDao;
import com.taxi.domain.User;
import com.taxi.service.PasswordEncryptor;
import com.taxi.service.UserService;
import com.taxi.service.exception.EntityAlreadyExistException;
import com.taxi.service.exception.EntityNotFoundException;
import com.taxi.service.mapper.UserMapper;
import com.taxi.service.validator.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncryptor passwordEncryptor;
    private final Validator<User> validator;
    private final int USERS_PER_PAGE = 5;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper, PasswordEncryptor passwordEncryptor, Validator<User> validator) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncryptor = passwordEncryptor;
        this.validator = validator;
    }

    @Override
    public void register(User user) {
        validator.validate(user);
        User encryptedUser = User.copyUser(user);
        if (userDao.findByEmail(encryptedUser.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException(String.format("User %s is already exist", encryptedUser.getEmail()));
        }
        userDao.save(userMapper.mapUserToUserEntity(encryptedUser));
    }

    @Override
    public Optional<User> login(String email, String password) {
        final Optional<User> user = userDao.findByEmail(email).map(userMapper::mapUserEntityToUser);
        if (user.isPresent()) {
            String encryptedPassword = passwordEncryptor.encrypt(password);
            if (encryptedPassword.equals(user.get().getPassword())) {
                return user;
            }
        }
        throw new EntityAlreadyExistException(String.format("User %s is not exist", email));

    }

    @Override
    public List<User> findAll(int pageNumber) {
        return userDao.findAll(pageNumber, USERS_PER_PAGE).stream()
                .map(userMapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDao.findById(id)
                .map(userMapper::mapUserEntityToUser)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s is not found", id))));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userDao.findByEmail(email)
                .map(userMapper::mapUserEntityToUser)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with e-mail %s is not found", email))));
    }
}
