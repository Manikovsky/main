package com.company.controller;

import com.company.model.HandbookRegister;
import com.company.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class Controller {
/*
    private Writer writer;
    private Service service;

    public Controller(Writer writer) {
        this.writer = writer;
        this.service = new Service();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void readMessage(String string) {
        try(StringReader reader = new StringReader(string)) {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(reader, Message.class);
            switch (message.getType()) {
                case CREATE_TABLE: {
                    createTable();
                    break; }
                case DELETE_TABLE: {
                    deleteTable();
                    break; }
                case INSERT_REGISTER: {
                    insertRegister(message.getRegisters());
                    break; }
                case DELETE_REGISTER: {
                    deleteRegister(message.getRegisters());
                    break; }
                case UPDATE_REGISTER: {
                    updateRegister(message.getRegisters());
                    break; }
                case READ_REGISTER: {
                    HandbookRegister register = message.getRegisters().get(0);
                    readRegister(register); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createTable() {
        service.createTable();
    }

    protected void deleteTable() { service.deleteHandbook(); }

    protected void insertRegister(List<HandbookRegister> list) {
        for(HandbookRegister register : list){
            service.insert(register);
        }
    }

    protected void deleteRegister(List<HandbookRegister> list) {
        for(HandbookRegister register : list) {
            service.deleteRegister(register);
        }
    }
    protected void updateRegister(List<HandbookRegister> list) {
        for(HandbookRegister register : list) {
            service.update(register);
        }
    }
    protected void readRegister(HandbookRegister register) {
        List<HandbookRegister> list = null;
        if(register.getId()!=0) list = service.findInId(register);
        else if(register.getFirstName() != null & register.getLastName() != null)
            list = service.findInFullName(register);
        else if(register.getFirstName() != null & register.getLastName() == null)
            list =  service.findInFirstName(register);
        else if(register.getFirstName() == null & register.getLastName() != null)
            list =  service.findInFirstName(register);
        writeMessage(new Message(MessageType.READ_REGISTER, list));
    }

    public void writeMessage(Message message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String createTable = "{\"type\":\"CREATE_TABLE\",\"registers\":null}";
        String insertRegister1 = "{\"type\":\"INSERT_REGISTER\",\"registers\":[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Manikovsky\",\"numberPhone\":\" 11\"}]}";
        String insertRegister3 = "{\"type\":\"INSERT_REGISTER\",\"registers\":[{\"id\":3,\"firstName\":\"Ivan\",\"lastName\":\"Leonov\",\"numberPhone\":\" 22\"}]}";
        String insertRegister2 = "{\"type\":\"INSERT_REGISTER\",\"registers\":[{\"id\":2,\"firstName\":\"Kate\",\"lastName\":\"Li\",\"numberPhone\":\" 33\"}]}";
        String updateRegister = "{\"type\":\"UPDATE_REGISTER\",\"registers\":[{\"id\":1,\"firstName\":\"Iv\",\"lastName\":\"Man\",\"numberPhone\":\"777\"}]}";
        String deleteTable = "{\"type\":\"DELETE_TABLE\",\"registers\":null}";
        String deleteRegister = "{\"type\":\"DELETE_REGISTER\",\"registers\":[{\"id\":2,\"firstName\":\"Ivan\",\"lastName\":\"Leonov\",\"numberPhone\":\"22\"}]}";
        String readRegisters = "{\"type\":\"READ_REGISTER\",\"registers\":[{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":null,\"numberPhone\":null}]}";

        StringWriter writer = new StringWriter();
        Controller controller = new Controller(writer);

        controller.readMessage(createTable);
        controller.readMessage(insertRegister1);
        controller.readMessage(insertRegister2);
        controller.readMessage(insertRegister3);
        controller.readMessage(readRegisters);
        System.out.println(writer.toString());

    }
*/
}
