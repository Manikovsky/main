package com.company;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static final String DB_URL = "jdbc:h2:/home/hackerman/java/SQLDemo/db/dataBase";
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
    public static void create(String tableName) throws SQLException {
        String request = "CREATE TABLE IF NOT EXISTS Handbook(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(20) NULL," +
                "last_name VARCHAR(20) NULL,"+
                "number VARCHAR(20) NULL)";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(request);
        request = "ALTER TABLE Handbook RENAME TO Book";
        statement.execute(request);
        request = String.format("INSERT INTO Book " +
                        "(first_name, last_name, number) VALUES ('%s', '%s', '%s')",
                "2", "3", "4");
        statement.execute(request);
        request = String.format("SELECT * FROM Book", tableName);
        ResultSet resultSet = statement.executeQuery(request);
        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + resultSet.getString(2) +
                    resultSet.getString(3)+ resultSet.getString(4));
        }
        request = String.format("ALTER TABLE Book ADD COLUMN name VARCHAR(20) NULL AFTER first_name",
                tableName);
        statement.execute(request);
        request = String.format("INSERT INTO Book " +
                        "(first_name,name, number ) VALUES ('%s', '%s', '%s')",
                "2", "name", "5");
        statement.execute(request);
        request = String.format("SELECT * FROM Book", tableName);
        resultSet = statement.executeQuery(request);
        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + resultSet.getString(2) +
                    resultSet.getString(3)+ resultSet.getString(4) + resultSet.getString(5));
        }
        request = "SELECT COUNT(*) " +
                "FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'Book'";
        resultSet = statement.executeQuery(request);
        while(resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

    }
    public static void main(String[] args) throws SQLException {
       // create("Handbook");
       /* Map<String, String> map = new HashMap<>();
        map.put("first_name", "Ivan");
        map.put("last_name", "Leonov");
        insert("Handbook", map);*/
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1","1");
        map.put("2", "2");
        map.put("3", "2");
    }
    public static void insert(String tableName, Map<String, String> map) {
        StringBuilder request = new StringBuilder(String.format("DELETE FROM %s WHERE ", tableName));
        for(Map.Entry<String, String> pair : map.entrySet()) {
            if("id".equals(pair.getKey())) request.append(String.format("%s = %s AND ",pair.getKey(), pair.getValue()));
            else request.append(String.format("%s = '%s' AND ",pair.getKey(), pair.getValue()));
        }
        request.replace(request.length()-4, request.length(),"");
        System.out.println(request.toString());
}}
