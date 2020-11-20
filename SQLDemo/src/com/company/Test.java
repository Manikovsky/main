package com.company;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.service.CatalogService;
import com.company.service.DataService;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CatalogService catalogService = new CatalogService();
        DataService subService = new DataService();
        catalogService.initialize();
        subService.initialize();
        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column(0, "first_name", "String", null));
        columnList.add(new Column(0, "last_name", "String", null));
        columnList.add(new Column(0, "other_name", "String", null));
        List<Row> rowList = new ArrayList<>();
        rowList.add(new Row(0, columnList));
        Catalog catalog = new Catalog("Handbook", 0, rowList);
        catalogService.addCatalog(catalog);
        catalogService.getColumns(catalog);
        columnList = new ArrayList<>();
        columnList.add(new Column(0, "thirth_name", "String", null));
        columnList.add(new Column(0, "number", "Integer", null));
        rowList = new ArrayList<>();
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        catalogService.changeColumn(catalog);
        catalogService.getColumns(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Manikovsky"));
        columnList.add(new Column(0, "number", "Integer", "740"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        subService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Leonov"));
        columnList.add(new Column(0, "number", "Integer", "104"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        subService.addRegister(catalog);
        columnList.clear();;
        columnList.add(new Column(0, "first_name", "String", "Kate"));
        columnList.add(new Column(0, "last_name", "String", "Li"));
        columnList.add(new Column(0, "number", "Integer", "10"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        subService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Man"));
        columnList.add(new Column(0, "number", "Integer", "100"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        subService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Manikovsky"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        subService.findRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "nullik"));
        columnList.add(new Column(0, "number", "Integer", "0"));
        rowList.clear();
        rowList.add(new Row(1, columnList));
        catalog.setRows(rowList);
        subService.changeRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        subService.findRegister(catalog);
        subService.deleteRegister(catalog);
        subService.findRegister(catalog);
        catalogService.deleteCatalog(catalog);
        subService.findRegister(catalog);

    }

}
