package com.taxi.dao.impl;

import com.taxi.dao.AddressDao;
import com.taxi.dao.HikariConnection;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import com.taxi.entity.AddressEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl extends AbstractCrudDaoImpl<AddressEntity> implements AddressDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM address WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM address";
    private static final String FIND_BY_ADDRESS_QUERY = "SELECT * FROM address WHERE address=?";
    private static final String SAVE_QUERY = "INSERT INTO address (address, latitude, longitude ) values(?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE address SET address = ?, latitude = ?, longitude = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM user";

    private static final Logger LOGGER = LogManager.getLogger(AddressDaoImpl.class);

    public AddressDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<AddressEntity> findByAddress(String address) {
        return findByParam(address, FIND_BY_ADDRESS_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    protected AddressEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return AddressEntity.builder()
                .withId(resultSet.getString("id"))
                .withAddress(resultSet.getString("address"))
                .withLatitude(resultSet.getDouble("latitude"))
                .withLongitude(resultSet.getDouble("longitude"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, AddressEntity entity) throws SQLException {
        statement.setString(1, entity.getStreet());
        statement.setString(2, String.valueOf(entity.getLatitude()));
        statement.setString(3, String.valueOf(entity.getLongitude()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, AddressEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(1, entity.getId());
    }

    @Override
    public List<AddressEntity> findAll(int page, int itemsPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<AddressEntity> address = new ArrayList<>();
                while (resultSet.next()) {
                    final AddressEntity optionalAddress = mapResultSetToEntity(resultSet);
                    address.add(optionalAddress);
                }
                return address;
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
