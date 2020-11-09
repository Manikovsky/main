package com.company.repository;

import com.company.service.Service;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public abstract class BaseTable implements Closeable {
    protected Connection connection;

    abstract public void createTable(String tableName, Map<String, String> columnsSettings);
    abstract public void deleteTable(String tableName);
    abstract public void addColumn(String tableName, String previousColumn, String name, String type);
    abstract public void deleteColumn(String tableName, String columnName);
    abstract public void renameTable(String oldName, String newName);

    BaseTable() {
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

    protected void executeSqlStatement(String sql) {
        reopenConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void reopenConnection() {
        try {
            if (connection == null || connection.isClosed())
                connection = Service.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
