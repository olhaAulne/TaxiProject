package com.taxi.dao.impl;

import com.taxi.dao.C3poDataSource;
import com.taxi.dao.Page;
import com.taxi.dao.UserDao;
import com.taxi.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<UserEntity> implements UserDao {

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String SAVE_QUERY = "INSERT INTO user (email, password, name, surname, phone_number ) values(?, ?, ?,? ,?)";
    private static final String UPDATE_QUERY = "UPDATE user SET email = ?, password = ?, name = ?, surname = ?, telephone_number =?  WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String COUNT_RECORD = "SELECT COUNT FROM user ";

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(C3poDataSource connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY, COUNT_RECORD);
    }

    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getString("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPhone(resultSet.getString("phone_number"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserEntity entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getSurname());
        statement.setString(5, entity.getTelephoneNumber());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserEntity entity) throws SQLException {
        prepareStatementForInsert(statement,entity);
        statement.setString(6, entity.getId());
    }


    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY, STRING_PARAM_SETTER);
    }
    @Override
    public List<UserEntity> findAll(Page page) {
        return findAll(page);
    }



}
