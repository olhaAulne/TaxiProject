package com.taxi.dao.impl;

import com.taxi.dao.ConnectorDB;
import com.taxi.dao.UserDao;
import com.taxi.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String SAVE_QUERY = "INSERT INTO user (email, password, name, surname, phone_number ) values(?, ?, ?,? ,?)";
    private static final String UPDATE_QUERY = "UPDATE user SET email = ?, password = ?, name = ?, surname = ?, telephone_number =?  WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM user WHERE id = ?";
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .withId(resultSet.getString("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPhone(resultSet.getString("phone_number"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getSurname());
        statement.setString(5, entity.getTelephoneNumber());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {
        prepareStatementForInsert(statement,entity);
        statement.setString(6, entity.getId());
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY, STRING_PARAM_SETTER);
    }
}