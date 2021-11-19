package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final Logger log = Logger.getLogger(Util.class.getName());
    public static final boolean FLAG_USE_MYSQL = false;
    public static final String SQL_CMD_CREATE_TABLE;
    private static final String DB_DRIVER_NAME;
    private static final String DB_URL;
    private static final String DB_USER_NAME;
    private static final String DB_USER_PASSWORD;

    static {
        if(FLAG_USE_MYSQL) {
            DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
            DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
            DB_USER_NAME = "root";
            DB_USER_PASSWORD = "rootroot";
            SQL_CMD_CREATE_TABLE = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(45) NOT NULL,lastName VARCHAR(45) NOT NULL,age INT(3) NOT NULL,PRIMARY KEY (id), UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);";
        } else {
            DB_DRIVER_NAME = "org.postgresql.Driver";
            DB_URL = "jdbc:postgresql://localhost:5432/mydbtest";
            DB_USER_NAME = "admin";
            DB_USER_PASSWORD = "12345678";
            SQL_CMD_CREATE_TABLE = "CREATE TABLE users (id SERIAL PRIMARY KEY, name CHARACTER VARYING(30) NOT NULL, lastName CHARACTER VARYING(30) NOT NULL, Age INTEGER NOT NULL);";
        }
    }

    private Util() {

    }

    public static Connection getConnectionJDBC() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER_NAME);
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnectionJDBC(Connection connection) {
        try {
            connection.close();
        } catch (SQLException | NullPointerException e) {
            log.warning("При работе closeConnectionJDBC возникла ошибка " + e);
        }
    }
}
