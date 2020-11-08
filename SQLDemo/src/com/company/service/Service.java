package com.company.service;

import com.company.model.HandbookRegister;
import com.company.repository.Handbook;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Service {

    private Handbook handbook = new Handbook();
    private Map<String, Map<String, String>> tables = new HashMap<>();

    public static Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

    public Handbook getHandbook() {
        return handbook;
    }

    public void setHandbook(Handbook handbook) {
        this.handbook = handbook;
    }

    public void createTable(HandbookRegister register) {
        Map<String, String> columnsSettings = new LinkedHashMap<>();
        columnsSettings.put("id", "BIGINT");
        columnsSettings.putAll(register.getColumnsSettings());
        tables.put(register.getTableName(),columnsSettings);
        handbook.createTable(register.getTableName(), register.getColumnsSettings());
    }

    public void addColumn(HandbookRegister register) {
        String previousColumn = null;
        Map<String, String> newColumnsSettings = register.getColumnsSettings();
        for(Map.Entry<String, String> pair: newColumnsSettings.entrySet()) {
            String columnName = pair.getKey();
            String columnType = pair.getValue();
            Map<String, String> oldMapSettings = tables.get(register.getTableName());
            if(oldMapSettings.containsKey(columnName)) previousColumn = columnName;
            else {
                handbook.addColumn(register.getTableName(), previousColumn, columnName, columnType);
                oldMapSettings.put(columnName, columnType);
            }
        }
    }

    public void deleteColumn(HandbookRegister register) {
        Map<String, String> oldMapSettings = tables.get(register.getTableName());
        Map<String, String> deletedColumns = register.getColumnsData();
        for(Map.Entry<String, String> pair : deletedColumns.entrySet()) {
            String columnName = pair.getKey();
            handbook.deleteColumn(register.getTableName(), columnName);
            oldMapSettings.remove(columnName);
        }

    }

    public void renameTable(HandbookRegister register) {
        Map<String, Map<String, String>> newTables = new HashMap<>();
        for(Map.Entry<String, Map<String, String>> pair : tables.entrySet()) {
            String tableName = pair.getKey();
            if(tableName.equals(register.getTableName())) {
                handbook.renameTable(register.getTableName(), register.getNewName());
                newTables.put(register.getNewName(),pair.getValue());
            }
            else newTables.put(tableName, pair.getValue());
        }
        tables = newTables;
    }

    public void insert(HandbookRegister register) {
        handbook.insert(register.getTableName(),register.getColumnsData(), register.getColumnsSettings());
    }

    public Map<String, String> changeColumn(HandbookRegister register) {
        deleteColumn(register);
        addColumn(register);
        return tables.get(register.getTableName());
    }

    public Map<Integer, HashMap<String, String>> find(HandbookRegister register) {
        return handbook.select(register.getTableName(), register.getColumnsData(), register.getColumnsSettings());
    }

    public void update(HandbookRegister register) {
        handbook. update(register.getTableName(), register.getColumnsData(), register.getColumnsSettings());
    }

    public void deleteHandbook(HandbookRegister register) {
        handbook.deleteTable(register.getTableName());
    }

    public void deleteRegister(HandbookRegister register) {
        handbook.deleteRegister(register.getTableName(), register.getColumnsData(), register.getColumnsSettings());
    }

    public void closeConnection() {
        handbook.close();
    }

}
