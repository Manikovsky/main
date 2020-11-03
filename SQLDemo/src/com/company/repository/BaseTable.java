package com.company.repository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;

public class BaseTable implements Closeable {
    private String tableName;
    private Connection connection;

    @Override
    public void close() throws IOException {

    }
}
