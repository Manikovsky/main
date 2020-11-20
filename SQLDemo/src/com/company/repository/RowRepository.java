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
 * Репозиторий для работы со строками справочника
 */
public class RowRepository extends BaseRepository{

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializationTable() {
        String request = "CREATE TABLE IF NOT EXISTS Rows" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "catalog_reference BIGINT REFERENCES Catalog (id) ON DELETE CASCADE)";
        super.executeSqlStatement(request);
    }

    /**
     * Функция добавления строки справочника по его id
     * @param catalog - модель справочника
     */
    @Override
    public void addRecord(Catalog catalog) {
        String request = String.format("INSERT INTO Rows (catalog_reference) " +
                        "VALUES (%d)", catalog.getTableId());
        super.executeSqlStatement(request);
    }

    /**
     * Функция удаления строки справочника по её id
     * @param catalog - модель справочника
     */
    @Override
    public void deleteRecord(Catalog catalog) {
        List<Row> catalogRows = catalog.getRows();
        for(Row row : catalogRows) {
            String request = String.format("DELETE FROM Rows WHERE " +
                    "id = %d", row.getId());
            super.executeSqlStatement(request);
        }
    }

    @Override
    public void changeRecord(Catalog catalog) {
        throw new UnsupportedOperationException("Rows has not this operation");
    }

    /**
     * Функция поиска id строк
     * @param catalog - модель справочника
     * @return Catalog - искомая модель справочника
     */
    @Override
    public Catalog findRecord(Catalog catalog) {
        List<Row> rowList = new ArrayList<>();
        StringBuilder request;
        if(catalog.getRows() == null)
            request = new StringBuilder("SELECT * FROM Rows ORDER BY id DESC LIMIT 1");
        else request = getStringBuilder(catalog);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request.toString())) {
            while (resultSet.next()) {
                rowList.add(new Row(resultSet.getLong(1), null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Catalog(catalog.getTableName(), catalog.getTableId(), rowList);
    }

    /**
     * Функция формирования SQL запроса
     * @param catalog - модель справочника
     * @return StringBuilder - запрос
     */
    private StringBuilder getStringBuilder(Catalog catalog) {
        List<Column> columnsSettings = catalog.getRows().get(0).getColumns();
        StringBuilder request = new StringBuilder("SELECT * FROM Rows AS Main_Rows WHERE EXISTS ");
        for(Column column : columnsSettings) {
            request.append(String.format("(SELECT * FROM Value where column_reference = %d " +
                            "AND data = '%s' and Main_Rows.id = Value.row_reference) AND EXISTS ",
                    column.getColumnId(), column.getColumnData()));
        }
        request.replace(request.length()-11, request.length(),"");
        return request;
    }

}
