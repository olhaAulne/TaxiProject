package com.taxi.dao.impl;

import com.taxi.dao.CarDao;
import com.taxi.dao.HikariConnection;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import com.taxi.entity.AddressEntity;
import com.taxi.entity.CarEntity;
import com.taxi.entity.CarType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl extends AbstractCrudDaoImpl<CarEntity> implements CarDao {

    private static final String FIND_BY_NUMBER_QUERY = "SELECT * FROM car WHERE number=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM car WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM car";
    private static final String SAVE_QUERY = "INSERT INTO car (description, car_number, driver_number, seat, " +
            "type, availability) values(?, ?, ?, ? ,?, ?)";
    private static final String UPDATE_QUERY = "UPDATE car SET description = ?, car_number = ?, driver_number = ?, " +
            "seat = ?, type =?, availability=?  WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM car";

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    public CarDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<CarEntity> findByNumber(String number) {
        return findByParam(number, FIND_BY_NUMBER_QUERY, STRING_PARAM_SETTER);
    }

    @Override
    protected CarEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return CarEntity.builder()
                .withId(resultSet.getString("id"))
                .withDescription(resultSet.getString("description"))
                .withNumber(resultSet.getString("car_number"))
                .withDriverNumber(resultSet.getString("driver_number"))
                .withSeat(resultSet.getInt("seat"))
                .withType(CarType.values()[resultSet.getInt("type") - 1])
                .withAvailability(resultSet.getInt("availability"))
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CarEntity entity) throws SQLException {
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getNumber());
        statement.setString(3, entity.getDriverNumber());
        statement.setString(4, String.valueOf(entity.getSeat()));
        statement.setString(5, String.valueOf(entity.getType()));
        statement.setString(6, String.valueOf(entity.getAvailability()));
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CarEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(7, entity.getId());
    }

    @Override
    public List<CarEntity> findAll(int page, int itemsPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<CarEntity> cars = new ArrayList<>();
                while (resultSet.next()) {
                    final CarEntity optionalAddress = mapResultSetToEntity(resultSet);
                    cars.add(optionalAddress);
                }
                return cars;
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
