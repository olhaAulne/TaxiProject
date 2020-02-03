package com.taxi.dao.impl;

import com.taxi.dao.HikariConnection;
import com.taxi.dao.UserDao;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import com.taxi.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<UserEntity> implements UserDao {

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM user";
    private static final String SAVE_QUERY = "INSERT INTO user (email, password, name, surname, phone_number, birthday, " +
            "gender, role ) values(?, ?, ?, ? ,?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET email = ?, password = ?, name = ?, surname = ?, " +
            "phone_number =?, birthday=?, gender=?, role=?  WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM user";


    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getString("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPhone(resultSet.getString("phone_number"))
                .withBirthday(resultSet.getDate("birthday"))
                .withGender(resultSet.getString("gender"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserEntity entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getName());
        statement.setString(4, entity.getSurname());
        statement.setString(5, entity.getTelephoneNumber());
        statement.setString(6, entity.getBirthday().toString());
        statement.setString(7, entity.getGender());
        statement.setString(8, entity.getRole().name());


    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(9, entity.getId());
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    public List<UserEntity> findAll(int page, int itemsPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<UserEntity> users = new ArrayList<>();
                while (resultSet.next()) {
                    final UserEntity optionalUser = mapResultSetToEntity(resultSet);
                    users.add(optionalUser);
                }
                return users;
            }

        } catch (SQLException e) {
            LOGGER.error(String.format("Can't execute query [%s]", e));
            throw new DataBaseSqlRuntimeException("", e);

        }
    }

    @Override
    public long count() {
        return super.count(COUNT_QUERY);
    }
}
