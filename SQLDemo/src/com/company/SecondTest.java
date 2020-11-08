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
        columnsSettings.put("last_name", "VARCHAR(20)");
        columnsSettings.put("number", "VARCHAR(20)");
        HandbookRegister register = new HandbookRegister("Handbook", columnsSettings, null);
        service.createTable(register);
        LinkedHashMap<String, String> columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Manikovsky");
        columnsData.put("number", "777");
        register = new HandbookRegister("Handbook", columnsData);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Ivan");
        columnsData.put("last_name", "Leonov");
        columnsData.put("number", "333");
        register = new HandbookRegister("Handbook", columnsData);
        service.insert(register);
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name", "Kate");
        columnsData.put("last_name", "Li");
        columnsData.put("number", "888");
        register = new HandbookRegister("Handbook", columnsData);
        service.insert(register);
        Map<Integer, HashMap<String, String>> result;
        columnsData = new LinkedHashMap<>();
        columnsData.put("first_name","Ivan");
        columnsData.put("last_name","Leonov");
        result = service.find(new HandbookRegister("Handbook",columnsSettings, columnsData));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        result = service.find(new HandbookRegister("Handbook", columnsSettings,null));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        columnsData = new LinkedHashMap<>();
        columnsData.put("id", "2");
        columnsData.put("first_name","koly");
        columnsData.put("last_name","dolj");
        register = new HandbookRegister("Handbook",columnsData);
        service.update(register);
        result = service.find(new HandbookRegister("Handbook", columnsSettings,null));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
        columnsData = new LinkedHashMap<>();
        columnsData.put("id","2");
        register = new HandbookRegister("Handbook",columnsData);
        service.deleteRegister(register);
        result = service.find(new HandbookRegister("Handbook", columnsSettings,null));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
       Map<String, String> newColumnsSettings =
               service.changeColumn("Handbook","second_name","last_name","VARCHAR(24)",columnsSettings);
        result = service.find(new HandbookRegister("Handbook",(LinkedHashMap) newColumnsSettings,null));
        for(Map.Entry<Integer,HashMap<String, String>> outerPair : result.entrySet()) {
            for(HashMap.Entry<String, String> innerPair : outerPair.getValue().entrySet()) {
                System.out.printf("%s = %s ",innerPair.getKey(),innerPair.getValue());
            }
            System.out.println("");
        }
    }
}
