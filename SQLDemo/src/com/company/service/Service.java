package com.company.service;

import com.company.model.HandbookRegister;
import com.company.repository.HandbookDAO;
import com.company.repository.TableDAO;

import java.sql.*;
import java.util.List;

public class Service {

    TableDAO handBooks = new HandbookDAO();;

    public static Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

    public TableDAO getHandBooks() {
        return handBooks;
    }

    public void setHandBooks(TableDAO handBooks) {
        this.handBooks = handBooks;
    }

    public void createTable() {
        handBooks.createTable();
    }

    public void insert(HandbookRegister register) {
        handBooks.insert(register);
    }

    public List<HandbookRegister> findInFirstName(HandbookRegister register) {
        return handBooks.select(register);
    }

    public List<HandbookRegister> findInLastName(HandbookRegister register) {
        return handBooks.select(register);
    }
    public List<HandbookRegister> findInId(HandbookRegister register) {
        return handBooks.select(register);
    }
    public List<HandbookRegister> findInFullName(HandbookRegister register) {
        return handBooks.select(register);
    }
    public void update(HandbookRegister register) {
        handBooks. update(register);
    }
    public void deleteHandbook() {
        handBooks.deleteTable();
    }
    public void deleteRegister(HandbookRegister register) {
        handBooks.deleteRegister(register);
    }

    public void closeConnection() {
        handBooks.close();
    }

}
