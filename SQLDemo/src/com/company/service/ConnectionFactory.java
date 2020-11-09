package com.company.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static final String DB_URL = "jdbc:h2:C:\\Users\\Ivan\\IdeaProjects\\sber\\SQLDemo\\db\\database";
    //public static final String DB_URL = "jdbc:h2:/home/hackerman/java/SQLDemo/db/dataBase";
    public static final String DB_Driver = "org.h2.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
