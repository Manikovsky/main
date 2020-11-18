package com.company.model;

import java.util.List;

public class Catalog {

    private String tableName;
    private long tableId;
    private List<Row> rows;

    public Catalog(String tableName, long tableId, List<Row> rows) {
        this.tableName = tableName;
        this.tableId = tableId;
        this.rows = rows;
    }

    public String getTableName() {
        return tableName;
    }

    public long getTableId() {
        return tableId;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
