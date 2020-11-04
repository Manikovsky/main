package com.company.repository;

import com.company.model.HandbookRegister;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HandBooks extends BaseTable implements TableOperations {
    public HandBooks() {
        super("Handbooks");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Handbook(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(20) NOT NULL," +
                "last_name VARCHAR(20) NOT NULL,"+
                "number VARCHAR(20) NOT NULL)");
    }

    @Override
    public void insert(String firstName, String lastName, String number) {
        try(PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO Handbook " +
                            "(first_name, last_name, number) VALUES (?, ?, ?)")) {
            reopenConnection();
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, number);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HandbookRegister> select(long id, String firstName, String lastName) {
        String request = "";
        if(id == 0 & firstName!=null & lastName!=null) request = "SELECT * FROM Handbook WHERE first_name = ? " +
                "AND last_name = ?";
        else if(id!=0) request = "SELECT * FROM Handbook WHERE id = ?";
        else if(firstName!=null&lastName==null) request = "SELECT * FROM Handbook WHERE first_name = ?";
        else if(lastName!=null&firstName==null) request = "SELECT * FROM Handbook WHERE last_name = ?";
        tryStatement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM  Handbook");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+" "+ resultSet.getString(2) + resultSet.getString(3));
        }
        resultSet.close();
        statement.close();
    }


}

