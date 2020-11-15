package com.company.repository;

import com.company.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CatalogRepository extends BaseRepository {
    
    public CatalogRepository(String name, Connection connection) {
        super(name, connection);
    }

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Catalog" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_name VARCHAR(20) NOT NULL)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Map<String, String> data) {
        String request = String.format("INSERT INTO Catalog " +
                "(catalog_name) VALUES ('%s')", data.get("catalog_name"));
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRecord(Map<String, String> data) {
        String request = String.format("DELETE FROM Catalog WHERE " +
                "catalog_name = '%s'", data.get("catalog_name"));
        super.executeSqlStatement(request);
    }

    @Override
    public void changeRecord(Map<String, String> data) {
        String request = String.format("UPDATE Catalog SET " +
                "catalog_name = '%s' WHERE catalog_name = '%s'", data.get("new_catalog_name"),
                data.get("catalog_name"));
        System.out.println(request);
        super.executeSqlStatement(request);
    }

    @Override
    public void findRecord(Map<String, String> data) {
        String request = "SELECT * FROM Catalog";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+" " +resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CatalogRepository repository = new CatalogRepository("Catalog", ConnectionFactory.getConnection());
        repository.initializationTable();
        Map<String, String> map = new HashMap<>();
        map.put("catalog_name", "Handbook");
        repository.addRecord(map);
        map.put("catalog_name", "HandbookSecond");
        repository.addRecord(map);
        map.put("new_catalog_name","newHandbook");
        repository.changeRecord(map);
        repository.findRecord(null);
        ColumnRepository columnRepository = new ColumnRepository("Column", ConnectionFactory.getConnection());
        columnRepository.initializationTable();
        map.put("catalog_ref","1");
        map.put("column_name", "ima");
        map.put("column_type", "string");
        columnRepository.addRecord(map);
        map.put("catalog_ref","1");
        map.put("column_name", "familiy");
        map.put("column_type", "string");
        columnRepository.addRecord(map);
        columnRepository.findRecord(null);
        repository.findRecord(null);
        columnRepository.findRecord(null);
        RowRepository rowRepository = new RowRepository("Row", ConnectionFactory.getConnection());
        rowRepository.initializationTable();
        ValueRepository valueRepository = new ValueRepository("Value", ConnectionFactory.getConnection());
        map = new HashMap<>();
        map.put("catalog_reference", "1");
        rowRepository.addRecord(map);
        valueRepository.initializationTable();
        map = new HashMap<>();
        map.put("column_reference", "1");
        map.put("data", "ivan");
        map.put("row_reference", "1");
        valueRepository.addRecord(map);
        map.put("column_reference", "2");
        map.put("data", "manikovsky");
        map.put("row_reference", "1");
        valueRepository.addRecord(map);
        valueRepository.findRecord(null);
        map = new HashMap<>();
        map.put("catalog_name", "Handbook");
        repository.deleteRecord(map);
        valueRepository.findRecord(null);
    }
}
