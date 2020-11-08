package com.company.service;

import com.company.model.HandbookRegister;
import com.company.repository.BaseTable;
import com.company.repository.Handbook;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Service {

    Handbook handBooks = new Handbook();;

    public static Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

    public BaseTable getHandBooks() {
        return handBooks;
    }

    public void setHandBooks(Handbook handBooks) {
        this.handBooks = handBooks;
    }

    public void createTable(HandbookRegister register) {
        handBooks.createTable(register.getTableName(), register.getColumnsSettings());
    }

    public void addColumn(String tableName,String previousColumn, String name, String type) {
        handBooks.addColumn(tableName, previousColumn, name, type);
    }

    public void deleteColumn(String tableName, String columnName) {
        handBooks.deleteColumn(tableName, columnName);
    }

    public void renameTable(String oldName, String newName) {
        handBooks.renameTable(oldName, newName);
    }

    public void insert(HandbookRegister register) {
        handBooks.insert(register.getTableName(), register.getColumnData());
    }

    public Map<String, String> changeColumn(String tableName, String newName, String oldName, String type,
                             Map<String, String> columnsSetting) {
        String previousColumn = null;
        Map<String, String> newColumnsSettings = new LinkedHashMap<>();
        deleteColumn(tableName, oldName);
        for(Map.Entry<String, String> pair : columnsSetting.entrySet()) {
            if(pair.getKey().equals(oldName)) break;
            previousColumn = pair.getKey();
        }
        for(Map.Entry<String, String> pair : columnsSetting.entrySet()) {
            if(pair.getKey().equals("id")) continue;
            if(pair.getKey().equals(oldName)) newColumnsSettings.put(newName, type);
            else newColumnsSettings.put(pair.getKey(), pair.getValue());
        }
        addColumn(tableName, previousColumn, newName, type);
        return newColumnsSettings;
    }

    public Map<Integer, HashMap<String, String>> find(HandbookRegister register) {
        return handBooks.select(register.getTableName(), register.getColumnData(), register.getColumnsSettings());
    }

    public void update(HandbookRegister register) {
        handBooks. update(register.getTableName(), register.getColumnData());
    }

    public void deleteHandbook(HandbookRegister register) {
        handBooks.deleteTable(register.getTableName());
    }

    public void deleteRegister(HandbookRegister register) {
        handBooks.deleteRegister(register.getTableName(), register.getColumnData());
    }

    public void closeConnection() {
        handBooks.close();
    }

}
