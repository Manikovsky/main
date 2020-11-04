package com.company.service;

import com.company.model.HandbookRegister;
import com.company.repository.HandBooks;
import java.sql.*;
import java.util.List;

public class Service {

    HandBooks handBooks;

    public static Connection getConnection() {
        return ConnectionFactory.getConnection();
    }

    public Service() throws SQLException {
        handBooks = new HandBooks();
    }

    public boolean createTable() throws SQLException {
        handBooks.createTable();
    }

    public boolean insert(String firstName, String lastName, String number) throws SQLException{
        handBooks.insert();
    }

    public List<HandbookRegister> findInFirstName(String firstName) throws SQLException{
        handBooks.select();
    }

    public List<HandbookRegister> findInLastName(String lastName) {

    }
    public HandbookRegister findInId(long id) {

    }
    public boolean update(long id) {

    }
    public void deleteHandbook() {

    }



    public static void main(String[] args) {
        try{
            Service service = new Service();
           // service.createTables();
           // service.insert();
            service.select();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
