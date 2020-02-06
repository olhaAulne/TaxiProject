package com.taxi.dao.impl;

import com.taxi.dao.HikariConnection;
import com.taxi.dao.TariffDao;
import com.taxi.entity.TariffEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TariffDaoImpl extends AbstractCrudDaoImpl<TariffEntity> implements TariffDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM tariff WHERE id=?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM tariff WHERE tariff_name=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM tariff";
    private static final String SAVE_QUERY = "INSERT INTO tariff (tariff_name, price) values(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE tariff SET tariff_name = ?, price = ? WHERE id = ?";


    public TariffDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    @Override
    protected TariffEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new TariffEntity(resultSet.getString("id"),
                resultSet.getString("tariff_name"),
                resultSet.getInt("price"));
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TariffEntity entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, String.valueOf(entity.getPrice()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TariffEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(3, entity.getId());
    }

    @Override
    public Optional<TariffEntity> findByTariffName(String name) {
        return findByParam(name, FIND_BY_NAME_QUERY, STRING_PARAM_SETTER);
    }
}
