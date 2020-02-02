package com.taxi.dao.impl;

import com.taxi.dao.AddressDao;
import com.taxi.dao.C3poDataSource;
import com.taxi.entity.AddressEntity;
import com.taxi.entity.OrderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl extends AbstractCrudDaoImpl<AddressEntity> implements AddressDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM address WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM address";
    private static final String SAVE_QUERY = "INSERT INTO address (address, latitude, longitude ) values(?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE address SET email = ?, password = ?, name = ?, surname = ?, telephone_number =?  WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM address WHERE id = ?";
    private static final String COUNT_RECORD = "SELECT ";

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public AddressDaoImpl(C3poDataSource connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY, COUNT_RECORD);
    }

    @Override
    public List<OrderEntity> findByAddress(String address) {
        return null;
    }

    @Override
    protected AddressEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, AddressEntity entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, AddressEntity entity) throws SQLException {

    }
}
