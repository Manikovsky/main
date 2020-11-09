package com.company;

import com.company.model.HandbookRegister;
import com.company.service.Service;
import org.junit.Assert;
import org.junit.Before;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    Service service = new Service();
    Map<Integer, Map<String, String>> expectedResult = new HashMap<>();
    LinkedHashMap<String, String> columnsSettings = new LinkedHashMap<>();

    @Before
    public void initExpectedRegisters() {
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        columnsData.put("number", "140");
        expectedResult.put(1, columnsData);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Petrov");
        columnsData.put("number", "210");
        expectedResult.put(2, columnsData);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        columnsData.put("number", "210");
        expectedResult.put(3, columnsData);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Kate");
        columnsData.put("last_name", "Li");
        columnsData.put("number", "500");
        expectedResult.put(4, columnsData);
        columnsSettings.put("first_name", "VARCHAR(25)");
        columnsSettings.put("last_name", "VARCHAR(25)");
        columnsSettings.put("number", "BIGINT");
    }

    @org.junit.Test
    public void mainTest() {
        fillAndChangeTable();
        initExpectedRegisters();
        findRegister();
        initExpectedRegisters();
        updateRegister();
        initExpectedRegisters();
        deleteRegister();
        initExpectedRegisters();
        deleteTable();
    }

    @org.junit.Test
    public void fillAndChangeTable() {
        LinkedHashMap<String, String> tempColumnsSettings = new LinkedHashMap<>();
        tempColumnsSettings.put("first_name", "VARCHAR(25)");
        tempColumnsSettings.put("male", "BOOL");
        tempColumnsSettings.put("number", "BIGINT");
        HandbookRegister register =
                new HandbookRegister("Handbook", null, null, tempColumnsSettings);
        service.createTable(register);
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("male", "BOOL");
        register = new HandbookRegister("Handbook",null, columnsData, columnsSettings);
        service.changeColumn(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        columnsData.put("number", "140");
        register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Petrov");
        columnsData.put("number", "210");
        register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        columnsData.put("number", "210");
        register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Kate");
        columnsData.put("last_name", "Li");
        columnsData.put("number", "500");
        register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.insert(register);
        register = new HandbookRegister("Handbook", null, null, columnsSettings);
        Map<Integer, Map<String, String>> actual = service.find(register);
        Assert.assertEquals(expectedResult, actual);
    }

    @org.junit.Test
    public void findRegister() {
        expectedResult.remove(2);
        expectedResult.remove(4);
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        HandbookRegister register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        Map<Integer, Map<String, String>> actual = service.find(register);
        Assert.assertEquals(expectedResult, actual);
    }

    @org.junit.Test
    public void updateRegister() {
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("id", "3");
        columnsData.put("first_name", "Oleg");
        columnsData.put("last_name", "Kir");
        columnsData.put("number", "700");
        expectedResult.put(3, columnsData);
        HandbookRegister register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.update(register);
        columnsData.remove("id");
        Map<Integer, Map<String, String>> actual =
                service.find(new HandbookRegister("Handbook",null, null, columnsSettings));
        Assert.assertEquals(expectedResult, actual);
    }

    @org.junit.Test
    public void deleteRegister() {
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Oleg");
        columnsData.put("last_name", "Kir");
        columnsData.put("number", "700");
        expectedResult.put(3, columnsData);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Kate");
        expectedResult.remove(4);
        HandbookRegister register = new HandbookRegister("Handbook", null, columnsData, columnsSettings);
        service.deleteRegister(register);
        Map<Integer, Map<String, String>> actual =
                service.find(new HandbookRegister("Handbook", null, null, columnsSettings));
        Assert.assertEquals(expectedResult, actual);
    }

    @org.junit.Test
    public void deleteTable() {
        HandbookRegister register =
                new HandbookRegister("Handbook", null, null, null);
        service.deleteHandbook(register);
        register = new HandbookRegister("Handbook", null, null, columnsSettings);
        Map<Integer, Map<String, String>> actual = service.find(register);
        Assert.fail("Таблица не найдена");
    }
}
