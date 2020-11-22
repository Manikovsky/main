package com.company;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.service.CatalogService;
import com.company.service.DataService;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Test {
    DataService dataService = new DataService();
    CatalogService catalogService = dataService.getCatalogService();

    @org.junit.Test
    public void mainTest() {
        createHandbook();
        changeColumn();
        addAndFindData();
        changeRegister();
        deleteRegister();
        deleteCatalog();
    }

    @org.junit.Test
    public void createHandbook() {
        catalogService.initialize();
        dataService.initialize();
        List<Column> columnsSettings = new ArrayList<>();
        columnsSettings.add(new Column(0, "first_name", "String", null));
        columnsSettings.add(new Column(0, "last_name", "String", null));
        columnsSettings.add(new Column(0, "other_name", "String", null));
        List<Row> rowList = new ArrayList<>();
        rowList.add(new Row(0, columnsSettings));
        Catalog catalog = new Catalog("Handbook", 0, rowList);
        catalogService.addCatalog(catalog);
        Catalog actualCatalog = catalogService.getColumns(catalog);
        columnsSettings.clear();
        columnsSettings.add(new Column(1, "first_name", "String", null));
        columnsSettings.add(new Column(2, "last_name", "String", null));
        columnsSettings.add(new Column(3, "other_name", "String", null));
        rowList.clear();
        rowList.add(new Row(-1, columnsSettings));
        Catalog expectedCatalog = new Catalog("Handbook", 1, rowList);
        Assert.assertEquals(expectedCatalog,actualCatalog);

    }

    @org.junit.Test
    public void changeColumn() {
        List<Column> columnsSettings = new ArrayList<>();
        columnsSettings.add(new Column(0, "other_name", "String", null));
        columnsSettings.add(new Column(0, "number", "Integer", null));
        List<Row> rowList = new ArrayList<>();
        rowList.add(new Row(0,columnsSettings));
        Catalog catalog = new Catalog("Handbook", 0, rowList);
        catalogService.changeColumn(catalog);
        Catalog actual = catalogService.getColumns(new Catalog("Handbook", 0, null));
        columnsSettings.clear();
        columnsSettings.add(new Column(1, "first_name", "String", null));
        columnsSettings.add(new Column(2, "last_name", "String", null));
        columnsSettings.add(new Column(4, "number", "Integer", null));
        rowList.clear();
        rowList.add(new Row(-1, columnsSettings));
        Catalog expected = new Catalog("Handbook",1, rowList);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void addAndFindData() {
        List<Row> rowList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            List<Column> columnsSettings = new ArrayList<>();
            columnsSettings.add(new Column(0, "first_name", "String", "Ivan - " + i));
            columnsSettings.add(new Column(0, "last_name", "String", "Manikovsky - " +i));
            columnsSettings.add(new Column(0, "number", "Integer", i));
            rowList.add(new Row(0, columnsSettings));
        }
        dataService.addRegister(new Catalog("Handbook", 0, rowList));

        rowList.clear();
        List<Column> searchingSettings = new ArrayList<>();
        searchingSettings.add(new Column(0, "first_name", "String", "Ivan - " + 2));
        searchingSettings.add(new Column(0, "last_name", "String", "Manikovsky - " +2));
        rowList.add(new Row(0, searchingSettings));
        Catalog actualCatalog = dataService.findRegister(new Catalog("Handbook", 0, rowList));
        List<Column> actualColumn = new ArrayList<>();
        actualColumn.add(new Column(1, "first_name", "String", "Ivan - " + 2));
        actualColumn.add(new Column(2, "last_name", "String", "Manikovsky - " +2));
        actualColumn.add(new Column(4, "number", "Integer", 2));
        rowList.clear();
        rowList.add(new Row(3, actualColumn));
        Catalog expectedCatalog = new Catalog("Handbook", 1, rowList);
        Assert.assertEquals(expectedCatalog, actualCatalog);
    }

    public void changeRegister() {
        List<Row> rowList = new ArrayList<>();
        List<Column> columnsSettings = new ArrayList<>();
        columnsSettings.add(new Column(0, "first_name", "String", "Egor"));
        columnsSettings.add(new Column(0, "last_name", "String", "Ivanov"));
        columnsSettings.add(new Column(0, "number", "Integer", 140));
        rowList.add(new Row(1, columnsSettings));
        dataService.changeRegister(new Catalog("Handbook", 0, rowList));

        rowList.clear();
        List<Column> searchingSettings = new ArrayList<>();
        searchingSettings.add(new Column(0, "first_name", "String", "Egor"));
        rowList.add(new Row(0, searchingSettings));
        Catalog actualCatalog = dataService.findRegister(new Catalog("Handbook", 0, rowList));
        List<Column> actualColumn = new ArrayList<>();
        actualColumn.add(new Column(1, "first_name", "String", "Egor"));
        actualColumn.add(new Column(2, "last_name", "String", "Ivanov"));
        actualColumn.add(new Column(4, "number", "Integer", 140));
        rowList.clear();
        rowList.add(new Row(1, actualColumn));
        Catalog expectedCatalog = new Catalog("Handbook", 1, rowList);
        Assert.assertEquals(expectedCatalog, actualCatalog);
    }

    @org.junit.Test
    public void deleteRegister() {
        List<Row> rowList = new ArrayList<>();
        List<Column> columnsSettings = new ArrayList<>();
        columnsSettings.add(new Column(0, "first_name", "String", "Egor"));
        rowList.add(new Row(1, columnsSettings));
        dataService.deleteRegister(new Catalog("Handbook", 0, rowList));

        rowList.clear();
        List<Column> searchingSettings = new ArrayList<>();
        searchingSettings.add(new Column(0, "first_name", "String", "Egor"));
        rowList.add(new Row(0, searchingSettings));
        Catalog actualCatalog = dataService.findRegister(new Catalog("Handbook", 0, rowList));
        rowList.clear();
        Catalog expectedCatalog = new Catalog("Handbook", 1, rowList);
        Assert.assertEquals(expectedCatalog, actualCatalog);
    }

    @org.junit.Test
    public void deleteCatalog(){
        catalogService.deleteCatalog(new Catalog("Handbook", 0, null));
        Catalog actualCatalog = catalogService.getColumns(new Catalog("Handbook", 0, null));
        List<Row> rowList = new ArrayList<>();
        List<Column> columnsSettings = new ArrayList<>();
        rowList.add(new Row(-1, columnsSettings));
        Catalog expectedCatalog = new Catalog("Handbook", -1, rowList);
        Assert.assertEquals(expectedCatalog, actualCatalog);
    }

}
