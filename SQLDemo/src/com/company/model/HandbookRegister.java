package com.company.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.LinkedHashMap;

@JsonAutoDetect
public class HandbookRegister {
    private String tableName;
    private String newName;
    private LinkedHashMap<String, String> columnsData;
    private LinkedHashMap<String, String> columnsSettings;


    public HandbookRegister(String tableName,String newName, LinkedHashMap<String, String> columnsData,
                            LinkedHashMap<String, String> columnsSettings) {
        this.tableName = tableName;
        this.newName = newName;
        this.columnsData = columnsData;
        this.columnsSettings = columnsSettings;
    }

    public String getTableName() {
        return tableName;
    }

    public String getNewName() { return newName; }

    public LinkedHashMap<String, String> getColumnsData() {
        return columnsData;
    }

    public LinkedHashMap<String, String> getColumnsSettings() {
        return columnsSettings;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setNewName(String newName) { this.newName = newName; }

    public void setColumnsData(LinkedHashMap<String, String> columnsData) {
        this.columnsData = columnsData;
    }

    public void setColumnsSettings(LinkedHashMap<String, String> columnsSettings) {
        this.columnsSettings = columnsSettings;
    }
}
