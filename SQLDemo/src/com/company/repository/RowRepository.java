package com.company.repository;

import java.sql.Connection;
import java.util.Map;

public class RowRepository extends BaseRepository{

    public RowRepository(String name, Connection connection) {
        super(name, connection);
    }

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Rows" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_reference BIGINT REFERENCES Catalog (id) ON DELETE CASCADE)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Map<String, String> data) {
        String request = String.format("INSERT INTO Rows " +
                        "(catalog_reference) " +
                        "VALUES (%s)", data.get("catalog_reference"));
        super.executeSqlStatement(request);
    }

    @Override
    public void deleteRecord(Map<String, String> data) {
        String request = String.format("DELETE FROM Rows WHERE " +
                "id = %s", data.get("id"));
    }

    @Override
    public void changeRecord(Map<String, String> data) {
        throw new UnsupportedOperationException("Для данной таблицы нет такой операции");
    }

    @Override
    public void findRecord(Map<String, String> data) {

    }
}
