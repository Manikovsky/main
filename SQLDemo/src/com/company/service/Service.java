package com.company.service;

import com.company.repository.HandBooks;
import java.sql.*;

public class Service {
    public static final String DB_URL = "jdbc:h2:/home/hackerman/java/SQLDemo/db/stockExchange";
    public static final String DB_Driver = "org.h2.Driver";

    HandBooks handBooks;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public Service() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        handBooks = new HandBooks();
    }

    public void createTablesAndForeignKeys() throws SQLException {
        shares.createTable();
        shareRates.createTable();
        traiders.createTable();
        traiderShareActions.createTable();
        // Создание внешних ключей (связt` между таблицами)
        shareRates.createForeignKeys();
        traiderShareActions.createForeignKeys();
    }


    public static void main(String[] args) {
        try{
            StockExchangeDB stockExchangeDB = new StockExchangeDB();
            stockExchangeDB.createTablesAndForeignKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
