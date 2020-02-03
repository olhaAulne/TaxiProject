package com.taxi.dao.impl;

import com.taxi.dao.HikariConnection;
import com.taxi.dao.OrderDao;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import com.taxi.entity.OrderEntity;
import com.taxi.entity.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDaoImpl extends AbstractCrudDaoImpl<OrderEntity> implements OrderDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM taxi_order WHERE id=?";
    private static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM taxi_order WHERE id_user=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM taxi_order";
    private static final String SAVE_QUERY = "INSERT INTO taxi_order (id_user, id_car, id_sale, order_date, " +
            "id_address_from, id_address_to, id_tariff, type  ) values(?, ?, ?, ? ,?, ? ,?, ?)";
    private static final String UPDATE_QUERY = "UPDATE taxi_order SET id_user = ?, id_car = ?, id_sale = ?, order_date = ?, " +
            "id_address_from = ?, id_address_to =?, id_tariff = ?, type = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM taxi_order ";

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(HikariConnection connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, SAVE_QUERY);
    }

    @Override
    protected OrderEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return OrderEntity.builder()
                .withId(resultSet.getString("id"))
                .withPassenger(resultSet.getString("id_user"))
                .withCar(resultSet.getString("id_car"))
                .withSale(resultSet.getString("id_sale"))
                .withDateTime(resultSet.getString("order_date"))
                .withAddressFrom(resultSet.getString("id_address_from"))
                .withAddressTo(resultSet.getString("id_address_to"))
                .withTariff(resultSet.getString("id_tariff"))
                .withOrderStatus(OrderStatus.values()[resultSet.getInt("type") - 1])
                .build();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderEntity entity) throws SQLException {
        statement.setString(1, entity.getPassenger());
        statement.setString(2, entity.getCar());
        statement.setString(3, entity.getSale());
        statement.setString(4, entity.getDateTime());
        statement.setString(5, entity.getAddressFrom());
        statement.setString(6, entity.getAddressTo());
        statement.setString(7, entity.getTariff());
        statement.setString(8, String.valueOf(entity.getStatus()));

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderEntity entity) throws SQLException {
        prepareStatementForInsert(statement, entity);
        statement.setString(9, entity.getId());
    }

    @Override
    public List<OrderEntity> findByPassenger(String userId) {
        return null;
    }


    @Override
    public List<OrderEntity> findAll(int page, int itemsPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<OrderEntity> orders = new ArrayList<>();
                while (resultSet.next()) {
                    final OrderEntity optionalOrder = mapResultSetToEntity(resultSet);
                    orders.add(optionalOrder);
                }
                if (resultSet.next()) {
                    return orders;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Can't execute query [%s]", e));
            throw new DataBaseSqlRuntimeException("", e);

        }
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return super.count(COUNT_QUERY);
    }
}
