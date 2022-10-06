package com.my.persistence.db;

import com.my.annotations.Value;
import com.my.exception.ManagerDBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ManagerDB {

    private final static Logger log = Logger.getLogger(ManagerDB.class);

    @Value(param = "db.driver")
    private String driver;

    @Value(param = "db.url")
    private String url;

    @Value(param = "db.user")
    private  String user;

    @Value(param = "db.password")
    private String password;


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

            //log.info("url = " + url + ", driver = " + driver + ", user" + user);

            /*Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if(con == null){
                throw new ManagerDBException("Unable to get connection to the server");
            }*/
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
