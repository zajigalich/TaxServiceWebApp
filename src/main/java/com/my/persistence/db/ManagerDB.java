package com.my.persistence.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDB {

    private final static Logger log = Logger.getLogger(ManagerDB.class);

    private static final String USER = "root";
    private static final String PASSWORD = "qwertyuiopOblt1>?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tax_data";

    private static final String URL = DB_URL + "?autoReconnect=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";



    //объект соединения - всего один
    private static ManagerDB instance;

    private ManagerDB() {
    }

    synchronized public static ManagerDB getInstance() {
        if (instance == null) {
            instance = new ManagerDB();
        }
        return instance;
    }


    //конекшн не один
    public Connection getConnection() throws SQLException {

        Connection con = null;
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            log.error("Driver not found");
            //throw new ClassNotFoundException(e);
            e.printStackTrace();
        } catch (SQLException e) {
            log.fatal("Unable to get connection");
            //throw new RuntimeException(e);
            throw new SQLException(e);
        }

        return con;
    }

    @Override
    public String toString() {
        return "ManagerDB";
    }
}
