package com.company.repository;

import java.sql.SQLException;

public interface TableOperations {
    public void createTable() throws SQLException;
    public void insert() throws SQLException;
    public void select() throws SQLException;
}
