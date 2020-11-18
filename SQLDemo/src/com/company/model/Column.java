package com.company.model;

public class Column {
    private long columnId;
    private String columnName;
    private String columnType;
    private String columnData;

    public Column(long columnId, String columnName, String columnType, String columnData) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnData = columnData;
    }

    public long getColumnId() {
        return columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumnData() {
        return columnData;
    }

    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setColumnData(String columnData) {
        this.columnData = columnData;
    }
}
