package com.company;

import com.company.model.HandbookRegister;
import com.company.service.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SecondTest {
    public static void main (String[] args) {
        Service service = new Service();
        LinkedHashMap<String, String> columnsSettings = new LinkedHashMap<>();
        columnsSettings.put("first_name", "VARCHAR(20)");
        columnsSettings.put("male", "BOOL");
        columnsSettings.put("number", "BIGINT");
        HandbookRegister register = new HandbookRegister("Handbook",null, null, columnsSettings);
        service.createTable(register);
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("male", "TRUE");
        columnsData.put("number", "777");
        register = new HandbookRegister("Handbook", null,columnsData,columnsSettings);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("male", "TRUE");
        columnsData.put("number", "333");
        register = new HandbookRegister("Handbook", null,columnsData,columnsSettings);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Kate");
        columnsData.put("male", "FALSE");
        columnsData.put("number", "888");
        register = new HandbookRegister("Handbook",null,columnsData,columnsSettings);
        service.insert(register);
        Map<Integer, HashMap<String, String>> result;
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name","Ivan");
       // columnsData.put("male","TRUE");
        result = service.find(new HandbookRegister("Handbook",null,columnsData, columnsSettings));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        result = service.find(new HandbookRegister("Handbook", null,null,columnsSettings));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        columnsData = new LinkedHashMap<>();
        columnsData.put("id", "2");
        columnsData.put("first_name","koly");
        columnsData.put("male","TRUE");
        columnsData.put("number","341");
        register = new HandbookRegister("Handbook",null,columnsData, columnsSettings);
        service.update(register);
        result = service.find(new HandbookRegister("Handbook", null,null,columnsSettings));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        columnsData = new LinkedHashMap<>();
        columnsData.put("id","2");
        register = new HandbookRegister("Handbook",null,columnsData, columnsSettings);
        service.deleteRegister(register);
        result = service.find(new HandbookRegister("Handbook", null,null,columnsSettings));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        columnsSettings = new LinkedHashMap<>();
        columnsSettings.put("first_name", "VARCHAR(20)");
        columnsSettings.put("second_name", "VARCHAR(24)");
        columnsSettings.put("number", "BIGINT");
        columnsData = new LinkedHashMap<>();
        columnsData.put("male", "BOOL");
       Map<String, String> newColumnsSettings =
               service.changeColumn(new HandbookRegister("Handbook",null,columnsData,columnsSettings));
        result = service.find(new HandbookRegister("Handbook", null,null,(LinkedHashMap) newColumnsSettings));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
    }
}
