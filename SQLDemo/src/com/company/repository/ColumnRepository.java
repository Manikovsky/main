package com.company.repository;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColumnRepository extends BaseRepository {

    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Column" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_reference BIGINT REFERENCES Catalog (id) ON DELETE CASCADE, " +
                "column_name VARCHAR(20) NOT NULL," +
                "column_type VARCHAR(20) NOT NULL)";
        super.executeSqlStatement(request);
    }

    @Override
    public void addRecord(Catalog catalog) {
        List<Column> columnsSettings = catalog.getRows().get(0).getColumns();
        for(Column column : columnsSettings) {
            String request = String.format("INSERT INTO Column " +
                            "(catalog_reference, column_name, column_type) " +
                            "VALUES (%d, '%s', '%s')", catalog.getTableId(),
                    column.getColumnName(), column.getColumnType());
            super.executeSqlStatement(request);
        }
    }

    @Override
    public void deleteRecord(Catalog catalog) {
        List<Column> columnsSettings = catalog.getRows().get(0).getColumns();
        String request = String.format("DELETE FROM Column WHERE " +
                "catalog_reference = %d AND column_name = '%s'", catalog.getTableId(),
                columnsSettings.get(0).getColumnName());
        columnsSettings.remove(0);
        super.executeSqlStatement(request);
    }

    @Override
    public void changeRecord(Catalog catalog) {
        deleteRecord(catalog);
        addRecord(catalog);
    }

    @Override
    public Catalog findRecord(Catalog catalog) {
        List<Column> columnsList = new ArrayList<>();
        List<Row> rowList =  new ArrayList<>();
        rowList.add(new Row(-1,columnsList));
        String request = String.format("SELECT * FROM Column WHERE catalog_reference = %d", catalog.getTableId());
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request)) {
            while (resultSet.next()) {
                columnsList.add(new Column(resultSet.getLong(1), resultSet.getString(3),
                        resultSet.getString(4), null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Catalog(catalog.getTableName(), catalog.getTableId(), rowList);
    }
}
