package com.company.repository;

import com.company.model.Catalog;
import com.company.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseRepository {

    protected Connection connection = ConnectionFactory.getConnection();

    public abstract void initializationTable();
    public abstract void addRecord(Catalog catalog);
    public abstract void deleteRecord(Catalog catalog);
    public abstract void changeRecord(Catalog catalog);
    public abstract Catalog findRecord(Catalog catalog);

    protected void executeSqlStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
