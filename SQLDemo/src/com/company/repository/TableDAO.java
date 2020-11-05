package com.company.repository;

import com.company.model.HandbookRegister;
import com.company.service.Service;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class TableDAO implements Closeable {
    protected String tableName;
    protected Connection connection;

    abstract public void createTable();
    abstract public void insert(HandbookRegister register);
    abstract public List<HandbookRegister> select(HandbookRegister register);
    abstract public void update(HandbookRegister register);
    abstract public void deleteRegister(HandbookRegister register);
    abstract public void deleteTable();

    TableDAO(String tableName) {
        this.tableName = tableName;
        this.connection = Service.getConnection();
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
            e.printStackTrace();
        }
    }
    void executeSqlStatement(String sql){
        reopenConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void reopenConnection() {
        try {
            if (connection == null || connection.isClosed())
                connection = Service.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
