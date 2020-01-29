package com.taxi.dao.impl;

import com.taxi.dao.ConnectorDB;
import com.taxi.dao.OrderDao;
import com.taxi.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends AbstractCrudDaoImpl<Order> implements OrderDao {

    private static final String FIND_BY_DATE_QUERY = "SELECT * FROM order WHERE date=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM order WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM order";
    private static final String SAVE_QUERY = "INSERT INTO order (order_status, order_date, id_user, id_sale, " +
            "id_tariff, car_number, id_address ) values(?, ?, ?,? ,?,? ,?)";
    private static final String UPDATE_QUERY = "UPDATE order SET order_status = ?, order_date = ?, id_user = ?, " +
            "id_sale = ?, id_tariff =?, car_number = ?, id_address = ?   WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM order WHERE id = ?";
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public OrderDaoImpl(ConnectorDB connector) {
        super(connector, FIND_BY_ID_QUERY, FIND_ALL_QUERY, SAVE_QUERY, SAVE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    public List<Order> findByDate(Date date) {
        return null;
    }

    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order entity) throws SQLException {

    }
}
