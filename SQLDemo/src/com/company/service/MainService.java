package com.company.service;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.repository.CatalogRepository;
import com.company.repository.ColumnRepository;
import com.company.repository.RowRepository;
import com.company.repository.ValueRepository;

import java.util.List;

public class MainService {
    private final CatalogRepository catalogRepository = new CatalogRepository();
    private final ColumnRepository columnRepository = new ColumnRepository();
    private final RowRepository rowRepository = new RowRepository();
    private final ValueRepository valueRepository = new ValueRepository();

    public MainService() {
        catalogRepository.initializationTable();
        columnRepository.initializationTable();
        rowRepository.initializationTable();
        valueRepository.initializationTable();
    }

    public void addCatalog(Catalog catalog) {
        catalogRepository.addRecord(catalog);
        long id = getCatalogId(catalog).getTableId();
        catalog.setTableId(id);
        List<Row> rowList = catalog.getRows();
        if(rowList != null && !rowList.isEmpty()) {
            List<Column> columnList = catalog.getRows().get(0).getColumns();
            if(columnList != null) addColumns(catalog);
        }
    }

    public void addColumns(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        columnRepository.addRecord(catalog);
    }

    public void changeCatalogName(Catalog catalog) {
        catalogRepository.changeRecord(catalog);
    }

    public void deleteCatalog(Catalog catalog) {
        catalogRepository.deleteRecord(catalog);
    }

    public Catalog getCatalogId(Catalog catalog) {
        return catalogRepository.findRecord(catalog);
    }

    public void addColumn(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        columnRepository.addRecord(catalog);
    }

    public void deleteColumn(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        columnRepository.deleteRecord(catalog);
    }

    public void changeColumn(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        columnRepository.changeRecord(catalog);
    }

    public Catalog getColumns(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        return columnRepository.findRecord(catalog);
    }
    public void addRegister(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        if(catalog.getRows().get(0).getId() <= 0) {
            rowRepository.addRecord(catalog);
            long rowId = rowRepository.findRecord(new Catalog(null,0, null)).getRows().get(0).getId();
            catalog.getRows().get(0).setId(rowId);
        }
        long columnIndex = catalog.getRows().get(0).getColumns().get(0).getColumnId();
        if(columnIndex <= 0) {
            List<Column> columnsWithId = getColumns(catalog).getRows().get(0).getColumns();
            for(Column column : catalog.getRows().get(0).getColumns()) {
                for(Column columnId : columnsWithId) {
                    if(column.getColumnName().equals(columnId.getColumnName())) {
                        column.setColumnId(columnId.getColumnId());
                    }
                }
            }
        }
        valueRepository.addRecord(catalog);

    }

    public void deleteRegister(Catalog catalog) {
        rowRepository.deleteRecord(catalog);
    }

    public void changeRegister(Catalog catalog) {
        valueRepository.deleteRecord(catalog);
        addRegister(catalog);
    }

    public Catalog findRow(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        long columnIndex = catalog.getRows().get(0).getColumns().get(0).getColumnId();
        if(columnIndex <= 0) {
            List<Column> columnsWithId = getColumns(catalog).getRows().get(0).getColumns();
            for(Column column : catalog.getRows().get(0).getColumns()){
                for(Column columnId : columnsWithId) {
                    if(column.getColumnName().equals(columnId.getColumnName())) column.setColumnId(columnId.getColumnId());
                }
            }
        }
        return rowRepository.findRecord(catalog);
    }

    public Catalog findRegister(Catalog catalog) {
        long id = getCatalogId(catalog).getTableId();
        if(id <= 0) catalog.setTableId(id);
        Catalog catalogWithRow = findRow(catalog);
        return valueRepository.findRecord(catalogWithRow);
    }

}
