package com.company.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.LinkedHashMap;

@JsonAutoDetect
public class HandbookRegister {
    private String tableName;
    private LinkedHashMap<String, String> columnsSettings;
    private LinkedHashMap<String, String> columnData;


    public HandbookRegister(String tableName, LinkedHashMap<String, String> columnsSettings,
                            LinkedHashMap<String, String> columnData) {
        this.tableName = tableName;
        this.columnsSettings = columnsSettings;
        this.columnData = columnData;
    }
    public HandbookRegister(String tableName, LinkedHashMap<String, String> columnData) {
        this.tableName = tableName;
        this.columnData = columnData;
    }

    public String getTableName() {
        return tableName;
    }

    public LinkedHashMap<String, String> getColumnsSettings() {
        return columnsSettings;
    }

    public LinkedHashMap<String, String> getColumnData() {
        return columnData;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnsSettings(LinkedHashMap<String, String> columnsSettings) {
        this.columnsSettings = columnsSettings;
    }

    public void setColumnData(LinkedHashMap<String, String> columnData) {
        this.columnData = columnData;
    }
}
