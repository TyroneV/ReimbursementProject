package com.web.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionUtilTest {

    @Test
    void getConnection() throws SQLException {
        assertTrue(ConnectionUtil.getConnection() != null,"Connection Successful");
    }
}