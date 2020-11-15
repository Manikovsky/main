package com.company.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ColumnRepository extends BaseRepository {
    public ColumnRepository(String name, Connection connection) {
        super(name, connection);
    }

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Column" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_ref BIGINT REFERENCES Catalog (id) ON DELETE CASCADE, " +
                "column_name VARCHAR(20) NOT NULL," +
                "column_type VARCHAR(20) NOT NULL)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Map<String, String> data) {
        String request = String.format("INSERT INTO Column " +
                "(catalog_ref, column_name, column_type) " +
                "VALUES (%s, '%s', '%s')", data.get("catalog_ref"),
                data.get("column_name"), data.get("column_type"));
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRecord(Map<String, String> data) {
        StringBuilder request = new StringBuilder("DELETE FROM Column WHERE ");
        for(Map.Entry<String, String> pair : data.entrySet()) {
            String columnName = pair.getKey();
            String value = pair.getValue();
            if("catalog_ref".equals(columnName))
                request.append(String.format("%s = %s AND ", columnName, value));
            else  request.append(String.format("%s = '%s' AND ", columnName, value));
        }
        request.replace(request.length()-4, request.length(),"");
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void changeRecord(Map<String, String> data) {
        String request = String.format("UPDATE Column SET " +
                "column_name = '%s', column_type = '%s' WHERE " +
                "catalog_ref = %s AND column_name = '%s'",
                data.get("new_column_name"), data.get("new_column_type"),
                data.get("catalog_ref"), data.get("column_name"));

        super.executeSqlStatement(request);
    }

    @Override
    public void findRecord(Map<String, String> data) {
        String request = "SELECT * FROM Column";
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
