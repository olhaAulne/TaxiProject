package com.taxi.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HikariConnectionTest {

    private static final String ACTUAL_DATABASE_PROPERTIES_FILENAME = "database";
    private static final String TEST_DATABASE_PROPERTIES_FILENAME = "h2_test_db";
    private static final String EXPECTED_MESSAGE = "Connection failed";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConnectionWithActualDBShouldWork() {
        HikariConnection manager = new HikariConnection(ACTUAL_DATABASE_PROPERTIES_FILENAME);
        assertNotNull(manager);
        assertNotNull(manager.getConnection());
        manager.shutdown();
    }

    @Test
    public void testH2ConnectionShouldWork() {
        HikariConnection manager = new HikariConnection(TEST_DATABASE_PROPERTIES_FILENAME);
        assertNotNull(manager);
        assertNotNull(manager.getConnection());
        manager.shutdown();
    }

    @Test
    public void testConnectionShouldThrowIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage(EXPECTED_MESSAGE);
        HikariConnection manager = new HikariConnection(ACTUAL_DATABASE_PROPERTIES_FILENAME);
        manager.shutdown();
        manager.getConnection();
    }
}