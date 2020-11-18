package com.company.model;

import java.util.List;

public class Row {
    private long id;
    private List<Column> columns;

    public Row(long id, List<Column> columns) {
        this.id = id;
        this.columns = columns;
    }

    public long getId() {
        return id;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
