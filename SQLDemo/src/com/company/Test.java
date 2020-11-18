package com.company;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.service.MainService;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MainService mainService = new MainService();
        List<Column> columnList = new ArrayList<>();
        columnList.add(new Column(0, "first_name", "String", null));
        columnList.add(new Column(0, "last_name", "String", null));
        columnList.add(new Column(0, "thirth_name", "String", null));
        List<Row> rowList = new ArrayList<>();
        rowList.add(new Row(0, columnList));
        Catalog catalog = new Catalog("Handbook", 0, rowList);
        mainService.addCatalog(catalog);
        mainService.getColumns(catalog);
        columnList = new ArrayList<>();
        columnList.add(new Column(0, "thirth_name", "String", null));
        columnList.add(new Column(0, "number", "Integer", null));
        rowList = new ArrayList<>();
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        mainService.changeColumn(catalog);
        mainService.getColumns(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Manikovsky"));
        columnList.add(new Column(0, "number", "Integer", "740"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        mainService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Leonov"));
        columnList.add(new Column(0, "number", "Integer", "104"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        mainService.addRegister(catalog);
        columnList.clear();;
        columnList.add(new Column(0, "first_name", "String", "Kate"));
        columnList.add(new Column(0, "last_name", "String", "Li"));
        columnList.add(new Column(0, "number", "Integer", "10"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        mainService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Man"));
        columnList.add(new Column(0, "number", "Integer", "100"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        mainService.addRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "Manikovsky"));
        rowList.clear();
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        mainService.findRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        columnList.add(new Column(0, "last_name", "String", "nullik"));
        columnList.add(new Column(0, "number", "Integer", "0"));
        rowList.clear();
        rowList.add(new Row(1, columnList));
        catalog.setRows(rowList);
        mainService.changeRegister(catalog);
        columnList.clear();
        columnList.add(new Column(0, "first_name", "String", "Ivan"));
        rowList.add(new Row(0, columnList));
        catalog.setRows(rowList);
        mainService.findRegister(catalog);
        mainService.deleteRegister(catalog);
        mainService.findRegister(catalog);
        mainService.deleteCatalog(catalog);
        mainService.findRegister(catalog);

    }

}
