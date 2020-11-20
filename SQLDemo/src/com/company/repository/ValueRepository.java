package com.company.repository;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с данными справочника
 */
public class ValueRepository extends BaseRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Value" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "column_reference BIGINT REFERENCES Column (id) ON DELETE CASCADE, " +
                "data VARCHAR(40) NOT NULL," +
                "row_reference BIGINT REFERENCES Rows (id) ON DELETE CASCADE)";
        super.executeSqlStatement(request);
    }

    /**
     * Функция добавления данных справочника
     * @param catalog - модель справочника
     */
    @Override
    public void addRecord(Catalog catalog) {
        List<Row> rowList = catalog.getRows();
        for(Row row : rowList) {
            List<Column> columnsSettings = row.getColumns();
            for(Column column : columnsSettings) {
                String request = String.format("INSERT INTO Value (column_reference, data, row_reference) " +
                                "VALUES (%d, '%s', %d)", column.getColumnId(), column.getColumnData(), row.getId());
                super.executeSqlStatement(request);
            }
        }
    }

    /**
     * Функция удаления данных справочника
     * @param catalog - модель справочника
     */
    @Override
    public void deleteRecord(Catalog catalog) {
        List<Row> rowList = catalog.getRows();
        for(Row row : rowList) {
            String request = String.format("DELETE FROM Value WHERE row_reference = %d", row.getId());
            super.executeSqlStatement(request);
        }
    }

    @Override
    public void changeRecord(Catalog catalog) {
        throw new UnsupportedOperationException("Value has not this operation");
    }

    /**
     * Функция поиска данных справочника по номерам строк
     * @param catalog - модель справочника
     * @return Catalog - искомая модель справочника
     */
    @Override
    public Catalog findRecord(Catalog catalog) {
        List<Row> rowsResult = new ArrayList<>();
        for(Row currentRow : catalog.getRows()) {
            List<Column> columnsResult = new ArrayList<>();
            String request = String.format("SELECT Column.id, Column.column_name, Column.column_type, " +
                    "Value.data FROM Value JOIN Column ON Column.id = Value.column_reference " +
                    "WHERE row_reference = %d", currentRow.getId());
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(request)) {
                while (resultSet.next()) {
                    columnsResult.add(new Column(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rowsResult.add(new Row(currentRow.getId(), columnsResult));
        }
        return new Catalog(catalog.getTableName(), catalog.getTableId(), rowsResult);
    }
}
