package com.taxi.dao.impl;

import com.taxi.dao.ConnectorDB;
import com.taxi.dao.CrudDao;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDaoImpl<E> implements CrudDao<E> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractCrudDaoImpl.class);
    protected final ConnectorDB connector;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String saveQuery;
    private final String updateQuery;
    private final String deleteQuery;

    public AbstractCrudDaoImpl(ConnectorDB connector, String findByIdQuery, String findAllQuery, String saveQuery, String updateQuery, String deleteQuery) {
        this.connector = connector;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
        this.deleteQuery = deleteQuery;
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
    public long count() {
        return 0;
    }

    @Override
    public void update(E entity) {
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
        Optional<E> entity = findById(id);
        if (entity.isPresent()) {
            try (final PreparedStatement preparedStatement =
                         connector.getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error("Delete failed",e);
                throw new DataBaseSqlRuntimeException("Delete failed",e);
            }

        }
    }
}
