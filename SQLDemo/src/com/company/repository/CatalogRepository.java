package com.company.repository;

import com.company.model.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CatalogRepository extends BaseRepository {

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Catalog" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_name VARCHAR(20) NOT NULL)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Catalog catalog) {
        String request = String.format("INSERT INTO Catalog " +
                "(catalog_name) VALUES ('%s')", catalog.getTableName());
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRecord(Catalog catalog) {
        String request = String.format("DELETE FROM Catalog WHERE " +
                "catalog_name = '%s'", catalog.getTableName());
        super.executeSqlStatement(request);
    }

    @Override
    public void changeRecord(Catalog catalog) {
        String request = String.format("UPDATE Catalog SET " +
                "catalog_name = '%s' WHERE id = %s", catalog.getTableName(),
                catalog.getTableId());
        super.executeSqlStatement(request);
    }

    @Override
    public Catalog findRecord(Catalog catalog) {
        String request = String.format("SELECT * FROM Catalog WHERE catalog_name = '%s'", catalog.getTableName());
        Catalog CatalogWithId = new Catalog(catalog.getTableName(), -1, null);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                CatalogWithId.setTableId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CatalogWithId;
    }
}
