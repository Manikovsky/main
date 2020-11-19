package com.company.service;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс - фабрика, осуществляющий упраление соединениями к Базе данных
 */
public class ConnectionFactory {

    /** Путь к базе данных*/
    public static final String DB_URL = "jdbc:h2:C:\\Users\\Ivan\\IdeaProjects\\sber\\SQLDemo\\db\\database";
    //public static final String DB_URL = "jdbc:h2:/home/hackerman/java/SQLDemo/db/dataBase";
    //public static final String DB_Driver = "org.h2.Driver";

    /** Пул соединений к БД */
    static JdbcConnectionPool cp = JdbcConnectionPool.create(DB_URL,"sa", "sa");

    /**
     * Функция получения соединения к БД
     * @return Connection - соединение к БД
     */
    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = cp.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
