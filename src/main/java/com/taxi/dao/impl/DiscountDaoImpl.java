package com.taxi.dao.impl;

import com.taxi.dao.DiscountDao;
import com.taxi.dao.HikariConnection;
import com.taxi.entity.DiscountEntity;
import com.taxi.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DiscountDaoImpl extends AbstractCrudDaoImpl<DiscountEntity> implements DiscountDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM discount WHERE id=?";
    private static final String FIND_BY_USER_QUERY = "SELECT * FROM discount WHERE user_id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM discount";
    private static final String SAVE_QUERY = "INSERT INTO discount (user_id, percent) values(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE discount SET user_id = ?, percent = ? WHERE id = ?";

    public DiscountDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<DiscountEntity> findByPassenger(UserEntity passenger) {
        return findByParam(passenger.getId(), FIND_BY_USER_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    protected DiscountEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new DiscountEntity(resultSet.getString("id"),
                UserEntity.builder().withId(resultSet.getString("user_id")).build(),
                resultSet.getDouble("percent"));
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, DiscountEntity entity) throws SQLException {
        statement.setString(1, entity.getPassenger().getId());
        statement.setString(2, String.valueOf(entity.getPercent()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, DiscountEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(3, entity.getId());
    }
}
