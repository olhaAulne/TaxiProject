package com.taxi.dao.impl;

import com.taxi.dao.HikariConnection;
import com.taxi.dao.SaleDao;
import com.taxi.entity.SaleEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SaleDaoImpl extends AbstractCrudDaoImpl<SaleEntity> implements SaleDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM sale WHERE id=?";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM sale WHERE sale_name=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM sale";
    private static final String SAVE_QUERY = "INSERT INTO sale (sale_name, amount) values(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE sale SET sale_name = ?, amount = ? WHERE id = ?";

    public SaleDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<SaleEntity> findBySaleName(String name) {
        return findByParam(name, FIND_BY_NAME_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    protected SaleEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new SaleEntity(resultSet.getString("id"),
                resultSet.getString("sale_name"),
                resultSet.getInt("amount"));
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, SaleEntity entity) throws SQLException {
        statement.setString(1, entity.getSaleName());
        statement.setString(2, String.valueOf(entity.getAmount()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, SaleEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(3, entity.getId());
    }
}
