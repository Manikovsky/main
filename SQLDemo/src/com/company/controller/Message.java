package com.company.controller;

import com.company.model.HandbookRegister;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
@JsonAutoDetect
public class Message {
    private MessageType type;
    private List<HandbookRegister> registers;

    public Message(MessageType type, List<HandbookRegister> registers) {
        this.type = type;
        this.registers = registers;
    }
    public Message() {

    }
    public MessageType getType() {
        return type;
    }

    public List<HandbookRegister> getRegisters() {
        return registers;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setRegisters(List<HandbookRegister> registers) {
        this.registers = registers;
    }
}
