package com.company.repository;

import java.util.Map;

public interface OperationsDAO {
    void insert(String tableName,Map<String, String> columnsData, Map<String, String> columnsSettings);
    Map<Integer, Map<String, String>>
    select(String tableName, Map<String, String> settingsSearch, Map<String, String> columnsSettings);
    void update(String tableName,Map<String, String> columnsData, Map<String, String> columnsSettings);
    void deleteRegister(String tableName, Map<String, String> columnsData, Map<String, String> columnsSettings);
}
