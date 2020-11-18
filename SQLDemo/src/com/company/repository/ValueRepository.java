package com.company.repository;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValueRepository extends BaseRepository {

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
    public void addRecord(Catalog catalog) {
        List<Row> rowList = catalog.getRows();
        for(Row row : rowList) {
            List<Column> columnList = row.getColumns();
            for(Column column : columnList) {
                String request = String.format("INSERT INTO Value " +
                                "(column_reference, data, row_reference) " +
                                "VALUES (%d, '%s', %d)", column.getColumnId(),
                        column.getColumnData(), row.getId());
                super.executeSqlStatement(request);
            }
        }
    }

    @Override
    public void deleteRecord(Catalog catalog) {
        List<Row> rowList = catalog.getRows();
        for(Row row : rowList) {
            String request = String.format("DELETE FROM Value " +
                            " WHERE row_reference = %d", row.getId());
            super.executeSqlStatement(request);
        }
    }

    @Override
    public void changeRecord(Catalog catalog) {

    }

    @Override
    public Catalog findRecord(Catalog catalog) {
        List<Row> rowsResult = new ArrayList<>();
        for(Row currentRow : catalog.getRows()) {
            String request = String.format("SELECT Column.id, Column.column_name, Column.column_type, " +
                    "Value.data FROM Value JOIN Column " +
                    "ON Column.id = Value.column_reference " +
                    "WHERE row_reference = %d", currentRow.getId());
            System.out.println(request);
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(request)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1)+" " +resultSet.getString(2)+
                            " "+ resultSet.getString(3)+ " " + resultSet.getString(4));
                    List<Column> columnList = new ArrayList<>();
                    columnList.add(new Column(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4)));
                    rowsResult.add(new Row(currentRow.getId(), columnList));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
        return new Catalog(catalog.getTableName(), catalog.getTableId(), rowsResult);
    }
}
