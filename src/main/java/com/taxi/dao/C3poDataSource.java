package com.taxi.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.taxi.dao.exception.DataBaseSqlRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class C3poDataSource {

    private static final Logger LOGGER = LogManager.getLogger(C3poDataSource.class);
    private static final ComboPooledDataSource COMBO_POOLED_DATA_SOURCE = new ComboPooledDataSource();
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";


    public C3poDataSource(String filename) throws PropertyVetoException {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        COMBO_POOLED_DATA_SOURCE.setDriverClass(resource.getString(DRIVER));
        COMBO_POOLED_DATA_SOURCE.setJdbcUrl(resource.getString(URL));
        COMBO_POOLED_DATA_SOURCE.setUser(resource.getString(USER));
        COMBO_POOLED_DATA_SOURCE.setPassword(resource.getString(PASSWORD));

    }


    public static Connection getConnection() {
        try {
            return COMBO_POOLED_DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection failed", e);
            throw new DataBaseSqlRuntimeException("Connection failed", e);
        }
    }

    public void shutdown() {
        COMBO_POOLED_DATA_SOURCE.close();
    }
}
