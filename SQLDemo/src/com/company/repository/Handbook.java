package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Handbook extends BaseTable implements OperationsDAO {

    /*Map<String, String> columnsSettings - параметр с насртройками полей таблицы,
    где ключ - наименование поля, а заначение - тип поля*/
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

    /*Map<String, String> columnsSettings - параметр с насртройками полей таблицы,
    где ключ - наименование поля, а заначение - тип поля;
    Map<String, String> columnsData - параметр с данными для каждого поля таблицы,
    где ключ - наименование поля, а значение - данные для этого поля*/
    @Override
    public void insert(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder requestStart = new StringBuilder(String.format("INSERT INTO %s (", tableName));
        StringBuilder requestEnd = new StringBuilder(" VALUES (");
        if(columnsData != null && columnsData.size() > 0) { // при не выполнении условия  - создание пустой строки
            for(Map.Entry<String, String> pair : columnsData.entrySet()) {
                requestStart.append(pair.getKey()).append(", ");
                if(columnsSettings.get(pair.getKey()).contains("VARCHAR"))
                    requestEnd.append(String.format("'%s', ",pair.getValue()));
                else requestEnd.append(String.format("%s, ",pair.getValue()));
            }
            requestStart.replace(requestStart.length()-2, requestStart.length(),"");
            requestEnd.replace(requestEnd.length()-2, requestEnd.length(), "");
        }
        requestStart.append(")");
        requestEnd.append(")");
        requestStart.append(requestEnd.toString());
        super.executeSqlStatement(requestStart.toString());
    }

    /*Возвращаемое значение типа Map<Integer, HashMap<String, String>> содержит набор полученных результатов где
    * в качестве ключа id  каждой уникальной записи, а значение типа HashMap<String, String> содрежит
    * данными для каждого поля таблицы, где ключ - наименование поля, а значение - данные для этого поля */
    @Override
    public Map<Integer, HashMap<String, String>>
    select(String tableName, Map<String, String> settingsSearch, Map<String, String> columnsSettings) {
        reopenConnection();
        Map<Integer, HashMap<String, String>> resMap = new HashMap<>();
        StringBuilder request = new StringBuilder(String.format("SELECT * FROM %s", tableName));
        if(settingsSearch != null) { //при не выполнении условия будут добавлены все записи
            request.append(" WHERE ");
            for(Map.Entry<String, String> pair : settingsSearch.entrySet()) {
                if(columnsSettings.get(pair.getKey()).contains("VARCHAR"))
                    request.append(String.format("%s = '%s' AND ", pair.getKey(), pair.getValue()));
                else request.append(String.format("%s = %s AND ", pair.getKey(), pair.getValue()));
            }
            request.replace(request.length()-4, request.length(),"");
        }
        System.out.println(request.toString());
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request.toString())) {
            while (resultSet.next()) {
                HashMap<String, String> innerMap = new LinkedHashMap<>();
                for(String str : columnsSettings.keySet()) {
                    int columnIndex = resultSet.findColumn(str); // получение индека для осуществления запроса к
                    if(columnIndex!=1) innerMap.put(str,resultSet.getString(columnIndex)); //  требуему полю таблицы
                }
                resMap.put(resultSet.getInt(1),innerMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /*Map<String, String> columnsSettings - параметр с насртройками полей таблицы,
    где ключ - наименование поля, а заначение - тип поля;
    Map<String, String> columnsData - параметр с данными для каждого поля таблицы,
    где ключ - наименование поля, а значение - данные для этого поля*/
    @Override
    public void update(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("UPDATE %s SET ", tableName));
        for(Map.Entry<String, String> pair : columnsData.entrySet()) {
            if(!pair.getKey().equals("id")) {
                if(columnsSettings.get(pair.getKey()).contains("VARCHAR"))
                    request.append(String.format("%s = '%s', ",pair.getKey(), pair.getValue()));
                else request.append(String.format("%s = %s, ",pair.getKey(), pair.getValue()));
            }
        }
        request.replace(request.length()-2, request.length(), "");
        request.append(String.format(" WHERE id = %s",columnsData.get("id")));
        super.executeSqlStatement(request.toString());
    }

    /*Map<String, String> columnsSettings - параметр с насртройками полей таблицы,
    где ключ - наименование поля, а заначение - тип поля;
    Map<String, String> columnsData - параметр с данными для каждого поля таблицы,
    где ключ - наименование поля, а значение - данные для этого поля*/
    @Override
    public void deleteRegister(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("DELETE FROM %s WHERE ", tableName));
           for(Map.Entry<String, String> pair : columnsData.entrySet()) {
               if("id".equals(pair.getKey()) || !columnsSettings.get(pair.getKey()).contains("VARCHAR"))
                   request.append(String.format("%s = %s AND ",pair.getKey(), pair.getValue()));
               else  request.append(String.format("%s = '%s' AND ",pair.getKey(), pair.getValue()));
           }
           request.replace(request.length()-4, request.length(),"");
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteTable(String tableName) {
        super.executeSqlStatement("DROP TABLE "+tableName);
    }

}

