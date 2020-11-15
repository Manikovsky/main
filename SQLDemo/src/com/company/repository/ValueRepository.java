package com.company.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ValueRepository extends BaseRepository {

    public ValueRepository(String name, Connection connection) {
        super(name, connection);
    }

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Value" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "column_reference BIGINT REFERENCES Column (id) ON DELETE CASCADE, " +
                "data VARCHAR(40) NOT NULL," +
                "row_reference BIGINT REFERENCES Rows (id) ON DELETE CASCADE)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Map<String, String> data) {
        String request = String.format("INSERT INTO Value " +
                        "(column_reference, data, row_reference) " +
                        "VALUES (%s, '%s', '%s')", data.get("column_reference"),
                data.get("data"), data.get("row_reference"));
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRecord(Map<String, String> data) {
        String request= String.format("DELETE FROM Value WHERE column_reference = %s " +
                "AND row_reference = %s", data.get("column_reference"), data.get("row_reference"));
        super.executeSqlStatement(request);
    }

    @Override
    public void changeRecord(Map<String, String> data) {
        String request = String.format("UPDATE Column Value " +
                        "data = '%s' WHERE column_reference = %s AND row_reference = %s",
                data.get("data"), data.get("column_reference"), data.get("row_reference"));

        super.executeSqlStatement(request);
    }

    @Override
    public void findRecord(Map<String, String> data) {
        String request = "SELECT * FROM Value";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+" " +resultSet.getString(2)+ " "+
                        resultSet.getString(3)+ " " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
