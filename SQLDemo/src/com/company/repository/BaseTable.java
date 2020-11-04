package com.company.repository;

import com.company.service.Service;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements Closeable {
    protected String tableName;
    protected Connection connection;

    BaseTable(String tableName) {
        this.tableName = tableName;
        this.connection = Service.getConnection();
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
    void executeSqlStatement(String sql) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = Service.getConnection();
        }
    }
}
