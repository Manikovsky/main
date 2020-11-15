package com.company.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseRepository {
    private String name;
    protected Connection connection;

    public BaseRepository(String name, Connection connection) {
        this.name = name;
        this.connection = connection;
    }

    public abstract void initializationTable();
    public abstract void addRecord(Map<String, String> data);
    public abstract void deleteRecord(Map<String, String> data);
    public abstract void changeRecord(Map<String, String> data);
    public abstract void findRecord(Map<String, String> data);

    protected void executeSqlStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
