package com.company.repository;

import com.company.model.HandbookRegister;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HandbookDAO extends TableDAO {
    public HandbookDAO() {
        super("Handbooks");
    }

    @Override
    public void createTable() {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Handbook(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "first_name VARCHAR(20) NOT NULL," +
                "last_name VARCHAR(20) NOT NULL,"+
                "number VARCHAR(20) NOT NULL)");
    }

    @Override
    public void insert(HandbookRegister register) {
        String request = String.format("INSERT INTO Handbook " +
                "(first_name, last_name, number) VALUES ('%s', '%s', '%s')",
                register.getFirstName(), register.getLastName(), register.getNumberPhone());
        super.executeSqlStatement(request);
    }

    @Override
    public List<HandbookRegister> select(HandbookRegister register) {
        reopenConnection();
        String firstName = register.getFirstName();
        String lastName = register.getLastName();
        long id = register.getId();
        String request = "";
        List<HandbookRegister> list = new ArrayList<>();
        if (id != 0 & firstName == null & lastName == null)
            request = String.format("SELECT * FROM Handbook WHERE id = %d",id);
        else if (id == 0 & firstName != null & lastName == null)
            request = String.format("SELECT * FROM Handbook WHERE first_name = '%s'",firstName);
        else if (id == 0 & firstName == null & lastName != null)
            request = String.format("SELECT * FROM Handbook WHERE last_name = '%s'",lastName);
        else if (id == 0 & firstName != null & lastName != null)
            request = String.format("SELECT * FROM Handbook WHERE first_name = '%s' " +
                    "AND last_name = '%s'", firstName, lastName);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()){
                HandbookRegister temp = new HandbookRegister(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(HandbookRegister register) {
        String request = String.format("UPDATE Handbook SET " +
                "first_name = '%s', last_name = '%s', number = '%s' WHERE id = %d",
                register.getFirstName(), register.getLastName(), register.getNumberPhone(), register.getId());
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRegister(HandbookRegister register) {
        String request = String.format("DELETE FROM Handbook WHERE id = %d", register.getId());
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteTable() {
        super.executeSqlStatement("DROP TABLE Handbook");
    }

}

