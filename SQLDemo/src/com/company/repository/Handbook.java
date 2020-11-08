package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Handbook extends BaseTable implements OperationDAO {

    public Handbook() { }

    @Override
    public void createTable(String tableName, Map<String, String> columnsSettings) {
        StringBuilder request = new StringBuilder(String.format("CREATE TABLE IF NOT EXISTS %s" +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY, ", tableName));
        if(columnsSettings != null && columnsSettings.size() > 0) {
            for(Map.Entry<String, String> pair : columnsSettings.entrySet()) {
                request.append(String.format(" %s %s NULL,", pair.getKey(), pair.getValue()));
            }
            request.replace(request.length()-1,request.length(),"");
        }
        request.append(")");
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void addColumn(String tableName,String previousColumn, String name, String type) {
        StringBuilder request = new StringBuilder(String.format("ALTER TABLE %s ADD COLUMN %s %s NULL",
                tableName, name, type));
        if(previousColumn!=null) request.append(" AFTER ").append(previousColumn);
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteColumn(String tableName, String columnName) {
        String request = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        super.executeSqlStatement(request);
    }

    public void renameTable(String oldName, String newName) {
        String request = String.format("ALTER TABLE %s RENAME TO %s",oldName, newName);
        super.executeSqlStatement(request);
    }

    @Override
    public void insert(String tableName, Map<String, String> columnsSettings) {
        StringBuilder requestStart = new StringBuilder(String.format("INSERT INTO %s (", tableName));
        StringBuilder requestEnd = new StringBuilder(" VALUES (");
        if(columnsSettings != null && columnsSettings.size() > 0) {
            for(Map.Entry<String, String> pair : columnsSettings.entrySet()) {
                requestStart.append(pair.getKey()).append(", ");
                requestEnd.append(String.format("'%s', ",pair.getValue()));
            }
            requestStart.replace(requestStart.length()-2, requestStart.length(),"");
            requestEnd.replace(requestEnd.length()-2, requestEnd.length(), "");
        }
        requestStart.append(")");
        requestEnd.append(")");
        requestStart.append(requestEnd.toString());
        super.executeSqlStatement(requestStart.toString());
    }

    @Override
    public Map<Integer, HashMap<String, String>>
    select(String tableName, Map<String, String> settingsSearch, Map<String, String> columnsSettings) {
        Map<Integer, HashMap<String, String>> resMap = new HashMap<>();
        StringBuilder request = new StringBuilder(String.format("SELECT * FROM %s", tableName));
        if(settingsSearch!=null) {
            request.append(" WHERE ");
            for(Map.Entry<String, String> pair : settingsSearch.entrySet())
                request.append(String.format("%s = '%s' AND ",pair.getKey(), pair.getValue()));
            request.replace(request.length()-4, request.length(),"");
        }
        System.out.println(request.toString());
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(request.toString())) {
            while (resultSet.next()){
                HashMap<String, String> innerMap = new HashMap<>();
                for(String str : columnsSettings.keySet()) {
                    int columnIndex = resultSet.findColumn(str);
                    if(columnIndex!=1) innerMap.put(str,resultSet.getString(columnIndex));
                }
                resMap.put(resultSet.getInt(1),innerMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    @Override
    public void update(String tableName, Map<String, String> searchSettings) {
        StringBuilder request = new StringBuilder(String.format("UPDATE %s SET ", tableName));
        for(Map.Entry<String, String> pair : searchSettings.entrySet()) {
            request.append(String.format("%s = '%s', ",pair.getKey(), pair.getValue()));
        }
        request.replace(request.length()-2, request.length(), "");
        request.append(String.format(" WHERE id = %s",searchSettings.get("id")));
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteRegister(String tableName, Map<String, String> deleteSettings) {
        StringBuilder request = new StringBuilder(String.format("DELETE FROM %s WHERE ", tableName));
           for(Map.Entry<String, String> pair : deleteSettings.entrySet()) {
               if("id".equals(pair.getKey())) request.append(String.format("%s = %s AND ",pair.getKey(), pair.getValue()));
               else request.append(String.format("%s = '%s' AND ",pair.getKey(), pair.getValue()));
           }
           request.replace(request.length()-4, request.length(),"");
        super.executeSqlStatement(request.toString());
    }

    @Override
    public void deleteTable(String tableName) {
        super.executeSqlStatement("DROP TABLE "+tableName);
    }

}

