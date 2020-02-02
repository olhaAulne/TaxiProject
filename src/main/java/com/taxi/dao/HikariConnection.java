package com.taxi.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HikariConnection {

    private static final Logger LOGGER = LogManager.getLogger(HikariConnection.class);

    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.pool.size";
    private static final String TIMEOUT = "db.timeout";

    private static final HikariConfig config = new HikariConfig();
    private HikariDataSource ds;

    public HikariConnection(String filename) {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        config.setDriverClassName(resource.getString(DRIVER));
        config.setJdbcUrl(resource.getString(URL));
        config.setUsername(resource.getString(USER));
        config.setPassword(resource.getString(PASSWORD));
        config.setMaximumPoolSize(Integer.parseInt(resource.getString(POOL_SIZE)));
        config.setConnectionTimeout(Integer.parseInt(resource.getString(TIMEOUT)));
        this.ds = new HikariDataSource(config);

    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error(String.format("Connection failed %s", e.getMessage()));
            throw new IllegalStateException(String.format("Connection failed %s", ""), e);
        }
    }

    public void shutdown() {
        ds.close();
    }
}
