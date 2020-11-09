package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Handbook extends BaseTable implements OperationsDAO {

    @Override
    public void createTable(String tableName, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("CREATE TABLE IF NOT EXISTS %s" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, ", tableName));
        if(columnsSettings != null && columnsSettings.size() > 0) {
            for(Map.Entry<String, String> pair : columnsSettings.entrySet()) {
                request.append(String.format(" %s %s NULL,", pair.getKey(), pair.getValue()));
            }
            request.replace(request.length()-1, request.length(),"");
        }
        request.append(")");
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void addColumn(String tableName, String previousColumn, String name, String type) {
        StringBuilder request = new StringBuilder(String.format("ALTER TABLE %s ADD COLUMN %s %s NULL",
                tableName, name, type));
        if(previousColumn != null) request.append(" AFTER ").append(previousColumn);
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteColumn(String tableName, String columnName) {
        String request = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        super.executeSqlStatement(request);
    }

    public void renameTable(String oldName, String newName) {
        String request = String.format("ALTER TABLE %s RENAME TO %s", oldName, newName);
        super.executeSqlStatement(request);
    }

    @Override
    public void insert(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder requestStart = new StringBuilder(String.format("INSERT INTO %s (", tableName));
        StringBuilder requestEnd = new StringBuilder(" VALUES (");
        if(columnsData != null && columnsData.size() > 0) { // при не выполнении условия  - создание пустой строки
            changeFirstBuilder(requestStart, requestEnd, columnsData, columnsSettings);
            requestEnd.replace(requestEnd.length()-2, requestEnd.length(), "");
        }
        requestStart.append(")");
        requestEnd.append(")");
        requestStart.append(requestEnd.toString());
        super.executeSqlStatement(requestStart.toString());
    }

    /*Возвращаемое значение типа Map<Integer, HashMap<String, String>> содержит набор полученных результатов где
     * в качестве ключа id  каждой уникальной записи, а значение типа HashMap<String, String> содрежит
     * данные для каждого поля таблицы, где ключ - наименование поля, а значение - данные для этого поля */
    @Override
    public Map<Integer, Map<String, String>>
    select(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        reopenConnection();
        Map<Integer, Map<String, String>> result = new LinkedHashMap<>();
        StringBuilder request = new StringBuilder(String.format("SELECT * FROM %s", tableName));
        if(columnsData != null) { //при не выполнении условия будут добавлены все записи
            request.append(" WHERE ");
            changeSecondBuilder(request, columnsData, columnsSettings);
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request.toString())) {
            while (resultSet.next()) {
                HashMap<String, String> innerMap = new LinkedHashMap<>();
                for(String str : columnsSettings.keySet()) {
                    int columnIndex = resultSet.findColumn(str); // получение индека для осуществления запроса к
                    if(columnIndex!=1) innerMap.put(str,resultSet.getString(columnIndex)); //  требуему полю таблицы
                }
                result.put(resultSet.getInt(1),innerMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("UPDATE %s SET ", tableName));
        changeFirstBuilder(request, null, columnsData, columnsSettings);
        request.append(String.format(" WHERE id = %s",columnsData.get("id")));
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteRegister(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("DELETE FROM %s WHERE ", tableName));
        changeSecondBuilder(request, columnsData, columnsSettings);
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteTable(String tableName) {
        super.executeSqlStatement("DROP TABLE "+tableName);
    }

    private void changeSecondBuilder(StringBuilder builder,
                                     Map<String, String> columnsData, Map<String, String> columnsSettings) {
        for(Map.Entry<String, String> pair : columnsData.entrySet()) {
            String columnName = pair.getKey();
            String data = pair.getValue();
            if("id".equals(columnName) || !columnsSettings.get(columnName).contains("VARCHAR"))
                builder.append(String.format("%s = %s AND ", columnName, data));
            else  builder.append(String.format("%s = '%s' AND ", columnName, data));
        }
        builder.replace(builder.length()-4, builder.length(),"");
    }

    private void changeFirstBuilder(StringBuilder builderStart, StringBuilder builderEnd,
                                    Map<String, String> columnsData, Map<String, String> columnsSettings) {
        for(Map.Entry<String, String> pair : columnsData.entrySet()) {
            String columnName = pair.getKey();
            String data = pair.getValue();
            if(builderEnd != null) {
                builderStart.append(columnName).append(", ");
                if(columnsSettings.get(columnName).contains("VARCHAR"))
                    builderEnd.append(String.format("'%s', ",data));
                else builderEnd.append(String.format("%s, ",data));
            }
            else if(!columnName.equals("id")) {
                if(columnsSettings.get(columnName).contains("VARCHAR"))
                    builderStart.append(String.format("%s = '%s', ",columnName, data));
                else builderStart.append(String.format("%s = %s, ",columnName, data));
            }
        }
        builderStart.replace(builderStart.length()-2, builderStart.length(),"");
    }

}

