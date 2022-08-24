package com.my.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDB {

    private static final String USER = "root";
    private static final String PASSWORD = "qwertyuiopOblt1>?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tax_data";

    private static final String URL = DB_URL +
            "?user=" + USER +
            "&password=" + PASSWORD +
            "&useSSL=false" +
            "&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";

    private static ManagerDB instance;

    private ManagerDB() {
    }

    synchronized public static ManagerDB getInstance() {
        if (instance == null) {
            instance = new ManagerDB();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
