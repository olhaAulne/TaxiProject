package com.taxi.dao.impl;

import com.taxi.dao.CrudDao;
import com.taxi.dao.HikariConnection;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import com.taxi.entity.OrderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;


public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractCrudDaoImpl.class);
    protected final HikariConnection connector;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String saveQuery;
    private final String updateQuery;

    public AbstractCrudDaoImpl(HikariConnection connector, String findByIdQuery, String findAllQuery, String saveQuery, String updateQuery) {
        this.connector = connector;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, E entity) throws SQLException;


    protected static final BiConsumer<PreparedStatement, String> STRING_PARAM_SETTER = (preparedStatement, str) -> {
        try {
            preparedStatement.setString(1, str);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseSqlRuntimeException(e.getMessage(), e);
        }
    };

    protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findByParam)) {

            designatedParamSetter.accept(preparedStatement, param);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Search failed", e);
            throw new DataBaseSqlRuntimeException("Search failed", e);
        }

        return Optional.empty();
    }

    @Override
    public void save(E entity) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(saveQuery)) {
            prepareStatementForInsert(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Insert failed", e);
            throw new DataBaseSqlRuntimeException("Insert failed", e);
        }
    }

    @Override
    public Optional findById(String id) {
        return findByParam(id, findByIdQuery, STRING_PARAM_SETTER);
    }

    @Override
    public List findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Search failed", e);
            throw new DataBaseSqlRuntimeException("Search failed", e);
        }
    }

    @Override
    public void updateById(E entity) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(updateQuery)) {
            prepareStatementForUpdate(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Update failed", e);
            throw new DataBaseSqlRuntimeException("Update failed", e);
        }

    }

    @Override
    public void deleteById(String id) {
        LOGGER.warn("Method don`t allowed");
        throw new UnsupportedOperationException();
    }

    public long count(String query) {
        try (final PreparedStatement preparedStatement = connector.getConnection().prepareStatement(query)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Can't execute query [%s]", e));
            throw new DataBaseSqlRuntimeException("", e);
        }
        return 0;
    }
}
